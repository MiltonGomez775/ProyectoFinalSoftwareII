package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Usuario;
import edu.co.uniquindio.demo.repository.UsuarioRepository;
import edu.co.uniquindio.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    // Mapa temporal de tokens (solo para pruebas, en prod debería ser Redis o DB)
    private final Map<String, String> tokensRecuperacion = new HashMap<>();

    public String generarTokenRecuperacion(String correo) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isEmpty()) return null;

        String token = UUID.randomUUID().toString();
        tokensRecuperacion.put(token, correo);
        return token;
    }

    public boolean restablecerContrasena(String token, String nuevaContrasena) {
        String correo = tokensRecuperacion.get(token);
        if (correo == null) return false;

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isEmpty()) return false;

        Usuario usuario = usuarioOpt.get();
        usuario.setContrasena(nuevaContrasena);  // Aquí podrías encriptar
        usuarioRepository.save(usuario);

        tokensRecuperacion.remove(token); // token ya no válido
        return true;
    }

    // tu método existente
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).orElse(null);
    }

    public String login(String correo, String contraseña) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getContrasena().equals(contraseña)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Llamamos directamente al método estático
        return JwtUtil.generarToken(usuario.getId(), usuario.getRol());
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
}

}
