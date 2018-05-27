package application.model.event;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class NewEvent {
    private Long id;
    private String name;
    private String address;
    private String date;
    private String description;
    private String shortDescription;
    private Integer active;
    private double latitude;
    private double longitude;
    private List<Map<String, String>> fields = new ArrayList<>();
    private List<Map<String, String>> speakers = new ArrayList<>();
    private Map<String, String> settings = new HashMap<>();
}
