package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.model.EspacioDisponible;
import edu.co.uniquindio.demo.service.EspacioDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST que gestiona los endpoints relacionados con los espacios disponibles.
 * Permite registrar nuevos espacios y consultar los disponibles por inmueble.
 */
@RestController
@RequestMapping("/api/espacios")
public class EspacioDisponibleController {

    @Autowired
    private EspacioDisponibleService espacioDisponibleService;

    /**
     * Endpoint para crear un nuevo espacio disponible.
     *
     * @param espacio objeto con los datos del espacio a registrar
     * @return espacio guardado en la base de datos
     */
    @PostMapping
    public ResponseEntity<EspacioDisponible> crearEspacio(@RequestBody EspacioDisponible espacio) {
        // Invoca el servicio encargado de crear el espacio disponible
        return ResponseEntity.ok(espacioDisponibleService.crearEspacio(espacio));
    }

    /**
     * Endpoint para listar los espacios disponibles de un inmueble específico.
     *
     * @param inmuebleId identificador del inmueble
     * @return lista de espacios disponibles asociados al inmueble
     */
    @GetMapping("/disponibles")
    public ResponseEntity<List<EspacioDisponible>> listarDisponibles(@RequestParam String inmuebleId) {
        // Llama al servicio para obtener los espacios activos relacionados con el inmueble
        return ResponseEntity.ok(espacioDisponibleService.listarDisponiblesPorInmueble(inmuebleId));
    }

}