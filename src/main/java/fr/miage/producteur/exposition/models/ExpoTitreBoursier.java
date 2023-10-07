package fr.miage.producteur.exposition.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpoTitreBoursier {

    private String mnemo;

    @Override
    public String toString() {
        return "TitreBoursier{" +
                "mnemo='" + mnemo + '\'' +
                '}';
    }
}
