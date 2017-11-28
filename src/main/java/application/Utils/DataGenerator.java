package application.Utils;

import application.dao.EventDaoJPA;
import application.model.Event.Event;

import java.util.Date;

public class DataGenerator {
    private EventDaoJPA eventDaoJPA;

    public DataGenerator(EventDaoJPA eventDaoJPA) {
        this.eventDaoJPA = eventDaoJPA;
    }

    public void initTestData() {
        Event event1 = new Event("Valami event", "Budapest ize utca 12/4", new Date(), "Ez egy hosszu leiras az esemenyrol, fellepokrol, altalanos programrol, etc", 20L, 3L);
        Event event2 = new Event("Valami event2", "Budapest ize utca 12/5", new Date(), "Ez egyfdsfdshosszu leiras ergaz esemenyrol, fellepokrol, alfdsfdstalanos programrol, etc", 203L, 36L);
        Event event3 = new Event("Valami event3", "Budapest ize utca 12/7", new Date(), "Ez egy hosszu leiras agregregz esemenyrol, fellepokrol, altalanos programrol, etc", 220L, 30L);

        eventDaoJPA.addEvent(event1);
        eventDaoJPA.addEvent(event2);
        eventDaoJPA.addEvent(event3);
    }
}
