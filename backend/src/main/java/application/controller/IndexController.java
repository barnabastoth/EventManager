package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String serveIndexPage() {
        return "redirect:/index.html";
    }

//    @GetMapping("/**")
//    public String serveAllRedirect() {
//        System.out.println("REDDDDDIIRRECT");
//        return "redirect:/index.html";
//    }
}
