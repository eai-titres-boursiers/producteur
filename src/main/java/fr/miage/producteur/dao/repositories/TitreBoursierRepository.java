package fr.miage.producteur.dao.repositories;

import fr.miage.producteur.dao.models.TitreBoursier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitreBoursierRepository extends MongoRepository<TitreBoursier, String> {
}
