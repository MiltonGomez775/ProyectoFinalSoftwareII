package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.dto.CitaResponse;
import edu.co.uniquindio.demo.model.Cita;
import edu.co.uniquindio.demo.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST encargado de gestionar las operaciones relacionadas con las citas.
 * Proporciona endpoints para crear, confirmar, cancelar y listar citas
 * según distintos criterios (cliente, propietario o estado).
 */
@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    /**
     * Crea una nueva cita en el sistema.
     *
     * @param cita Objeto con la información de la cita a registrar.
     * @return Cita creada y almacenada en la base de datos.
     */
    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        // Se delega la creación de la cita al servicio correspondiente
        return ResponseEntity.ok(citaService.crearCita(cita));
    }

    /**
     * Confirma una cita existente.
     *
     * @param id Identificador único de la cita a confirmar.
     * @return Cita actualizada con estado confirmado.
     */
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Cita> confirmarCita(@PathVariable String id) {
        // Cambia el estado de la cita a "confirmada"
        return ResponseEntity.ok(citaService.confirmarCita(id));
    }

    /**
     * Cancela una cita previamente registrada.
     *
     * @param id Identificador único de la cita.
     * @return Cita actualizada con estado cancelado.
     */
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Cita> cancelarCita(@PathVariable String id) {
        // Actualiza el estado de la cita a "cancelada"
        return ResponseEntity.ok(citaService.cancelarCita(id));
    }

    /**
     * Obtiene la lista de citas asociadas a un cliente específico.
     *
     * @param clienteId Identificador del cliente.
     * @return Lista de citas correspondientes al cliente.
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Cita>> listarPorCliente(@PathVariable String clienteId) {
        return ResponseEntity.ok(citaService.listarCitasPorCliente(clienteId));
    }

    /**
     * Obtiene la lista de citas asociadas a un propietario.
     *
     * @param propietarioId Identificador del propietario.
     * @return Lista de citas correspondientes al propietario.
     */
    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<Cita>> listarPorPropietario(@PathVariable String propietarioId) {
        return ResponseEntity.ok(citaService.listarCitasPorPropietario(propietarioId));
    }

    /**
     * Lista todas las citas pendientes en el sistema.
     *
     * @return Lista de citas con estado pendiente.
     */
    @GetMapping("/pendientes")
    public ResponseEntity<List<CitaResponse>> listarPendientes() {
        // Retorna únicamente las citas pendientes de confirmación o atención
        return ResponseEntity.ok(citaService.listarPendientes());
    }

    /**
     * Lista todas las citas registradas (independientemente de su estado).
     *
     * @return Lista completa de citas.
     */
    @GetMapping
    public ResponseEntity<List<CitaResponse>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodas());
    }


}
