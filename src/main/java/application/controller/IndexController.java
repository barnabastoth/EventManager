package application.controller;

import application.Utils.Path;
import application.Utils.ViewUtil;

import java.util.HashMap;
import java.util.Map;

public class IndexController {

    private ViewUtil viewUtil;

    public IndexController(ViewUtil viewUtil) {this.viewUtil = viewUtil;}


    public spark.Route serveIndexPage = (request, response) -> {

        Map<String, Object> model = new HashMap<>();

        return viewUtil.render(request, model, Path.Template.INDEX, null);
    };
}
