package edu.co.uniquindio.demo.dto;
import lombok.Data;

public class RestablecerRequest {
    private String token;
    private String nuevaContrasena;

    public RestablecerRequest() {
    }

    public RestablecerRequest(String token,String nuevaContrasena) {
        this.token = token;
        this.nuevaContrasena = nuevaContrasena;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    public void setNuevaContrasena(String nuevaContrasena) {
        this.nuevaContrasena = nuevaContrasena;
    }
}
