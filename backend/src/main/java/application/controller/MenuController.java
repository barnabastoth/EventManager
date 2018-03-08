package application.controller;

import application.model.menu.Menu;
import application.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    @CrossOrigin
    @GetMapping("/menu/{menu}")
    public Menu serveMenu(@PathVariable("menu") String menu) {
        return menuRepository.findByRoute(menu);
    }
}
