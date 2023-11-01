package fr.miage.producteur.dao.models;

import fr.saurel.remi.tp_tb.shared_tb.TitreBoursierDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "titres-boursiers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TitreBoursier {

    @Id
    private String mnemo;

    @Field("company")
    private String company;

    @Field("value")
    private float value;
}
