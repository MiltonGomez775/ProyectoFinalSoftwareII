package edu.co.uniquindio.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import edu.co.uniquindio.demo.model.Inmueble;

public interface InmuebleRepository extends MongoRepository<Inmueble, String> {

    List<Inmueble> findByEstado(String estado);
    Optional<Inmueble> findByDireccion(String direccion);
}
