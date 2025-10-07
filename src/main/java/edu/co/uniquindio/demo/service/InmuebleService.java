package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Inmueble;
import edu.co.uniquindio.demo.repository.InmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Publica (guarda) un nuevo inmueble en la base de datos con estado "disponible".
     *
     * Este método establece por defecto el estado del inmueble como "disponible"
     * antes de guardarlo en la base de datos.
     *
     * @param inmueble objeto {@link Inmueble} que contiene la información a registrar
     * @return el inmueble guardado con su identificador asignado
     */
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
}
