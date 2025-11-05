package edu.co.uniquindio.demo.repository;

import edu.co.uniquindio.demo.model.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad {@link Cita}.
 *
 * Esta interfaz permite interactuar con la colección "citas" en MongoDB
 * y define consultas personalizadas para filtrar por cliente, propietario o estado.
 *
 * Parte de la historia de usuario: “Gestionar agenda”.
 */
@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {

    /**
     * Obtiene todas las citas asociadas a un cliente específico.
     *
     * @param clienteId identificador único del cliente.
     * @return lista de citas del cliente.
     */
    List<Cita> findByClienteId(String clienteId);

    /**
     * Obtiene todas las citas asociadas a un propietario específico.
     *
     * @param propietarioId identificador único del propietario.
     * @return lista de citas del propietario.
     */
    List<Cita> findByPropietarioId(String propietarioId);

    /**
     * Obtiene todas las citas con un estado específico.
     * Ejemplo: “pendiente”, “confirmada”, “cancelada”.
     *
     * @param estado estado de la cita a buscar.
     * @return lista de citas que cumplen con el estado indicado.
     */
    List<Cita> findByEstado(String estado);
}
