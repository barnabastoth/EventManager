package application.controller;

import application.dao.EventDaoJPA;
import com.google.gson.Gson;

import java.util.HashMap;

public class EventController {
    EventDaoJPA eventDaoJPA;

    public EventController(EventDaoJPA eventDaoJPA) {
        this.eventDaoJPA = eventDaoJPA;
    }

    public String getLatestEvent() {
        HashMap<String, Object> event = new HashMap<>();

        event.put("event", eventDaoJPA.getLatestEvent());

        return new Gson().toJson(event);
    }

    public String getEventByID(Long eventID) {
        HashMap<String, Object> event = new HashMap<>();

        event.put("event", eventDaoJPA.getEventByID(eventID));

        return new Gson().toJson(event);
    }
}
