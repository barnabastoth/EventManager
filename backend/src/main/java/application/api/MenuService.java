package application.api;

import application.model.menu.Menu;
import application.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @CrossOrigin
    @GetMapping("/menu/{menu}")
    public Menu serveMenu(@PathVariable("menu") String menu) {
        return menuRepository.findByRoute(menu);
    }
}
