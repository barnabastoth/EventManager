package application.controller;

import application.model.menu.Menu;
import application.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired MenuRepository menuRepository;

    @GetMapping("/{menu}")
    public Menu serveMenu(@PathVariable("menu") String menu) {
        return menuRepository.findByRoute(menu);
    }

}
