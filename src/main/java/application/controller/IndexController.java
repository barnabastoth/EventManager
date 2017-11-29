package application.controller;

import application.Utils.Path;
import application.Utils.ViewUtil;
import application.dao.EventDaoJPA;
import application.dao.MenuDaoJPA;
import application.model.Menu.Menu;
import com.google.gson.Gson;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class IndexController {

    private ViewUtil viewUtil;
    private MenuDaoJPA menuDaoJPA;

    public IndexController(ViewUtil viewUtil, MenuDaoJPA menuDaoJPA) {
        this.viewUtil = viewUtil;
        this.menuDaoJPA = menuDaoJPA;
    }

    public spark.Route serveIndexPage = (request, response) -> {

        Map<String, Object> model = new HashMap<>();

        return viewUtil.render(request, model, Path.Template.INDEX, null);
    };

    public void addMenu(Menu menu) {
        menuDaoJPA.addMenu(menu);
    }

    public String getMenuByID(Long id) {
        HashMap<String, Object> menus = new HashMap<>();

        menus.put("menu",menuDaoJPA.getMenuByID(id));

        return new Gson().toJson(menus);
    }


}
