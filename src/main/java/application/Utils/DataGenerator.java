package application.Utils;

import application.controller.AccountController;
import application.controller.EventController;
import application.controller.IndexController;
import application.dao.EventDaoJPA;
import application.dao.MenuDaoJPA;
import application.model.Event.Event;
import application.model.Menu.Menu;

import java.util.Date;

public class DataGenerator {
    private IndexController indexController;
    private AccountController accountController;
    private EventController eventController;

    public DataGenerator(IndexController indexController, AccountController accountController, EventController eventController) {
        this.indexController = indexController;
        this.accountController = accountController;
        this.eventController = eventController;
    }

    public void initTestData() {
        Event event1 = new Event("Valami event", "Budapest ize utca 12/4", new Date(), "Ez egy hosszu, altah, fellepokrol, altahosszu leiras az esemenyrol, fellepokrol, altalanos programrol, etc", 20L, 3L, "bg-img-1.jpg", "http://www.ticketportal.hu/");
        Event event2 = new Event("Valami event2", "Budapest ize utca 12/5", new Date(), "Ez egyfdsfdshosszu leiras ergaz esemenyrol, fellepokrol, alfdsfdstalanos programrol, etc", 203L, 36L, "bg-img-2.jpg", "http://www.ticketportal.hu/");
        Event event3 = new Event("Valami event3", "Budapest ize utca 12/7", new Date(), "Ez egy hosszu leiras agregregz esemenyrol, fellepokrol, altalanos programrol, etc", 220L, 30L, "bg-img-3.jpg", "http://www.ticketportal.hu/");
        Event event4 = new Event("Valami event4", "Budapest ize utchtrhtrehtrhtrea 12/4", new Date(), "Ez egy hosszu leiras az ewfewfewfweesemenyrol, fellepokrol, altalanos programrol, etc", 23213210L, 3L, "bg-img-4.jpg", "http://www.ticketportal.hu/");
        Event event5 = new Event("Valami event5", "Budapest ize utcagrtgtr 12/5", new Date(), "Ez egyfdsfdshosszu leiras ergfewfewfaz esemenyrol, fellepokrol, alfdsfdstalanos programrol, etc", 203213213L, 3621231L, "bg-img-5.jpg", "http://www.ticketportal.hu/");
        eventController.addEvent(event1);
        eventController.addEvent(event2);
        eventController.addEvent(event3);
        eventController.addEvent(event4);
        eventController.addEvent(event5);

        Menu menu1 = new Menu("Főoldal", "ez a főoldal textez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal text");
        Menu menu2 = new Menu("Anyagok", "ez a főoldal textez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal text");
        Menu menu3 = new Menu("Fórum", "ez a főoldal textez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal text");
        Menu menu4 = new Menu("Kapcsolat", "ez a főoldal textez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal text");
        Menu menu5 = new Menu("Rólunk", "ez a főoldal textez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal textez a főoldal text \n ez a főoldal textez a főoldal text");

        indexController.addMenu(menu1);
        indexController.addMenu(menu2);
        indexController.addMenu(menu3);
        indexController.addMenu(menu4);
        indexController.addMenu(menu5);

    }
}
