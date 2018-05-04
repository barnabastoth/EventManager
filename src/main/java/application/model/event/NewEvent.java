package application.model.event;

import java.util.HashMap;

public class NewEvent {

    private HashMap<String, String> title = new HashMap<>();
    private HashMap<String, String> name = new HashMap<>();
    private HashMap<String, String> address = new HashMap<>();
    private HashMap<String, String> description = new HashMap<>();
    private HashMap<String, String> price = new HashMap<>();
    private HashMap<String, String> duration = new HashMap<>();
    private HashMap<String, String> ticketLink = new HashMap<>();
    private HashMap<String, String> date = new HashMap<>();
    private Integer active;

    public HashMap<String, String> getTitle() {
        return title;
    }

    public void setTitle(HashMap<String, String> title) {
        this.title = title;
    }

    public HashMap<String, String> getName() {
        return name;
    }

    public void setName(HashMap<String, String> name) {
        this.name = name;
    }

    public HashMap<String, String> getAddress() {
        return address;
    }

    public void setAddress(HashMap<String, String> address) {
        this.address = address;
    }

    public HashMap<String, String> getDescription() {
        return description;
    }

    public void setDescription(HashMap<String, String> description) {
        this.description = description;
    }

    public HashMap<String, String> getPrice() {
        return price;
    }

    public void setPrice(HashMap<String, String> price) {
        this.price = price;
    }

    public HashMap<String, String> getDuration() {
        return duration;
    }

    public void setDuration(HashMap<String, String> duration) {
        this.duration = duration;
    }

    public HashMap<String, String> getTicketLink() {
        return ticketLink;
    }

    public void setTicketLink(HashMap<String, String> ticketLink) {
        this.ticketLink = ticketLink;
    }

    public HashMap<String, String> getDate() {
        return date;
    }

    public void setDate(HashMap<String, String> date) {
        this.date = date;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
