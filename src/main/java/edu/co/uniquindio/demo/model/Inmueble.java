package edu.co.uniquindio.demo.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "inmuebles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inmueble {
    @Id
    private String id;
    private String direccion;
    private double area;
    private double canon;
    private boolean administracionIncluida;
    private double valorAdministracion;
    private String descripcion;
    private String estado = "disponible"; // disponible/ocupado
    private String propietarioId;
    private List<String> fotos; // URLs o nombres de archivos
}
