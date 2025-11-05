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

/**
 * Servicio encargado de manejar la lógica de autenticación de usuarios.
 *
 * <p>Esta clase valida las credenciales de acceso (correo y contraseña)
 * contra los registros almacenados en la base de datos. Si la autenticación
 * es exitosa, genera un token JWT que podrá ser utilizado para autorizar
 * las solicitudes posteriores del usuario.</p>
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    /**
     * Repositorio que permite acceder a los datos de los usuarios registrados
     * en la base de datos.
     */
    private final UsuarioRepository usuarioRepository;

yorgen
    /**
     * Realiza el proceso de inicio de sesión validando las credenciales de usuario.
     *
     * <p>Primero busca el usuario por su correo electrónico. Si no se encuentra,
     * lanza una excepción. Luego compara la contraseña proporcionada con la
     * almacenada en la base de datos. Si coincide, genera y devuelve un token JWT
     * que contiene la información básica del usuario autenticado (ID y rol).</p>
     *
     * @param correo correo electrónico del usuario que intenta iniciar sesión
     * @param contraseña contraseña en texto plano proporcionada por el usuario
     * @return token JWT generado para el usuario autenticado
     * @throws RuntimeException si el usuario no existe o la contraseña es incorrecta
     */
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
 main
    public String login(String correo, String contraseña) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getContrasena().equals(contraseña)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Genera y retorna el token JWT con los datos del usuario
        return JwtUtil.generarToken(usuario.getId(), usuario.getRol());
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
}

}
