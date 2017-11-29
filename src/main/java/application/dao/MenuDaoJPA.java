package application.dao;

import application.model.Event.Event;
import application.model.Menu.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class MenuDaoJPA {
    private EntityManagerFactory entityManagerFactory;

    public MenuDaoJPA(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Long addMenu(Menu menu) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.persist(menu);

        em.getTransaction().commit();

        em.close();

        return menu.getID();
    }

    public Menu getMenuByID(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Menu> result = em.createNamedQuery("Menu.getMenuByID", Menu.class)
                .setParameter("id", id);

        List<Menu> menu = result.getResultList();

        em.close();

        if (!menu.isEmpty()) {
            return menu.get(0);
        }
        return null;
    }
}
