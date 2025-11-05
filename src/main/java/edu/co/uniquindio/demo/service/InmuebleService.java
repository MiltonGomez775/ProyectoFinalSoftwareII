package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.dto.InmuebleDetalle;
import edu.co.uniquindio.demo.model.Inmueble;
import edu.co.uniquindio.demo.repository.InmuebleRepository;
import edu.co.uniquindio.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InmuebleService {

    @Autowired
    private InmuebleRepository inmuebleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Inmueble publicarInmueble(Inmueble inmueble) {
        inmueble.setEstado("disponible");
        return inmuebleRepository.save(inmueble);
    }

    public Inmueble editarInmueble(String id, Inmueble inmuebleActualizado) {
        Inmueble inmueble = inmuebleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inmueble no encontrado"));

        inmueble.setDireccion(inmuebleActualizado.getDireccion());
        inmueble.setArea(inmuebleActualizado.getArea());
        inmueble.setCanon(inmuebleActualizado.getCanon());
        inmueble.setAdministracionIncluida(inmuebleActualizado.isAdministracionIncluida());
        inmueble.setValorAdministracion(inmuebleActualizado.getValorAdministracion());
        inmueble.setDescripcion(inmuebleActualizado.getDescripcion());
        inmueble.setEstado(inmuebleActualizado.getEstado());

        return inmuebleRepository.save(inmueble);
    }

    public List<Inmueble> listarInmuebles() {
        return inmuebleRepository.findAll();
    }

    public Inmueble obtenerInmueblePorId(String id) {
        return inmuebleRepository.findById(id).orElse(null);
    }

    public List<Inmueble> filtrarInmuebles(String direccion, Double minPrecio, Double maxPrecio, String estado) {
    List<Inmueble> todos = inmuebleRepository.findAll();

    return todos.stream()
            .filter(i -> direccion == null || i.getDireccion().toLowerCase().contains(direccion.toLowerCase()))
            .filter(i -> minPrecio == null || i.getCanon() >= minPrecio)
            .filter(i -> maxPrecio == null || i.getCanon() <= maxPrecio)
            .filter(i -> estado == null || i.getEstado().equalsIgnoreCase(estado))
            .toList();
    }     

    public List<InmuebleDetalle> listarInmueblesConPropietario() {
        List<Inmueble> inmuebles = inmuebleRepository.findAll();

        return inmuebles.stream().map(i -> {
            InmuebleDetalle dto = new InmuebleDetalle();
            dto.setId(i.getId());
            dto.setDireccion(i.getDireccion());
            dto.setArea(i.getArea());
            dto.setCanon(i.getCanon());
            dto.setAdministracionIncluida(i.isAdministracionIncluida());
            dto.setValorAdministracion(i.getValorAdministracion());
            dto.setDescripcion(i.getDescripcion());
            dto.setEstado(i.getEstado());

            // Traer el nombre del propietario
            String propietarioNombre = usuarioRepository.findById(i.getPropietarioId())
                                        .map(p -> p.getNombre())
                                        .orElse("Desconocido");
            dto.setPropietarioNombre(propietarioNombre);

            return dto;
        }).collect(Collectors.toList());
    }
}
