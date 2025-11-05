package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.dto.InmuebleDetalle;
import edu.co.uniquindio.demo.model.Inmueble;
import edu.co.uniquindio.demo.repository.InmuebleRepository;
import edu.co.uniquindio.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio encargado de manejar la lógica de negocio relacionada con los inmuebles.
 * 
 * Esta clase actúa como intermediaria entre el controlador (capa web)
 * y el repositorio (capa de persistencia), encapsulando las operaciones
 * CRUD sobre la entidad {@link Inmueble}.
 */
@Service
public class InmuebleService {

    /**
     * Inyección del repositorio que permite acceder a la base de datos
     * para realizar operaciones sobre la entidad {@link Inmueble}.
     */
    @Autowired
    private InmuebleRepository inmuebleRepository;


yorgen
    /**
     * Publica (guarda) un nuevo inmueble en la base de datos con estado "disponible".
     *
     * Este método establece por defecto el estado del inmueble como "disponible"
     * antes de guardarlo en la base de datos.
     *
     * @param inmueble objeto {@link Inmueble} que contiene la información a registrar
     * @return el inmueble guardado con su identificador asignado
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

main
    public Inmueble publicarInmueble(Inmueble inmueble) {
        inmueble.setEstado("disponible");
        return inmuebleRepository.save(inmueble);
    }

    /**
     * Edita los datos de un inmueble existente.
     *
     * Busca el inmueble por su identificador; si no lo encuentra, lanza una excepción.
     * Si existe, actualiza los campos con los valores del objeto recibido y guarda los cambios.
     *
     * @param id identificador único del inmueble a editar
     * @param inmuebleActualizado objeto {@link Inmueble} con los nuevos valores
     * @return el inmueble actualizado
     * @throws RuntimeException si el inmueble no existe en la base de datos
     */
    public Inmueble editarInmueble(String id, Inmueble inmuebleActualizado) {
        Inmueble inmueble = inmuebleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inmueble no encontrado"));

        inmueble.setDireccion(inmuebleActualizado.getDireccion());
        inmueble.setArea(inmuebleActualizado.getArea());
        inmueble.setCanon(inmuebleActualizado.getCanon());
        inmueble.setAdministracionIncluida(inmuebleActualizado.isAdministracionIncluida());
        inmueble.setValorAdministracion(inmuebleActualizado.getValorAdministracion());
        inmueble.setDescripcion(inmuebleActualizado.getDescripcion());
        inmueble.setEstado(inmuebleActualizado.getEstado());
        
        return inmuebleRepository.save(inmueble);
    }

    /**
     * Obtiene la lista completa de inmuebles registrados en la base de datos.
     *
     * Este método se utiliza para mostrar todos los inmuebles disponibles,
     * independientemente de su estado (disponible, arrendado, etc.).
     *
     * @return lista de objetos {@link Inmueble} registrados en la base de datos
     */
    public List<Inmueble> listarInmuebles() {
        return inmuebleRepository.findAll();
    }

    public Inmueble obtenerInmueblePorId(String id) {
        return inmuebleRepository.findById(id).orElse(null);
    }

    public List<Inmueble> filtrarInmuebles(String direccion, Double minPrecio, Double maxPrecio, String estado) {
    List<Inmueble> todos = inmuebleRepository.findAll();

    return todos.stream()
            .filter(i -> direccion == null || i.getDireccion().toLowerCase().contains(direccion.toLowerCase()))
            .filter(i -> minPrecio == null || i.getCanon() >= minPrecio)
            .filter(i -> maxPrecio == null || i.getCanon() <= maxPrecio)
            .filter(i -> estado == null || i.getEstado().equalsIgnoreCase(estado))
            .toList();
    }     

    public InmuebleDetalle obtenerDetalleInmueblePorId(String id) {
        Inmueble inmueble = inmuebleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inmueble no encontrado"));

        InmuebleDetalle dto = new InmuebleDetalle();
        dto.setId(inmueble.getId());
        dto.setDireccion(inmueble.getDireccion());
        dto.setArea(inmueble.getArea());
        dto.setCanon(inmueble.getCanon());
        dto.setAdministracionIncluida(inmueble.isAdministracionIncluida());
        dto.setValorAdministracion(inmueble.getValorAdministracion());
        dto.setDescripcion(inmueble.getDescripcion());
        dto.setEstado(inmueble.getEstado());

        // traer el nombre del propietario
        String propietarioNombre = usuarioRepository.findById(inmueble.getPropietarioId())
                                    .map(p -> p.getNombre())
                                    .orElse("Desconocido");
        dto.setPropietarioNombre(propietarioNombre);

        return dto;
    }

}
