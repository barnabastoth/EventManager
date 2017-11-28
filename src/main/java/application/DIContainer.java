package application;

import application.Utils.DataGenerator;
import application.Utils.ViewUtil;
import application.controller.IndexController;
import application.dao.EventDaoJPA;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.crypto.Data;

public class DIContainer {

    public Application initialize() {

        ViewUtil viewUtil = new ViewUtil();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaTestingPU");
        EventDaoJPA eventDaoJPA = new EventDaoJPA(entityManagerFactory);
        DataGenerator dataGenerator = new DataGenerator(eventDaoJPA);
        IndexController indexController = new IndexController(viewUtil);


        return new Application(indexController, viewUtil, dataGenerator);
    }
}
