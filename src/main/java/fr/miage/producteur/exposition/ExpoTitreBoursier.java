package fr.miage.producteur.exposition;

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
    private String company;
    private float value;

    @Override
    public String toString() {
        return "TitreBoursier{" +
                "mnemo='" + mnemo + '\'' +
                ", valeur=" + value +
                '}';
    }
}
