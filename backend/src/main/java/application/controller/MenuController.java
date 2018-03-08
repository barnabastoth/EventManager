package application.controller;

import application.model.menu.Menu;
import application.repository.CommentRepository;
import application.repository.EventRepository;
import application.repository.MenuRepository;
import application.repository.UserRepository;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@CrossOrigin
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired MenuRepository menuRepository;
    @Autowired EventRepository eventRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired UserService userService;

    @GetMapping("/{menu}")
    public Menu serveMenu(@PathVariable("menu") String menu) {
        return menuRepository.findByRoute(menu);
    }

    @GetMapping("/latestActivites")
    public Map<String, Object> serveLatestActivites() {
        Map<String, Object> latestActivites = new TreeMap<>();
        latestActivites.put("event", eventRepository.getLatestEvent());
        latestActivites.put("comment", commentRepository.getLatestComment());
        latestActivites.put("user", userService.getLatestUser());
        System.out.println(latestActivites);
        return latestActivites;
    }

}
