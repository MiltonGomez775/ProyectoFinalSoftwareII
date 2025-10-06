package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.dto.LoginRequest;
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
    response.put("mensaje", "Login exitoso");

    return ResponseEntity.ok(response);
}

}
