package fr.miage.producteur.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitreBoursierRepository extends MongoRepository<TitreBoursierRepository, String> {
}
