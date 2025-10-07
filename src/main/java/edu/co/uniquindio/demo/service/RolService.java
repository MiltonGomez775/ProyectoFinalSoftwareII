package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Rol;
import edu.co.uniquindio.demo.repository.RolRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que maneja la lógica de negocio relacionada con los roles del sistema.
 * 
 * Esta clase actúa como un intermediario entre el controlador (controlador REST o MVC)
 * y la capa de acceso a datos (repositorio). Su objetivo principal es aislar la lógica
 * de persistencia y proveer métodos específicos para gestionar los roles.
 */
@Service
public class RolService {

    /**
     * Dependencia del repositorio que permite acceder a los datos
     * de la entidad Rol en la base de datos.
     */
    private final RolRepository rolRepository;

    /**
     * Constructor que inyecta el repositorio de roles.
     * 
     * @param rolRepository repositorio encargado de las operaciones CRUD sobre Rol
     */
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /**
     * Guarda un nuevo rol en la base de datos.
     * 
     * Esta función se utiliza cuando se necesita registrar un nuevo rol en el sistema.
     * Internamente usa el método {@code save()} del repositorio para persistir el objeto.
     * 
     * @param rol Objeto {@link Rol} que contiene la información del rol a guardar
     * @return El objeto {@link Rol} guardado con su ID generado por la base de datos
     */
    public Rol guardarRol(Rol rol) {
        return this.rolRepository.save(rol);
    }

    /**
     * Obtiene todos los roles registrados en la base de datos.
     * 
     * Este método se utiliza generalmente para listar los roles disponibles,
     * por ejemplo en una vista de administración o en un endpoint REST.
     * Internamente usa el método {@code findAll()} del repositorio.
     * 
     * @return Una lista de objetos {@link Rol} con todos los registros existentes
     */
    public List<Rol> obtenerTodos() {
        return this.rolRepository.findAll();
    }
}
