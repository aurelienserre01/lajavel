package app.controllers;

import org.lajavel.Controller;
import org.lajavel.Personne;
import org.lajavel.Response;
import org.lajavel.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexController extends Controller {
        public void gettingStarted(Response response){
        response.html(View.make("gettingStarted"));
    }
}
