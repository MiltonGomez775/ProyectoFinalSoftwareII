package edu.co.uniquindio.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "espacios_disponibles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspacioDisponible {

    @Id
    private String id;

    private String propietarioId;
    private String inmuebleId;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private boolean disponible = true;
}
