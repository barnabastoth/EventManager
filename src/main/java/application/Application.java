package application;

import application.Utils.DataGenerator;
import application.Utils.Path;
import application.Utils.ViewUtil;
import application.controller.IndexController;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Application {
    IndexController indexController;
    DataGenerator dataGenerator;


    public Application(IndexController indexController, ViewUtil viewUtil, DataGenerator dataGenerator) {
        this.indexController = indexController;
        this.dataGenerator = dataGenerator;
    }

    public void start() {

        dataGenerator.initTestData();

        // SERVER SETTINGS
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        if (Config.isDevEnv) enableDebugScreen();

        get(Path.Web.INDEX, indexController.serveIndexPage);
    }

}
