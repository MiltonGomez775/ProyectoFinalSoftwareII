package edu.co.uniquindio.demo.repository;

import edu.co.uniquindio.demo.model.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CitaRepository extends MongoRepository<Cita, String> {
    List<Cita> findByClienteId(String clienteId);
    List<Cita> findByPropietarioId(String propietarioId);
    List<Cita> findByEstado(String estado);
}
