package application.model.Research;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Getter
@Setter
public class NewResearch {
    private Long id;
    private String name;
    private String content;
    private String date;
    private String description;
    private Integer active;
    private List<Map<String, String>> categories = new ArrayList<>();
}
