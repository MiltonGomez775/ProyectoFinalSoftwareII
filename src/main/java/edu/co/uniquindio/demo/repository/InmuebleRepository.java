package edu.co.uniquindio.demo.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import edu.co.uniquindio.demo.model.Inmueble;
import org.bson.types.ObjectId;

public interface InmuebleRepository extends MongoRepository<Inmueble, String> {
    List<Inmueble> findByEstado(String estado);
    List<Inmueble> findByPropietarioId(ObjectId propietarioId); //Consulta por propietario
} 
