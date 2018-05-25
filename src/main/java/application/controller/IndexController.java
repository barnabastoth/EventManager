package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    private String serveIndex() {
        System.out.println("ANYANDSNADNASNDNSADNSANDNSA ");
        return "index";
    }

}
