package edu.co.uniquindio.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un rol dentro del sistema inmobiliario.
 * 
 * <p>Cada rol define el nivel de permisos o tipo de usuario 
 * que puede interactuar con el sistema (por ejemplo: 
 * interesado, arrendatario, propietario, asesor o administrador).</p>
 */
@Document(collection = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    /** Identificador único del rol en la base de datos MongoDB. */
    @Id
    private String id;

    /** Nombre del rol, puede ser: interesado, arrendatario, propietario, asesor o admin. */
    private String nombre;

    @Override
    public String toString() {
        return "Rol{id='" + id + "', nombre='" + nombre + "'}";
    }
}

