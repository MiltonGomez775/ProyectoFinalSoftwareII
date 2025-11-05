package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.dto.CitaResponse;
import edu.co.uniquindio.demo.model.Cita;
import edu.co.uniquindio.demo.model.EspacioDisponible;
import edu.co.uniquindio.demo.repository.CitaRepository;
import edu.co.uniquindio.demo.repository.EspacioDisponibleRepository;
import edu.co.uniquindio.demo.repository.InmuebleRepository;
import edu.co.uniquindio.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la gestión de citas dentro del sistema inmobiliario.
 * Permite crear, confirmar, cancelar y listar citas según distintos criterios.
 *
 * Esta clase se relaciona directamente con la historia de usuario:
 * “Gestionar agenda” (vista de solicitudes pendientes, asignar asesor y fecha definitiva,
 * agenda de citas por asesor).
 */
@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private EspacioDisponibleRepository espacioDisponibleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InmuebleRepository inmuebleRepository;

    /**
     * Crea una nueva cita a partir de un espacio disponible seleccionado.
     * Marca el espacio como no disponible una vez asignado.
     *
     * @param cita objeto con la información básica de la cita a registrar.
     * @return la cita guardada en base de datos.
     */
    public Cita crearCita(Cita cita) {
        // Verifica que el espacio exista
        EspacioDisponible espacio = espacioDisponibleRepository.findById(cita.getEspacioId())
                .orElseThrow(() -> new RuntimeException("El espacio no existe"));

        // Verifica disponibilidad
        if (!espacio.isDisponible()) {
            throw new RuntimeException("El espacio no está disponible");
        }

        // Copia los datos del espacio a la cita
        cita.setPropietarioId(espacio.getPropietarioId());
        cita.setInmuebleId(espacio.getInmuebleId());
        cita.setFecha(espacio.getFecha());
        cita.setHoraInicio(espacio.getHoraInicio());
        cita.setHoraFin(espacio.getHoraFin());
        cita.setEstado("pendiente");

        // Actualiza el estado del espacio
        espacio.setDisponible(false);
        espacioDisponibleRepository.save(espacio);

        return citaRepository.save(cita);
    }

    /**
     * Lista todas las citas asociadas a un cliente específico.
     *
     * @param clienteId identificador del cliente.
     * @return lista de citas del cliente.
     */
    public List<Cita> listarCitasPorCliente(String clienteId) {
        return citaRepository.findByClienteId(clienteId);
    }

    /**
     * Lista todas las citas asociadas a un propietario.
     *
     * @param propietarioId identificador del propietario.
     * @return lista de citas del propietario.
     */
    public List<Cita> listarCitasPorPropietario(String propietarioId) {
        return citaRepository.findByPropietarioId(propietarioId);
    }

    /**
     * Cambia el estado de una cita a "confirmada".
     *
     * @param id identificador de la cita.
     * @return cita actualizada con estado confirmado.
     */
    public Cita confirmarCita(String id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado("confirmada");
        return citaRepository.save(cita);
    }

    /**
     * Cancela una cita y libera el espacio asociado.
     *
     * @param id identificador de la cita.
     * @return cita actualizada con estado cancelado.
     */
    public Cita cancelarCita(String id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado("cancelada");

        // Libera el espacio asociado si existe
        EspacioDisponible espacio = espacioDisponibleRepository.findById(cita.getEspacioId())
                .orElse(null);
        if (espacio != null) {
            espacio.setDisponible(true);
            espacioDisponibleRepository.save(espacio);
        }

        return citaRepository.save(cita);
    }

    /**
     * Devuelve todas las citas que se encuentran en estado "pendiente".
     * Incluye información del cliente, propietario e inmueble.
     *
     * @return lista de citas pendientes con información enriquecida.
     */
    public List<CitaResponse> listarPendientes() {
        List<Cita> citas = citaRepository.findByEstado("pendiente");

        return citas.stream().map(cita -> {
            CitaResponse response = new CitaResponse();
            response.setId(cita.getId());
            response.setClienteId(cita.getClienteId());
            response.setPropietarioId(cita.getPropietarioId());
            response.setInmuebleId(cita.getInmuebleId());
            response.setEspacioId(cita.getEspacioId());
            response.setFecha(cita.getFecha());
            response.setHoraInicio(cita.getHoraInicio());
            response.setHoraFin(cita.getHoraFin());
            response.setEstado(cita.getEstado());

            // Agrega nombres descriptivos
            usuarioRepository.findById(cita.getClienteId())
                    .ifPresent(u -> response.setNombreCliente(u.getNombre()));

            usuarioRepository.findById(cita.getPropietarioId())
                    .ifPresent(u -> response.setNombrePropietario(u.getNombre()));

            inmuebleRepository.findById(cita.getInmuebleId())
                    .ifPresent(inmueble -> response.setNombreInmueble(inmueble.getDireccion()));

            return response;
        }).toList();
    }

    /**
     * Devuelve todas las citas registradas en el sistema, sin importar su estado.
     * Incluye información del cliente, propietario e inmueble.
     *
     * @return lista completa de citas con detalles asociados.
     */
    public List<CitaResponse> listarTodas() {
        List<Cita> citas = citaRepository.findAll();

        return citas.stream().map(cita -> {
            CitaResponse response = new CitaResponse();
            response.setId(cita.getId());
            response.setClienteId(cita.getClienteId());
            response.setPropietarioId(cita.getPropietarioId());
            response.setInmuebleId(cita.getInmuebleId());
            response.setEspacioId(cita.getEspacioId());
            response.setFecha(cita.getFecha());
            response.setHoraInicio(cita.getHoraInicio());
            response.setHoraFin(cita.getHoraFin());
            response.setEstado(cita.getEstado());

            usuarioRepository.findById(cita.getClienteId())
                    .ifPresent(u -> response.setNombreCliente(u.getNombre()));

            usuarioRepository.findById(cita.getPropietarioId())
                    .ifPresent(u -> response.setNombrePropietario(u.getNombre()));

            inmuebleRepository.findById(cita.getInmuebleId())
                    .ifPresent(inmueble -> response.setNombreInmueble(inmueble.getDireccion()));

            return response;
        }).toList();
    }

}

