package application.controller;

import application.Utils.Path;
import application.Utils.ViewUtil;
import application.dao.EventDaoJPA;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class IndexController {

    private ViewUtil viewUtil;
    private EventDaoJPA eventDaoJPA;

    public IndexController(ViewUtil viewUtil, EventDaoJPA eventDaoJPA) {
        this.viewUtil = viewUtil;
        this.eventDaoJPA = eventDaoJPA;
    }

    public spark.Route serveIndexPage = (request, response) -> {

        Map<String, Object> model = new HashMap<>();

        return viewUtil.render(request, model, Path.Template.INDEX, null);
    };

    public String getAllMenu() {
        HashMap<String, Object> menus = new HashMap<>();

        System.out.println(menus.get("menus"));

        return new Gson().toJson(menus);
    }


}
