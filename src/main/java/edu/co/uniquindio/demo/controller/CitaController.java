package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.dto.CitaResponse;
import edu.co.uniquindio.demo.model.Cita;
import edu.co.uniquindio.demo.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST que gestiona las operaciones relacionadas con las citas.
 *
 * Permite crear, confirmar, cancelar y consultar citas asociadas a clientes,
 * propietarios o el listado general. Esta clase actúa como punto de entrada
 * para las solicitudes HTTP que manipulan información de la agenda.
 */
@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    /**
     * Crea una nueva cita en el sistema.
     *
     * @param cita objeto con la información de la cita a crear.
     * @return la cita creada con su identificador asignado.
     */
    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.crearCita(cita));
    }

    /**
     * Confirma una cita pendiente cambiando su estado a "confirmada".
     *
     * @param id identificador de la cita a confirmar.
     * @return la cita actualizada con el nuevo estado.
     */
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Cita> confirmarCita(@PathVariable String id) {
        return ResponseEntity.ok(citaService.confirmarCita(id));
    }

    /**
     * Cancela una cita existente cambiando su estado a "cancelada".
     *
     * @param id identificador de la cita a cancelar.
     * @return la cita actualizada con el nuevo estado.
     */
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Cita> cancelarCita(@PathVariable String id) {
        return ResponseEntity.ok(citaService.cancelarCita(id));
    }

    /**
     * Lista todas las citas asociadas a un cliente específico.
     *
     * @param clienteId identificador del cliente.
     * @return lista de citas registradas por ese cliente.
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Cita>> listarPorCliente(@PathVariable String clienteId) {
        return ResponseEntity.ok(citaService.listarCitasPorCliente(clienteId));
    }

    /**
     * Lista todas las citas asociadas a un propietario de inmuebles.
     *
     * @param propietarioId identificador del propietario.
     * @return lista de citas relacionadas con ese propietario.
     */
    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<Cita>> listarPorPropietario(@PathVariable String propietarioId) {
        return ResponseEntity.ok(citaService.listarCitasPorPropietario(propietarioId));
    }

    /**
     * Obtiene el listado de citas que están pendientes de confirmación.
     *
     * @return lista de citas pendientes (DTO con información resumida).
     */
    @GetMapping("/pendientes")
    public ResponseEntity<List<CitaResponse>> listarPendientes() {
        return ResponseEntity.ok(citaService.listarPendientes());
    }

    /**
     * Obtiene todas las citas registradas en el sistema.
     *
     * @return lista completa de citas en formato CitaResponse.
     */
    @GetMapping
    public ResponseEntity<List<CitaResponse>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodas());
    }

}

