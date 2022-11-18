package app;

import app.controllers.ContributorController;
import app.controllers.DocumentationController;
import app.controllers.IndexController;
import app.controllers.PartenerController;
import org.lajavel.*;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Application app = Application.start(7071, Application.Mode.DEVELOPMENT);
        Route.register(HttpVerb.GET, "/", IndexController.class, "gettingStarted");
        Route.register(HttpVerb.GET, "/contributors", ContributorController.class, "showContributors");
        Route.register(HttpVerb.GET, "/parteners", PartenerController.class, "showParteners");
        Route.register(HttpVerb.GET, "/documentation", DocumentationController.class, "gettingDocumentation");

      }
}