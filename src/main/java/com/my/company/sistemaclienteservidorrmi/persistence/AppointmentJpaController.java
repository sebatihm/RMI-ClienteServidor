/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.my.company.sistemaclienteservidorrmi.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.my.company.sistemaclienteservidorrmi.entities.Appointment;
import com.my.company.sistemaclienteservidorrmi.persistence.exceptions.NonexistentEntityException;


/**
 *
 * @author Sebah
 */
public class AppointmentJpaController implements Serializable {
    private EntityManagerFactory emf = null;

    public AppointmentJpaController() {
        emf =  Persistence.createEntityManagerFactory("serverPersistence");
    }

    
    public AppointmentJpaController( EntityManagerFactory emf) {
        this. emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Appointment appointment){
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(appointment);
            em.getTransaction().commit();
        } finally {
            if (em != null){
                em.close();
            }
        }
        
    }
    
    public void edit(Appointment appointment) throws NonexistentEntityException, Exception{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(appointment);
            em.getTransaction().commit();
        } finally {
            if (em != null){
                em.close();
            }
        }
        
        
    
        
    }
    
     public void destroy(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Appointment alumno = em.getReference(Appointment.class, id);
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }
    
    public List<Appointment> findAppointmentEntities(){
        return findAppointmentEntities(true,-1,-1);
    }
    
    public List<Appointment> findAppointmentEntities(int max, int first){
        return findAppointmentEntities(true,max,first);
    }
    
    private List<Appointment> findAppointmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Appointment.class));
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
    
    public Appointment findAppointment(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Appointment.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getAppointmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Appointment> rt = cq.from(Appointment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
