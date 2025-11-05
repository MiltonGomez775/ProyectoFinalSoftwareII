package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.EspacioDisponible;
import edu.co.uniquindio.demo.repository.EspacioDisponibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EspacioDisponibleService {

    @Autowired
    private EspacioDisponibleRepository espacioDisponibleRepository;

    public EspacioDisponible crearEspacio(EspacioDisponible espacio) {
        espacio.setDisponible(true);
        return espacioDisponibleRepository.save(espacio);
    }

    public List<EspacioDisponible> listarDisponiblesPorInmueble(String inmuebleId) {
        return espacioDisponibleRepository.findByInmuebleIdAndDisponibleTrue(inmuebleId);
    }

    public void marcarComoNoDisponible(String id) {
        EspacioDisponible espacio = espacioDisponibleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado"));
        espacio.setDisponible(false);
        espacioDisponibleRepository.save(espacio);
    }
}
