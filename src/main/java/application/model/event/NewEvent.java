package application.model.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewEvent {

    private Long id;
    private String name;
    private String address;
    private String date;
    private String description;
    private String active;
    private List<Map<String, String>> fields = new ArrayList<>();
    private List<Map<String, String>> speakers = new ArrayList<>();
    private Map<String, String> settings = new HashMap<>();

    public NewEvent() { }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Map<String, String>> getFields() {
        return fields;
    }

    public void setFields(List<Map<String, String>> fields) {
        this.fields = fields;
    }

    public List<Map<String, String>> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Map<String, String>> speakers) {
        this.speakers = speakers;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }
}
