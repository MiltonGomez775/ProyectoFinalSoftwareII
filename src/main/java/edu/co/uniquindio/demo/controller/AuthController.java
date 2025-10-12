package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.dto.LoginRequest;
import edu.co.uniquindio.demo.dto.RecuperarRequest;
import edu.co.uniquindio.demo.dto.RestablecerRequest;
import edu.co.uniquindio.demo.model.Usuario;
import edu.co.uniquindio.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sesiones")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
    String token = authService.login(loginRequest.getCorreo(), loginRequest.getContrasena());

    // Aquí buscas el usuario por correo
    Usuario usuario = authService.obtenerUsuarioPorCorreo(loginRequest.getCorreo());

    Map<String, String> response = new HashMap<>();
    response.put("token", token);
    response.put("usuarioId", usuario.getId());
    response.put("rol", usuario.getRol());
    response.put("mensaje", "Login exitoso");

    return ResponseEntity.ok(response);
    }

    @PostMapping("/recuperar")
public ResponseEntity<Map<String, String>> recuperar(@RequestBody RecuperarRequest request) {
    String token = authService.generarTokenRecuperacion(request.getCorreo());
    
    Map<String, String> response = new HashMap<>();
    if (token != null) {
        response.put("mensaje", "Se generó el token de recuperación correctamente");
        response.put("token", token);
        return ResponseEntity.ok(response);
    } else {
        response.put("mensaje", "No existe un usuario con ese correo");
        return ResponseEntity.badRequest().body(response);
    }
}

@PostMapping("/restablecer")
public ResponseEntity<Map<String, String>> restablecer(@RequestBody RestablecerRequest request) {
    boolean actualizado = authService.restablecerContrasena(request.getToken(), request.getNuevaContrasena());

    Map<String, String> response = new HashMap<>();
    if (actualizado) {
        response.put("mensaje", "Contraseña actualizada correctamente");
        return ResponseEntity.ok(response);
    } else {
        response.put("mensaje", "Token inválido o expirado");
        return ResponseEntity.badRequest().body(response);
    }
}

}
