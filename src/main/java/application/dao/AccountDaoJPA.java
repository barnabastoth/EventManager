package application.dao;

import application.model.Account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDaoJPA {

    private EntityManagerFactory entityManagerFactory;

    public AccountDaoJPA(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Long add(Account account) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.persist(account);

        em.getTransaction().commit();
        em.close();

        return account.getId();
    }

    public Account findAccountById(Long accountId) {
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Account> result = em.createNamedQuery("Account.findAccountById", Account.class)
                .setParameter("accountId", accountId);
        List<Account> accounts = result.getResultList();

        em.close();

        if (!accounts.isEmpty()) {
            return accounts.get(0);
        }
        return null;
    }

}
