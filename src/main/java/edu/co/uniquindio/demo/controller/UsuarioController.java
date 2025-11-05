package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.model.Usuario;
import edu.co.uniquindio.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints relacionados con la gestión de usuarios.
 * A través de esta clase se manejan las operaciones CRUD y de cambio de roles.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param usuario Objeto con la información del usuario a registrar
     * @return Usuario creado en la base de datos
     */
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    /**
     * Endpoint para listar todos los usuarios registrados.
     *
     * @return Lista de usuarios en formato JSON
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    /**
     * Endpoint para cambiar el rol de un usuario.
     *
     * @param id identificador del usuario
     * @param nuevoRol nuevo rol a asignar
     * @return Usuario actualizado con su nuevo rol
     */
    @PutMapping("/{id}/rol")
    public ResponseEntity<Usuario> cambiarRol(
            @PathVariable String id,
            @RequestParam String nuevoRol) {
        return ResponseEntity.ok(usuarioService.cambiarRol(id, nuevoRol));
    }

    /**
     * Endpoint para obtener un usuario por su ID.
     * Nota: La URL contiene doble nivel "/usuarios/{id}" para mayor claridad semántica.
     *
     * @param id identificador único del usuario
     * @return Detalle del usuario correspondiente
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable String id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    /**
     * Endpoint para listar únicamente los usuarios con rol PROPIETARIO.
     *
     * @return Lista filtrada de usuarios propietarios
     */
    @GetMapping("/propietarios")
    public ResponseEntity<List<Usuario>> listarPropietarios() {
        return ResponseEntity.ok(usuarioService.listarPropietarios());
    }

    // TODO: En versiones futuras se podrían agregar endpoints de actualización y eliminación.
    // Ejemplo: /actualizar/{id}, /eliminar/{id}
}
