package edu.co.uniquindio.demo.repository;

import edu.co.uniquindio.demo.model.EspacioDisponible;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EspacioDisponibleRepository extends MongoRepository<EspacioDisponible, String> {
    List<EspacioDisponible> findByInmuebleIdAndDisponibleTrue(String inmuebleId);
}
