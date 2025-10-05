package edu.co.uniquindio.demo.repository;

import edu.co.uniquindio.demo.model.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio encargado de las operaciones CRUD para la entidad {@link Rol}.
 * Extiende de {@link MongoRepository}, lo que permite interactuar con la base de datos MongoDB
 * sin necesidad de implementar los métodos básicos.
 */
@Repository
public interface RolRepository extends MongoRepository<Rol, String> {

    /**
     * Busca un rol en la base de datos por su nombre.
     *
     * @param nombre nombre del rol a buscar.
     * @return un {@link Optional} que contiene el rol si existe.
     */
    Optional<Rol> findByNombre(String nombre);
}