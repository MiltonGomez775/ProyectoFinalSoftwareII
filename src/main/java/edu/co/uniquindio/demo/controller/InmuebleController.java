package edu.co.uniquindio.demo.controller;

import edu.co.uniquindio.demo.dto.InmuebleDetalle;
import edu.co.uniquindio.demo.model.Inmueble;
import edu.co.uniquindio.demo.service.InmuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de inmuebles.
 * Permite crear, editar, listar, filtrar y consultar los detalles de un inmueble específico.
 */
@RestController
@RequestMapping("/api/inmuebles")
public class InmuebleController {

    @Autowired
    private InmuebleService inmuebleService;

    /**
     * Endpoint para publicar un nuevo inmueble.
     * 
     * @param inmueble objeto con los datos del inmueble a registrar
     * @return Inmueble guardado en la base de datos
     */
    @PostMapping
    public ResponseEntity<Inmueble> publicarInmueble(@RequestBody Inmueble inmueble) {
        return ResponseEntity.ok(inmuebleService.publicarInmueble(inmueble));
    }

    /**
     * Endpoint para editar la información de un inmueble existente.
     * 
     * @param id identificador del inmueble a editar
     * @param inmuebleActualizado objeto con los nuevos datos
     * @return inmueble actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inmueble> editarInmueble(
            @PathVariable String id,
            @RequestBody Inmueble inmuebleActualizado) {
        return ResponseEntity.ok(inmuebleService.editarInmueble(id, inmuebleActualizado));
    }

    /**
     * Obtiene un inmueble por su identificador.
     * Devuelve un código 404 si el inmueble no existe.
     *
     * @param id identificador único del inmueble
     * @return inmueble encontrado o notFound()
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inmueble> obtenerInmueblePorId(@PathVariable String id) {
        Inmueble inmueble = inmuebleService.obtenerInmueblePorId(id);
        if (inmueble != null) {
            return ResponseEntity.ok(inmueble);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retorna una lista con todos los inmuebles registrados.
     *
     * @return lista completa de inmuebles
     */
    @GetMapping
    public ResponseEntity<List<Inmueble>> listarInmuebles() {
        return ResponseEntity.ok(inmuebleService.listarInmuebles());
    }

    /**
     * Permite filtrar inmuebles según criterios opcionales.
     * 
     * @param direccion parte del texto de dirección a buscar
     * @param minPrecio valor mínimo del canon
     * @param maxPrecio valor máximo del canon
     * @param estado estado del inmueble (ej: disponible, arrendado)
     * @return lista de inmuebles que cumplen con los filtros
     */
    @GetMapping("/filtrar")
    public ResponseEntity<List<Inmueble>> filtrarInmuebles(
        @RequestParam(required = false) String direccion,
        @RequestParam(required = false) Double minPrecio,
        @RequestParam(required = false) Double maxPrecio,
        @RequestParam(required = false) String estado) {

        // Filtrado de inmuebles según los parámetros recibidos
        List<Inmueble> resultados = inmuebleService.filtrarInmuebles(direccion, minPrecio, maxPrecio, estado);
        return ResponseEntity.ok(resultados);
    }

    /**
     * Obtiene un DTO con los detalles extendidos de un inmueble.
     * 
     * @param id identificador del inmueble
     * @return detalle del inmueble o notFound() si no existe
     */
    @GetMapping("/detalles/{id}")
    public ResponseEntity<InmuebleDetalle> obtenerDetalleInmueble(@PathVariable String id) {
        try {
            InmuebleDetalle detalle = inmuebleService.obtenerDetalleInmueblePorId(id);
            return ResponseEntity.ok(detalle);
        } catch (RuntimeException e) {
            // Si no se encuentra el inmueble, retornar 404
            return ResponseEntity.notFound().build();
        }
    }

}
