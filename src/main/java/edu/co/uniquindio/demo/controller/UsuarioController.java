package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.model.Usuario;
import edu.co.uniquindio.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PutMapping("/{id}/rol")
    public ResponseEntity<Usuario> cambiarRol(
            @PathVariable String id,
            @RequestParam String nuevoRol) {
        return ResponseEntity.ok(usuarioService.cambiarRol(id, nuevoRol));
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable String id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
}

    @GetMapping("/propietarios")
    public ResponseEntity<List<Usuario>> listarPropietarios() {
        return ResponseEntity.ok(usuarioService.listarPropietarios());
    }

}
