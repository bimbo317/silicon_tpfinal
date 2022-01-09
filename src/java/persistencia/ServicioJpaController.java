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
import logica.Paquete;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Servicio;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author bimbo
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ServicioJpaController(){
        emf= Persistence.createEntityManagerFactory("TpFinalv7PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getLista_paquetes() == null) {
            servicio.setLista_paquetes(new ArrayList<Paquete>());
        }
        if (servicio.getListaVenta() == null) {
            servicio.setListaVenta(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Paquete> attachedLista_paquetes = new ArrayList<Paquete>();
            for (Paquete lista_paquetesPaqueteToAttach : servicio.getLista_paquetes()) {
                lista_paquetesPaqueteToAttach = em.getReference(lista_paquetesPaqueteToAttach.getClass(), lista_paquetesPaqueteToAttach.getId());
                attachedLista_paquetes.add(lista_paquetesPaqueteToAttach);
            }
            servicio.setLista_paquetes(attachedLista_paquetes);
            List<Venta> attachedListaVenta = new ArrayList<Venta>();
            for (Venta listaVentaVentaToAttach : servicio.getListaVenta()) {
                listaVentaVentaToAttach = em.getReference(listaVentaVentaToAttach.getClass(), listaVentaVentaToAttach.getIdVenta());
                attachedListaVenta.add(listaVentaVentaToAttach);
            }
            servicio.setListaVenta(attachedListaVenta);
            em.persist(servicio);
            for (Paquete lista_paquetesPaquete : servicio.getLista_paquetes()) {
                lista_paquetesPaquete.getLista_servicios_incluidos().add(servicio);
                lista_paquetesPaquete = em.merge(lista_paquetesPaquete);
            }
            for (Venta listaVentaVenta : servicio.getListaVenta()) {
                Servicio oldServicioOfListaVentaVenta = listaVentaVenta.getServicio();
                listaVentaVenta.setServicio(servicio);
                listaVentaVenta = em.merge(listaVentaVenta);
                if (oldServicioOfListaVentaVenta != null) {
                    oldServicioOfListaVentaVenta.getListaVenta().remove(listaVentaVenta);
                    oldServicioOfListaVentaVenta = em.merge(oldServicioOfListaVentaVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getId());
            List<Paquete> lista_paquetesOld = persistentServicio.getLista_paquetes();
            List<Paquete> lista_paquetesNew = servicio.getLista_paquetes();
            List<Venta> listaVentaOld = persistentServicio.getListaVenta();
            List<Venta> listaVentaNew = servicio.getListaVenta();
            List<Paquete> attachedLista_paquetesNew = new ArrayList<Paquete>();
            for (Paquete lista_paquetesNewPaqueteToAttach : lista_paquetesNew) {
                lista_paquetesNewPaqueteToAttach = em.getReference(lista_paquetesNewPaqueteToAttach.getClass(), lista_paquetesNewPaqueteToAttach.getId());
                attachedLista_paquetesNew.add(lista_paquetesNewPaqueteToAttach);
            }
            lista_paquetesNew = attachedLista_paquetesNew;
            servicio.setLista_paquetes(lista_paquetesNew);
            List<Venta> attachedListaVentaNew = new ArrayList<Venta>();
            for (Venta listaVentaNewVentaToAttach : listaVentaNew) {
                listaVentaNewVentaToAttach = em.getReference(listaVentaNewVentaToAttach.getClass(), listaVentaNewVentaToAttach.getIdVenta());
                attachedListaVentaNew.add(listaVentaNewVentaToAttach);
            }
            listaVentaNew = attachedListaVentaNew;
            servicio.setListaVenta(listaVentaNew);
            servicio = em.merge(servicio);
            for (Paquete lista_paquetesOldPaquete : lista_paquetesOld) {
                if (!lista_paquetesNew.contains(lista_paquetesOldPaquete)) {
                    lista_paquetesOldPaquete.getLista_servicios_incluidos().remove(servicio);
                    lista_paquetesOldPaquete = em.merge(lista_paquetesOldPaquete);
                }
            }
            for (Paquete lista_paquetesNewPaquete : lista_paquetesNew) {
                if (!lista_paquetesOld.contains(lista_paquetesNewPaquete)) {
                    lista_paquetesNewPaquete.getLista_servicios_incluidos().add(servicio);
                    lista_paquetesNewPaquete = em.merge(lista_paquetesNewPaquete);
                }
            }
            for (Venta listaVentaOldVenta : listaVentaOld) {
                if (!listaVentaNew.contains(listaVentaOldVenta)) {
                    listaVentaOldVenta.setServicio(null);
                    listaVentaOldVenta = em.merge(listaVentaOldVenta);
                }
            }
            for (Venta listaVentaNewVenta : listaVentaNew) {
                if (!listaVentaOld.contains(listaVentaNewVenta)) {
                    Servicio oldServicioOfListaVentaNewVenta = listaVentaNewVenta.getServicio();
                    listaVentaNewVenta.setServicio(servicio);
                    listaVentaNewVenta = em.merge(listaVentaNewVenta);
                    if (oldServicioOfListaVentaNewVenta != null && !oldServicioOfListaVentaNewVenta.equals(servicio)) {
                        oldServicioOfListaVentaNewVenta.getListaVenta().remove(listaVentaNewVenta);
                        oldServicioOfListaVentaNewVenta = em.merge(oldServicioOfListaVentaNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = servicio.getId();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Paquete> lista_paquetes = servicio.getLista_paquetes();
            for (Paquete lista_paquetesPaquete : lista_paquetes) {
                lista_paquetesPaquete.getLista_servicios_incluidos().remove(servicio);
                lista_paquetesPaquete = em.merge(lista_paquetesPaquete);
            }
            List<Venta> listaVenta = servicio.getListaVenta();
            for (Venta listaVentaVenta : listaVenta) {
                listaVentaVenta.setServicio(null);
                listaVentaVenta = em.merge(listaVentaVenta);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
