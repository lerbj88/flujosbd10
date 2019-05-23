package com.flujos.flujosbd.model;

public class UsuariosFlujos {

    private Integer fiusuario;
    private Integer fiflujo;
    private Integer id_flujo;
    private String descripcion;

    public UsuariosFlujos() {

    }

    public UsuariosFlujos(Integer fiusuario, Integer fiflujo, Integer id_flujo, String descripcion) {
        this.fiusuario = fiusuario;
        this.fiflujo = fiflujo;
        this.id_flujo = id_flujo;
        this.descripcion = descripcion;
    }

    public Integer getFiusuario() {
        return fiusuario;
    }

    public void setFiusuario(Integer fiusuario) {
        this.fiusuario = fiusuario;
    }

    public Integer getFiflujo() {
        return fiflujo;
    }

    public void setFiflujo(Integer fiflujo) {
        this.fiflujo = fiflujo;
    }

    public Integer getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Integer id_flujo) {
        this.id_flujo = id_flujo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "UsuariosFlujos{" +
                "fiusuario=" + fiusuario +
                ", fiflujo=" + fiflujo +
                ", id_flujo=" + id_flujo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
