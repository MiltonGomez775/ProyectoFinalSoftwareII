package edu.co.uniquindio.demo.dto;
import lombok.Data;

public class RecuperarRequest {
    private String correo;

    public RecuperarRequest() {
    }

    public RecuperarRequest(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
