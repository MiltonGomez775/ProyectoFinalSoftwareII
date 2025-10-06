package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Rol;
import edu.co.uniquindio.demo.repository.RolRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /**
     * Guarda un nuevo rol en la base de datos.
     * @param rol Objeto Rol a registrar
     * @return Rol guardado
     */
    public Rol guardarRol(Rol rol) {
        return this.rolRepository.save(rol);
    }

    /**
     * Obtiene todos los roles registrados.
     * @return Lista de roles
     */
    public List<Rol> obtenerTodos() {
        return this.rolRepository.findAll();
    }
}

