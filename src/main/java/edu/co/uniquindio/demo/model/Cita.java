package edu.co.uniquindio.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Representa una cita dentro del sistema de gestión inmobiliaria.
 *
 * Cada cita está asociada a un cliente, un propietario, un inmueble y
 * un espacio disponible. Además, incluye información sobre la fecha,
 * horario y estado de la cita.
 *
 * Se almacena en la colección "citas" dentro de MongoDB.
 */
@Document(collection = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    /** Identificador único de la cita */
    @Id
    private String id;

    /** Identificador del cliente que solicita la cita */
    private String clienteId;

    /** Identificador del propietario del inmueble */
    private String propietarioId;

    /** Identificador del inmueble asociado a la cita */
    private String inmuebleId;

    /** Identificador del espacio disponible reservado */
    private String espacioId;

    /** Fecha programada de la cita (formato: yyyy-MM-dd) */
    private String fecha;

    /** Hora de inicio de la cita (formato: HH:mm) */
    private String horaInicio;

    /** Hora de finalización de la cita (formato: HH:mm) */
    private String horaFin;

    /** Estado actual de la cita (por ejemplo: "pendiente", "confirmada", "cancelada") */
    private String estado;
}

