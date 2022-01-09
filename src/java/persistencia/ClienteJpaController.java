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
import logica.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Cliente;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author bimbo
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ClienteJpaController(){
        emf= Persistence.createEntityManagerFactory("TpFinalv7PU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getListaventas() == null) {
            cliente.setListaventas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedListaventas = new ArrayList<Venta>();
            for (Venta listaventasVentaToAttach : cliente.getListaventas()) {
                listaventasVentaToAttach = em.getReference(listaventasVentaToAttach.getClass(), listaventasVentaToAttach.getIdVenta());
                attachedListaventas.add(listaventasVentaToAttach);
            }
            cliente.setListaventas(attachedListaventas);
            em.persist(cliente);
            for (Venta listaventasVenta : cliente.getListaventas()) {
                Cliente oldClienteOfListaventasVenta = listaventasVenta.getCliente();
                listaventasVenta.setCliente(cliente);
                listaventasVenta = em.merge(listaventasVenta);
                if (oldClienteOfListaventasVenta != null) {
                    oldClienteOfListaventasVenta.getListaventas().remove(listaventasVenta);
                    oldClienteOfListaventasVenta = em.merge(oldClienteOfListaventasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            List<Venta> listaventasOld = persistentCliente.getListaventas();
            List<Venta> listaventasNew = cliente.getListaventas();
            List<Venta> attachedListaventasNew = new ArrayList<Venta>();
            for (Venta listaventasNewVentaToAttach : listaventasNew) {
                listaventasNewVentaToAttach = em.getReference(listaventasNewVentaToAttach.getClass(), listaventasNewVentaToAttach.getIdVenta());
                attachedListaventasNew.add(listaventasNewVentaToAttach);
            }
            listaventasNew = attachedListaventasNew;
            cliente.setListaventas(listaventasNew);
            cliente = em.merge(cliente);
            for (Venta listaventasOldVenta : listaventasOld) {
                if (!listaventasNew.contains(listaventasOldVenta)) {
                    listaventasOldVenta.setCliente(null);
                    listaventasOldVenta = em.merge(listaventasOldVenta);
                }
            }
            for (Venta listaventasNewVenta : listaventasNew) {
                if (!listaventasOld.contains(listaventasNewVenta)) {
                    Cliente oldClienteOfListaventasNewVenta = listaventasNewVenta.getCliente();
                    listaventasNewVenta.setCliente(cliente);
                    listaventasNewVenta = em.merge(listaventasNewVenta);
                    if (oldClienteOfListaventasNewVenta != null && !oldClienteOfListaventasNewVenta.equals(cliente)) {
                        oldClienteOfListaventasNewVenta.getListaventas().remove(listaventasNewVenta);
                        oldClienteOfListaventasNewVenta = em.merge(oldClienteOfListaventasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Venta> listaventas = cliente.getListaventas();
            for (Venta listaventasVenta : listaventas) {
                listaventasVenta.setCliente(null);
                listaventasVenta = em.merge(listaventasVenta);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
