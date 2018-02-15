package application.model.subscription;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subscription")
public class Subscribers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "user_id") private int id;
    @ElementCollection
    Set<String> subscribers = new HashSet<>();

    public Set<String> getSubscribers() { return subscribers; }
    public void setSubscribers(Set<String> subscribers) { this.subscribers = subscribers; }
}
