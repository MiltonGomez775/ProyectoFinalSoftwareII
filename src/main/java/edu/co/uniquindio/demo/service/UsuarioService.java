package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Usuario;
import edu.co.uniquindio.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los usuarios.
 * Incluye operaciones CRUD y utilidades como cambio de rol o filtrado de propietarios.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra un nuevo usuario en la base de datos.
     * Se valida que el correo no esté ya registrado antes de guardar.
     *
     * @param usuario objeto con los datos del nuevo usuario
     * @return el usuario guardado
     */
    public Usuario registrarUsuario(Usuario usuario) {
        // Validar correo único
        Optional<Usuario> existente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existente.isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Estado y rol inicial por defecto
        usuario.setEstado("activo");
        usuario.setRol("interesado");

        // Guardar y retornar el nuevo usuario
        return usuarioRepository.save(usuario);
    }

    /**
     * Retorna una lista con todos los usuarios del sistema.
     *
     * @return lista de usuarios registrados
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Cambia el rol de un usuario dado su ID.
     *
     * @param usuarioId identificador del usuario
     * @param nuevoRol  rol nuevo a asignar
     * @return usuario actualizado con el nuevo rol
     */
    public Usuario cambiarRol(String usuarioId, String nuevoRol) {
        // Buscar el usuario por ID, o lanzar excepción si no existe
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // Actualizar el rol del usuario
        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene un usuario por su ID, lanzando error si no se encuentra.
     *
     * @param id identificador del usuario
     * @return usuario correspondiente al ID
     */
    public Usuario obtenerUsuarioPorId(String id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        return usuarioOpt.get();
    }

    /**
     * Retorna una lista de todos los usuarios con rol "PROPIETARIO".
     *
     * @return lista de usuarios propietarios
     */
    public List<Usuario> listarPropietarios() {
        return usuarioRepository.findAll()
                .stream()
                // Filtrar usuarios cuyo rol sea propietario (sin importar mayúsculas/minúsculas)
                .filter(usuario -> "PROPIETARIO".equalsIgnoreCase(usuario.getRol()))
                .collect(Collectors.toList());
    }

}
