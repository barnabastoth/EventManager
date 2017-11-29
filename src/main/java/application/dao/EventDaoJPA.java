package application.dao;

import application.model.Event.Event;
import application.model.Event.Speaker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public Event getLatestEvent() {
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Event> result = em.createNamedQuery("Event.getLatestEvent", Event.class);

        List<Event> events = result.getResultList();

        em.close();

        if (!events.isEmpty()) {
            return events.get(0);
        }
        return null;
    }

    public Event getEventByID(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Event> result = em.createNamedQuery("Event.getEventByID", Event.class)
                .setParameter("id", id);

        List<Event> events = result.getResultList();

        em.close();

        if (!events.isEmpty()) {
            return events.get(0);
        }
        return null;
    }

//    public void addSpeakerToEvent(Event event, String speaker) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//
//        Event mergedEvent = em.merge(event);
//
//        mergedEvent.addSpeaker(speaker);
//
//        em.getTransaction().commit();
//        em.close();
//    }
}
