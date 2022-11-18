package app.repository;

import app.models.Contributor;
import app.models.Partner;

import java.util.List;

public class PartenersRepository {
    private static PartenersRepository instance;
    private List<Partner> data;

    private PartenersRepository() {
        Partner google = new Partner("Google", "/images/logos/google_logo.png", "Los Angeles");
        Partner apple = new Partner("Apple", "/images/logos/Apple-logo.png", "Paris");

        this.data = List.of(google,apple);
    }

    public static PartenersRepository getInstance() {
        if (instance == null) {
            instance = new PartenersRepository();
        }

        return instance;
    }

    public static List<Partner> findAll(){
        return PartenersRepository.getInstance().data;
    }
}
