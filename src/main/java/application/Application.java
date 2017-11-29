package application;

import application.Utils.DataGenerator;
import application.Utils.Path;
import application.Utils.ViewUtil;
import application.controller.EventController;
import application.controller.IndexController;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Application {
    IndexController indexController;
    DataGenerator dataGenerator;
    EventController eventController;


    public Application(IndexController indexController, ViewUtil viewUtil, DataGenerator dataGenerator, EventController eventController) {
        this.indexController = indexController;
        this.dataGenerator = dataGenerator;
        this.eventController = eventController;
    }

    public void start() {

        System.out.println(eventController.getEventByID(3L));

        dataGenerator.initTestData();

        // SERVER SETTINGS
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        if (Config.isDevEnv) enableDebugScreen();

        get(Path.Web.INDEX, indexController.serveIndexPage);
        get(Path.Web.LATEST_EVENT, (req, res) -> eventController.getLatestEvent());
        get(Path.Web.EVENT_BY_ID, (req, res) -> {
            Long eventID = Long.parseLong(req.params(":id"));
            return eventController.getEventByID(eventID);
        });
    }

}
