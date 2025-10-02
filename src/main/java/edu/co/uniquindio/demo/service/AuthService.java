package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Usuario;
import edu.co.uniquindio.demo.repository.UsuarioRepository;
import edu.co.uniquindio.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public String login(String correo, String contraseña) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getContrasena().equals(contraseña)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Llamamos directamente al método estático
        return JwtUtil.generarToken(usuario.getId(), usuario.getRol());
    }
}
