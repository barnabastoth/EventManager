package application.model;

import application.model.Research.Research;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    private String name;

    private String icon;

    @ManyToMany(mappedBy = "categories",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Research> researches =  new ArrayList<>();

}