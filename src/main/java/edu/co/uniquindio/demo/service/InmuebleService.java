package edu.co.uniquindio.demo.service;

import edu.co.uniquindio.demo.model.Inmueble;
import edu.co.uniquindio.demo.repository.InmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

 /**
 * US-03 — Servicio de inmuebles.
 * Nota: al publicar, el estado por defecto es "disponible".
 * Se agregan utilidades para listar por estado y por propietario.
 */

@Service
public class InmuebleService {

    @Autowired
    private InmuebleRepository inmuebleRepository;

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
  
   /** US-03: Lista inmuebles por estado ("disponible"/"ocupado"). */
    public List<Inmueble> listarPorEstado(String estado) {
        return inmuebleRepository.findByEstado(estado);
    }
  
    public Inmueble obtenerInmueblePorId(String id) {
        return inmuebleRepository.findById(id).orElse(null);
    }

}
