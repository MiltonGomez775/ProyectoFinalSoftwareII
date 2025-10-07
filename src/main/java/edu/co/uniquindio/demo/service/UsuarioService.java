package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Usuario;
import edu.co.uniquindio.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de manejar la lógica de negocio relacionada con los usuarios.
 *
 * <p>Esta clase centraliza las operaciones principales sobre la entidad {@link Usuario},
 * incluyendo el registro de nuevos usuarios, la consulta de todos los registros y
 * el cambio de roles. Su propósito es aislar la lógica de negocio del controlador,
 * garantizando una arquitectura limpia.</p>
 */
@Service
public class UsuarioService {

    /**
     * Repositorio que permite el acceso a la base de datos para realizar
     * operaciones CRUD sobre la entidad {@link Usuario}.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra un nuevo usuario en la base de datos, asegurando que el correo sea único.
     *
     * <p>Antes de guardar el usuario, valida que no exista otro registro con el mismo correo.
     * Si ya existe, lanza una excepción. Si no, establece el estado inicial como
     * <strong>"activo"</strong> y el rol por defecto como <strong>"interesado"</strong>,
     * y luego lo guarda en la base de datos.</p>
     *
     * @param usuario objeto {@link Usuario} que contiene la información del nuevo usuario
     * @return el objeto {@link Usuario} guardado en la base de datos
     * @throws RuntimeException si el correo electrónico ya está registrado
     */
    public Usuario registrarUsuario(Usuario usuario) {
        // Validar correo único
        Optional<Usuario> existente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (existente.isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Estado y rol inicial
        usuario.setEstado("activo");
        usuario.setRol("interesado");

        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene la lista completa de usuarios registrados.
     *
     * <p>Este método retorna todos los usuarios almacenados en la base de datos,
     * sin aplicar filtros ni paginación. Es útil en paneles administrativos o listados generales.</p>
     *
     * @return una lista con todos los objetos {@link Usuario} registrados
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Cambia el rol de un usuario existente.
     *
     * <p>Busca el usuario por su identificador; si no lo encuentra, lanza una excepción.
     * Si existe, actualiza su rol con el nuevo valor y guarda los cambios.</p>
     *
     * @param usuarioId identificador único del usuario
     * @param nuevoRol nuevo rol que se desea asignar (por ejemplo, "administrador", "propietario", "arrendatario")
     * @return el objeto {@link Usuario} actualizado con el nuevo rol
     * @throws RuntimeException si el usuario no existe en la base de datos
     */
    public Usuario cambiarRol(String usuarioId, String nuevoRol) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setRol(nuevoRol);
        return usuarioRepository.save(usuario);
    }
}
