package edu.co.uniquindio.demo.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import edu.co.uniquindio.demo.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
}
