package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.model.Inmueble;
import edu.co.uniquindio.demo.service.InmuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inmuebles")
public class InmuebleController {

    @Autowired
    private InmuebleService inmuebleService;

    @PostMapping
    public ResponseEntity<Inmueble> publicarInmueble(@RequestBody Inmueble inmueble) {
        return ResponseEntity.ok(inmuebleService.publicarInmueble(inmueble));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inmueble> editarInmueble(
            @PathVariable String id,
            @RequestBody Inmueble inmuebleActualizado) {
        return ResponseEntity.ok(inmuebleService.editarInmueble(id, inmuebleActualizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inmueble> obtenerInmueblePorId(@PathVariable String id) {
        Inmueble inmueble = inmuebleService.obtenerInmueblePorId(id);
        if (inmueble != null) {
            return ResponseEntity.ok(inmueble);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Inmueble>> listarInmuebles() {
        return ResponseEntity.ok(inmuebleService.listarInmuebles());
    }

}
