package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.model.EspacioDisponible;
import edu.co.uniquindio.demo.service.EspacioDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/espacios")
public class EspacioDisponibleController {

    @Autowired
    private EspacioDisponibleService espacioDisponibleService;

    @PostMapping
    public ResponseEntity<EspacioDisponible> crearEspacio(@RequestBody EspacioDisponible espacio) {
        return ResponseEntity.ok(espacioDisponibleService.crearEspacio(espacio));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<EspacioDisponible>> listarDisponibles(@RequestParam String inmuebleId) {
        return ResponseEntity.ok(espacioDisponibleService.listarDisponiblesPorInmueble(inmuebleId));
    }
}
