package fr.miage.producteur.config;

import fr.miage.producteur.dao.models.TitreBoursier;

import java.util.ArrayList;
import java.util.List;

public class InitCollection {

    public static List<TitreBoursier> initCollection() {

        TitreBoursier microsoft = new TitreBoursier("MIC", "Microsoft", 15);
        TitreBoursier apple = new TitreBoursier("APP", "Apple", 20);
        TitreBoursier google = new TitreBoursier("GOO", "Google", 25);

        List<TitreBoursier> titresBoursiers = new ArrayList<>();
        titresBoursiers.add(microsoft);
        titresBoursiers.add(apple);
        titresBoursiers.add(google);

        return titresBoursiers;
    }
}
