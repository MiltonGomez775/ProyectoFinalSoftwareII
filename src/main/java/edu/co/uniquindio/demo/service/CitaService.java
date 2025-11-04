package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Cita;
import edu.co.uniquindio.demo.model.EspacioDisponible;
import edu.co.uniquindio.demo.repository.CitaRepository;
import edu.co.uniquindio.demo.repository.EspacioDisponibleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private EspacioDisponibleRepository espacioDisponibleRepository;

    public Cita crearCita(Cita cita) {
        EspacioDisponible espacio = espacioDisponibleRepository.findById(cita.getEspacioId())
                .orElseThrow(() -> new RuntimeException("El espacio no existe"));

        if (!espacio.isDisponible()) {
            throw new RuntimeException("El espacio no está disponible");
        }

        // Copiar datos del espacio
        cita.setPropietarioId(espacio.getPropietarioId());
        cita.setInmuebleId(espacio.getInmuebleId());
        cita.setFecha(espacio.getFecha());
        cita.setHoraInicio(espacio.getHoraInicio());
        cita.setHoraFin(espacio.getHoraFin());
        cita.setEstado("pendiente");

        // Marcar el espacio como ocupado
        espacio.setDisponible(false);
        espacioDisponibleRepository.save(espacio);

        return citaRepository.save(cita);
    }

    public List<Cita> listarCitasPorCliente(String clienteId) {
        return citaRepository.findByClienteId(clienteId);
    }

    public List<Cita> listarCitasPorPropietario(String propietarioId) {
        return citaRepository.findByPropietarioId(propietarioId);
    }

    public Cita confirmarCita(String id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado("confirmada");
        return citaRepository.save(cita);
    }

    public Cita cancelarCita(String id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado("cancelada");

        // Al cancelar, liberar el espacio
        EspacioDisponible espacio = espacioDisponibleRepository.findById(cita.getEspacioId())
                .orElse(null);
        if (espacio != null) {
            espacio.setDisponible(true);
            espacioDisponibleRepository.save(espacio);
        }

        return citaRepository.save(cita);
    }

    public List<Cita> listarPendientes() {
        return citaRepository.findByEstado("pendiente");
    }
}
