package fr.miage.producteur.dao.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "titresBoursiers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TitreBoursier {

    @Id
    private String mnemo;
}
