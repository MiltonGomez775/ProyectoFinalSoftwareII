package edu.co.uniquindio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaResponse {
    private String id;
    private String clienteId;
    private String propietarioId;
    private String inmuebleId;
    private String espacioId;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private String nombreCliente;
    private String nombrePropietario;
    private String nombreInmueble;
}
