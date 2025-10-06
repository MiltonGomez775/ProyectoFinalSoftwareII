package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.model.Rol;
import edu.co.uniquindio.demo.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST encargado de gestionar las operaciones relacionadas con los roles del sistema.
 * Permite crear nuevos roles y listar los existentes.
 *
 * @author
 * @version 1.1
 */
@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    /**
     * Constructor con inyección de dependencias explícita.
     * @param rolService servicio que maneja la lógica de negocio de roles
     */
    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    /**
     * Endpoint para registrar un nuevo rol en el sistema.
     * @param rol Objeto Rol recibido en formato JSON
     * @return Rol creado
     */
    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.guardarRol(rol)); // método actualizado
    }

    /**
     * Endpoint para listar todos los roles disponibles en el sistema.
     * @return Lista de roles existentes
     */
    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolService.obtenerTodos()); // método actualizado
    }
}
