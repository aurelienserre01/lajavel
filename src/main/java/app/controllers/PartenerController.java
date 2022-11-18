package app.controllers;

import app.models.Contributor;
import app.models.Partner;
import app.repository.ContributorsRepository;
import app.repository.PartenersRepository;
import org.lajavel.Controller;
import org.lajavel.Response;
import org.lajavel.View;

import java.util.List;
import java.util.Map;

public class PartenerController extends Controller {

    public void showParteners(Response response){
        List<Partner> parteners = PartenersRepository.findAll();
        response.html(View.make("parteners", Map.entry("parteners",parteners)));
    }
}
