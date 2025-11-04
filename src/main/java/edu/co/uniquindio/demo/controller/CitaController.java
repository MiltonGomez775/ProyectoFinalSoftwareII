package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.model.Cita;
import edu.co.uniquindio.demo.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.crearCita(cita));
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Cita> confirmarCita(@PathVariable String id) {
        return ResponseEntity.ok(citaService.confirmarCita(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Cita> cancelarCita(@PathVariable String id) {
        return ResponseEntity.ok(citaService.cancelarCita(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Cita>> listarPorCliente(@PathVariable String clienteId) {
        return ResponseEntity.ok(citaService.listarCitasPorCliente(clienteId));
    }

    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<Cita>> listarPorPropietario(@PathVariable String propietarioId) {
        return ResponseEntity.ok(citaService.listarCitasPorPropietario(propietarioId));
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Cita>> listarPendientes() {
        return ResponseEntity.ok(citaService.listarPendientes());
    }
}
