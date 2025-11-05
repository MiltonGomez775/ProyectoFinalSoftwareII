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
 * Servicio para la gestión de los inmuebles en el sistema.
 */

@Service
public class InmuebleService {

    @Autowired
    private InmuebleRepository inmuebleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Publica un nuevo inmueble en el sistema.
     * @param inmueble entidad del inmueble
     * @return inmueble guardado con estado "disponible"
     */
    public Inmueble publicarInmueble(Inmueble inmueble) {
        inmueble.setEstado("disponible");
        return inmuebleRepository.save(inmueble);
    }

    /**
     * Edita un inmueble existente.
     * @param id identificador del inmueble
     * @param inmuebleActualizado datos actualizados
     * @return inmueble modificado
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
     * Lista todos los inmuebles registrados.
     * @return lista de inmuebles
     */
    public List<Inmueble> listarInmuebles() {
        return inmuebleRepository.findAll();
    }

    /**
     * Obtiene un inmueble por su ID.
     * @param id identificador del inmueble
     * @return inmueble o null si no existe
     */
    public Inmueble obtenerInmueblePorId(String id) {
        return inmuebleRepository.findById(id).orElse(null);
    }

    /**
     * Filtra inmuebles según dirección, precio y estado.
     * @param direccion dirección parcial o completa
     * @param minPrecio precio mínimo
     * @param maxPrecio precio máximo
     * @param estado estado del inmueble
     * @return lista de inmuebles filtrados
     */
    public List<Inmueble> filtrarInmuebles(String direccion, Double minPrecio, Double maxPrecio, String estado) {
        List<Inmueble> todos = inmuebleRepository.findAll();

        return todos.stream()
                .filter(i -> direccion == null || i.getDireccion().toLowerCase().contains(direccion.toLowerCase()))
                .filter(i -> minPrecio == null || i.getCanon() >= minPrecio)
                .filter(i -> maxPrecio == null || i.getCanon() <= maxPrecio)
                .filter(i -> estado == null || i.getEstado().equalsIgnoreCase(estado))
                .toList();
    }

    /**
     * Obtiene el detalle completo de un inmueble.
     * @param id identificador del inmueble
     * @return DTO con la información detallada
     */
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

        // Se obtiene el nombre del propietario asociado al inmueble
        String propietarioNombre = usuarioRepository.findById(inmueble.getPropietarioId())
                .map(p -> p.getNombre())
                .orElse("Desconocido");

        dto.setPropietarioNombre(propietarioNombre);

        return dto;
    }

}
