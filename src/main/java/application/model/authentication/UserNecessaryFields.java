package application.model.authentication;

public class UserNecessaryFields {

    private String name;
    private String profession;
    private int nrOfEvents;

    public UserNecessaryFields(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public UserNecessaryFields(String name, String profession, int nrOfEvents) {
        this.name = name;
        this.profession = profession;
        this.nrOfEvents = nrOfEvents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getNrOfEvents() {
        return nrOfEvents;
    }

    public void setNrOfEvents(int nrOfEvents) {
        this.nrOfEvents = nrOfEvents;
    }
}
