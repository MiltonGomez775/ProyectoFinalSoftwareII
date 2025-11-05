package edu.co.uniquindio.demo.dto;

public class InmuebleDetalle {
    private String id;
    private String direccion;
    private double area;
    private double canon;
    private boolean administracionIncluida;
    private double valorAdministracion;
    private String descripcion;
    private String estado;
    private String propietarioNombre;

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public double getCanon() { return canon; }
    public void setCanon(double canon) { this.canon = canon; }

    public boolean isAdministracionIncluida() { return administracionIncluida; }
    public void setAdministracionIncluida(boolean administracionIncluida) { this.administracionIncluida = administracionIncluida; }

    public double getValorAdministracion() { return valorAdministracion; }
    public void setValorAdministracion(double valorAdministracion) { this.valorAdministracion = valorAdministracion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getPropietarioNombre() { return propietarioNombre; }
    public void setPropietarioNombre(String propietarioNombre) { this.propietarioNombre = propietarioNombre; }
}
