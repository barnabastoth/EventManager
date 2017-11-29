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
        Event event1 = new Event("Valami event", "Budapest ize utca 12/4", new Date(), "Ez egy hosszu, altah, fellepokrol, altahosszu leiras az esemenyrol, fellepokrol, altalanos programrol, etc", 20L, 3L, "bg-img-1.jpg", "http://www.ticketportal.hu/");
        Event event2 = new Event("Valami event2", "Budapest ize utca 12/5", new Date(), "Ez egyfdsfdshosszu leiras ergaz esemenyrol, fellepokrol, alfdsfdstalanos programrol, etc", 203L, 36L, "bg-img-2.jpg", "http://www.ticketportal.hu/");
        Event event3 = new Event("Valami event3", "Budapest ize utca 12/7", new Date(), "Ez egy hosszu leiras agregregz esemenyrol, fellepokrol, altalanos programrol, etc", 220L, 30L, "bg-img-3.jpg", "http://www.ticketportal.hu/");
        Event event4 = new Event("Valami event4", "Budapest ize utchtrhtrehtrhtrea 12/4", new Date(), "Ez egy hosszu leiras az ewfewfewfweesemenyrol, fellepokrol, altalanos programrol, etc", 23213210L, 3L, "bg-img-4.jpg", "http://www.ticketportal.hu/");
        Event event5 = new Event("Valami event5", "Budapest ize utcagrtgtr 12/5", new Date(), "Ez egyfdsfdshosszu leiras ergfewfewfaz esemenyrol, fellepokrol, alfdsfdstalanos programrol, etc", 203213213L, 3621231L, "bg-img-5.jpg", "http://www.ticketportal.hu/");
        eventDaoJPA.addEvent(event1);
        eventDaoJPA.addEvent(event2);
        eventDaoJPA.addEvent(event3);
        eventDaoJPA.addEvent(event4);
        eventDaoJPA.addEvent(event5);




//        eventDaoJPA.addSpeakerToEvent(event1, "Speaker1fewfew");
//        eventDaoJPA.addSpeakerToEvent(event1, "Speakefewfw  r1");
//        eventDaoJPA.addSpeakerToEvent(event2, "SpeakeCXYr1");
//        eventDaoJPA.addSpeakerToEvent(event2, "Speake2  Qr1");
//        eventDaoJPA.addSpeakerToEvent(event3, "SpeakeFDSAr1");
//        eventDaoJPA.addSpeakerToEvent(event3, "SpeakeEDWQr1");
//        eventDaoJPA.addSpeakerToEvent(event4, "Speaker1");
//        eventDaoJPA.addSpeakerToEvent(event4, "Speake34324r1");
//        eventDaoJPA.addSpeakerToEvent(event5, "SpeakeGHSFr1");
//        eventDaoJPA.addSpeakerToEvent(event5, "Speaker1");
    }
}
