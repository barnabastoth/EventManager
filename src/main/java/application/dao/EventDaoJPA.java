package application.dao;

import application.model.Event.Event;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EventDaoJPA {

    private EntityManagerFactory entityManagerFactory;

    public EventDaoJPA(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Long addEvent(Event event) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.persist(event);

        em.getTransaction().commit();

        em.close();

        return event.getID();
    }
}
