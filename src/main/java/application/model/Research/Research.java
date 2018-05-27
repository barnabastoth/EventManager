package application.model.Research;

import application.model.Category;
import application.model.authentication.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Research {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "research_id")
    private Long id;

    private String name;

    @Lob
    private String content;

    @Column(columnDefinition = "TEXT", length = 1024)
    private String description;

    private Integer active;

    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "research_categories",
        joinColumns = @JoinColumn(name = "research_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

}