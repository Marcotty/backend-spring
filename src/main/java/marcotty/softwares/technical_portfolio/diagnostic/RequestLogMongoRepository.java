package marcotty.softwares.technical_portfolio.diagnostic;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestLogMongoRepository extends MongoRepository<RequestLogDocument, String> {

    // Requête dérivée : Spring Data génère l'implémentation à partir du nom de la méthode,
    // exactement comme vu avec JpaRepository — même principe, moteur différent derrière.
    List<RequestLogDocument> findAllByOrderByHorodatageDesc();
}