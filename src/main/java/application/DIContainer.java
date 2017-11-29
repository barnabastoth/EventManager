package application;

import application.Utils.DataGenerator;
import application.Utils.ViewUtil;
import application.controller.AccountController;
import application.controller.EventController;
import application.controller.IndexController;
import application.dao.AccountDaoJPA;
import application.dao.EventDaoJPA;
import application.dao.MenuDaoJPA;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DIContainer {

    public Application initialize() {

        ViewUtil viewUtil = new ViewUtil();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaTestingPU");
        EventDaoJPA eventDaoJPA = new EventDaoJPA(entityManagerFactory);
        MenuDaoJPA menuDaoJPA = new MenuDaoJPA(entityManagerFactory);
        IndexController indexController = new IndexController(viewUtil, menuDaoJPA);
        AccountDaoJPA accountDaoJPA = new AccountDaoJPA(entityManagerFactory);
        AccountController accountController = new AccountController(accountDaoJPA);
        EventController eventController = new EventController(eventDaoJPA);
        DataGenerator dataGenerator = new DataGenerator(indexController, accountController, eventController);


        return new Application(indexController, viewUtil, dataGenerator, eventController);
    }
}
