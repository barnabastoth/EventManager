package application.api;

import application.model.Menu.Menu;
import application.repository.MenuRepository;
import application.utils.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuServiceREST {

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping(Path.Web.MENU_BY_ID)
    public Menu getMenuByID(@PathVariable Long id) {
        return menuRepository.getMenuByID(id);
    }
}
