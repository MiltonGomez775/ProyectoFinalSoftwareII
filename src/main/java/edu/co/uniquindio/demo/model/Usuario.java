package edu.co.uniquindio.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String correo;
    private String telefono;
    private String contrasena;
    private String estado = "activo";
    private String rol = "interesado";
}


