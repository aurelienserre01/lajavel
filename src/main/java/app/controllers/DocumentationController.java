package app.controllers;

import org.lajavel.Controller;
import org.lajavel.Response;
import org.lajavel.View;

public class DocumentationController extends Controller {
    public void gettingDocumentation(Response response){
        response.html(View.make("documentation"));
    }
}
