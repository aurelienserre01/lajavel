package app.controllers;

import app.models.Contributor;
import app.repository.ContributorsRepository;

import org.lajavel.Controller;
import org.lajavel.Response;
import org.lajavel.View;

import java.util.List;
import java.util.Map;

public class ContributorController extends Controller {

    public void showContributors(Response response){
        List<Contributor> contributors = ContributorsRepository.findAll();
        response.html(View.make("contributors", Map.entry("contributors",contributors)));
    }
}
