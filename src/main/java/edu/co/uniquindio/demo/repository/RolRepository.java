package edu.co.uniquindio.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.co.uniquindio.demo.model.Rol;

public interface RolRepository extends MongoRepository<Rol, String> {
    Optional<Rol> findByNombre(String nombre);
}