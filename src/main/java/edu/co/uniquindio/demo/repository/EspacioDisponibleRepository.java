package edu.co.uniquindio.demo.repository;

import edu.co.uniquindio.demo.model.EspacioDisponible;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EspacioDisponibleRepository extends MongoRepository<EspacioDisponible, String> {

    // 🔹 Obtener los espacios disponibles de un inmueble
    List<EspacioDisponible> findByInmuebleIdAndDisponibleTrue(String inmuebleId);

    // 🔹 Buscar un espacio por inmueble, fecha y hora de inicio (para evitar duplicados)
    Optional<EspacioDisponible> findByInmuebleIdAndFechaAndHoraInicio(String inmuebleId, String fecha, String horaInicio);
}
