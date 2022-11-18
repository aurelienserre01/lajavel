package app.repository;

import app.models.Contributor;

import java.util.List;

public class ContributorsRepository {
    private static ContributorsRepository instance;
    private List<Contributor> data;

    private ContributorsRepository() {
        Contributor aurelien = new Contributor(1, "SERRE", "Aurelien","/images/logo_lajavel.png",21,"Oyonnax");
        Contributor damien = new Contributor(2, "DABERNAT", "Damien","/images/logo_lajavel.png",21,"Annecy");

        this.data = List.of(aurelien,damien);
    }

    public static ContributorsRepository getInstance() {
        if (instance == null) {
            instance = new ContributorsRepository();
        }

        return instance;
    }

    public static List<Contributor> findAll(){
        return ContributorsRepository.getInstance().data;
    }

}
