package edu.co.uniquindio.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id
    private String id;
    private String clienteId;
    private String propietarioId;
    private String inmuebleId;
    private String espacioId;

    private String fecha;
    private String horaInicio;
    private String horaFin;

    private String estado;
}
