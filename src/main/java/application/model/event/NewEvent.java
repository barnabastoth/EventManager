package application.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewEvent {

    private Map<String, String> basicInfo = new HashMap<>();
    private List<Map<String, String>> fields = new ArrayList<>();
    private List<Map<String, String>> speakers = new ArrayList<>();
    private Map<String, String> settings = new HashMap<>();

    public NewEvent() { }

    public Map<String, String> getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(Map<String, String> basicInfo) {
        this.basicInfo = basicInfo;
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
