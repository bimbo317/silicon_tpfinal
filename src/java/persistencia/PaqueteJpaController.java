/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Paquete;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author bimbo
 */
public class PaqueteJpaController implements Serializable {

    public PaqueteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public PaqueteJpaController(){
        emf= Persistence.createEntityManagerFactory("TpFinalv7PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquete paquete) {
        if (paquete.getLista_servicios_incluidos() == null) {
            paquete.setLista_servicios_incluidos(new ArrayList<Servicio>());
        }
        if (paquete.getListaVenta() == null) {
            paquete.setListaVenta(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedLista_servicios_incluidos = new ArrayList<Servicio>();
            for (Servicio lista_servicios_incluidosServicioToAttach : paquete.getLista_servicios_incluidos()) {
                lista_servicios_incluidosServicioToAttach = em.getReference(lista_servicios_incluidosServicioToAttach.getClass(), lista_servicios_incluidosServicioToAttach.getId());
                attachedLista_servicios_incluidos.add(lista_servicios_incluidosServicioToAttach);
            }
            paquete.setLista_servicios_incluidos(attachedLista_servicios_incluidos);
            List<Venta> attachedListaVenta = new ArrayList<Venta>();
            for (Venta listaVentaVentaToAttach : paquete.getListaVenta()) {
                listaVentaVentaToAttach = em.getReference(listaVentaVentaToAttach.getClass(), listaVentaVentaToAttach.getIdVenta());
                attachedListaVenta.add(listaVentaVentaToAttach);
            }
            paquete.setListaVenta(attachedListaVenta);
            em.persist(paquete);
            for (Servicio lista_servicios_incluidosServicio : paquete.getLista_servicios_incluidos()) {
                lista_servicios_incluidosServicio.getLista_paquetes().add(paquete);
                lista_servicios_incluidosServicio = em.merge(lista_servicios_incluidosServicio);
            }
            for (Venta listaVentaVenta : paquete.getListaVenta()) {
                Paquete oldPaqueteOfListaVentaVenta = listaVentaVenta.getPaquete();
                listaVentaVenta.setPaquete(paquete);
                listaVentaVenta = em.merge(listaVentaVenta);
                if (oldPaqueteOfListaVentaVenta != null) {
                    oldPaqueteOfListaVentaVenta.getListaVenta().remove(listaVentaVenta);
                    oldPaqueteOfListaVentaVenta = em.merge(oldPaqueteOfListaVentaVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquete paquete) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete persistentPaquete = em.find(Paquete.class, paquete.getId());
            List<Servicio> lista_servicios_incluidosOld = persistentPaquete.getLista_servicios_incluidos();
            List<Servicio> lista_servicios_incluidosNew = paquete.getLista_servicios_incluidos();
            List<Venta> listaVentaOld = persistentPaquete.getListaVenta();
            List<Venta> listaVentaNew = paquete.getListaVenta();
            List<Servicio> attachedLista_servicios_incluidosNew = new ArrayList<Servicio>();
            for (Servicio lista_servicios_incluidosNewServicioToAttach : lista_servicios_incluidosNew) {
                lista_servicios_incluidosNewServicioToAttach = em.getReference(lista_servicios_incluidosNewServicioToAttach.getClass(), lista_servicios_incluidosNewServicioToAttach.getId());
                attachedLista_servicios_incluidosNew.add(lista_servicios_incluidosNewServicioToAttach);
            }
            lista_servicios_incluidosNew = attachedLista_servicios_incluidosNew;
            paquete.setLista_servicios_incluidos(lista_servicios_incluidosNew);
            List<Venta> attachedListaVentaNew = new ArrayList<Venta>();
            for (Venta listaVentaNewVentaToAttach : listaVentaNew) {
                listaVentaNewVentaToAttach = em.getReference(listaVentaNewVentaToAttach.getClass(), listaVentaNewVentaToAttach.getIdVenta());
                attachedListaVentaNew.add(listaVentaNewVentaToAttach);
            }
            listaVentaNew = attachedListaVentaNew;
            paquete.setListaVenta(listaVentaNew);
            paquete = em.merge(paquete);
            for (Servicio lista_servicios_incluidosOldServicio : lista_servicios_incluidosOld) {
                if (!lista_servicios_incluidosNew.contains(lista_servicios_incluidosOldServicio)) {
                    lista_servicios_incluidosOldServicio.getLista_paquetes().remove(paquete);
                    lista_servicios_incluidosOldServicio = em.merge(lista_servicios_incluidosOldServicio);
                }
            }
            for (Servicio lista_servicios_incluidosNewServicio : lista_servicios_incluidosNew) {
                if (!lista_servicios_incluidosOld.contains(lista_servicios_incluidosNewServicio)) {
                    lista_servicios_incluidosNewServicio.getLista_paquetes().add(paquete);
                    lista_servicios_incluidosNewServicio = em.merge(lista_servicios_incluidosNewServicio);
                }
            }
            for (Venta listaVentaOldVenta : listaVentaOld) {
                if (!listaVentaNew.contains(listaVentaOldVenta)) {
                    listaVentaOldVenta.setPaquete(null);
                    listaVentaOldVenta = em.merge(listaVentaOldVenta);
                }
            }
            for (Venta listaVentaNewVenta : listaVentaNew) {
                if (!listaVentaOld.contains(listaVentaNewVenta)) {
                    Paquete oldPaqueteOfListaVentaNewVenta = listaVentaNewVenta.getPaquete();
                    listaVentaNewVenta.setPaquete(paquete);
                    listaVentaNewVenta = em.merge(listaVentaNewVenta);
                    if (oldPaqueteOfListaVentaNewVenta != null && !oldPaqueteOfListaVentaNewVenta.equals(paquete)) {
                        oldPaqueteOfListaVentaNewVenta.getListaVenta().remove(listaVentaNewVenta);
                        oldPaqueteOfListaVentaNewVenta = em.merge(oldPaqueteOfListaVentaNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = paquete.getId();
                if (findPaquete(id) == null) {
                    throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete paquete;
            try {
                paquete = em.getReference(Paquete.class, id);
                paquete.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.", enfe);
            }
            List<Servicio> lista_servicios_incluidos = paquete.getLista_servicios_incluidos();
            for (Servicio lista_servicios_incluidosServicio : lista_servicios_incluidos) {
                lista_servicios_incluidosServicio.getLista_paquetes().remove(paquete);
                lista_servicios_incluidosServicio = em.merge(lista_servicios_incluidosServicio);
            }
            List<Venta> listaVenta = paquete.getListaVenta();
            for (Venta listaVentaVenta : listaVenta) {
                listaVentaVenta.setPaquete(null);
                listaVentaVenta = em.merge(listaVentaVenta);
            }
            em.remove(paquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquete> findPaqueteEntities() {
        return findPaqueteEntities(true, -1, -1);
    }

    public List<Paquete> findPaqueteEntities(int maxResults, int firstResult) {
        return findPaqueteEntities(false, maxResults, firstResult);
    }

    private List<Paquete> findPaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquete.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Paquete findPaquete(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquete> rt = cq.from(Paquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
