package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.model.Inmueble;
import edu.co.uniquindio.demo.service.InmuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    /**
     * US-03 — Publicar/editar/listar inmuebles.
     * Endpoints clave de la HU:
     *  - POST /api/inmuebles   (publicar/crear)
     *  - PUT  /api/inmuebles/{id} (editar)
     *  - GET  /api/inmuebles   (listar)
     */

@RestController
@RequestMapping("/api/inmuebles")
public class InmuebleController {

    @Autowired
    private InmuebleService inmuebleService;

    /** US-03: Publica (crea) un inmueble. */
    @PostMapping
    public ResponseEntity<Inmueble> publicarInmueble(@RequestBody Inmueble inmueble) {
        return ResponseEntity.ok(inmuebleService.publicarInmueble(inmueble));
    }
    
    /** US-03: Edita un inmueble por su ID. */
    @PutMapping("/{id}")
    public ResponseEntity<Inmueble> editarInmueble(
            @PathVariable String id,
            @RequestBody Inmueble inmuebleActualizado) {
        return ResponseEntity.ok(inmuebleService.editarInmueble(id, inmuebleActualizado));
    }
    
    /** US-03: Lista todos los inmuebles. */
    @GetMapping
    public ResponseEntity<List<Inmueble>> listarInmuebles() {
        return ResponseEntity.ok(inmuebleService.listarInmuebles());
    }
}
