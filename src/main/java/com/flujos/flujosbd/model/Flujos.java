package com.flujos.flujosbd.model;

public class Flujos {

    private Short id_flujo;
    private String descripcion;
    private Short fitipo;

    public Flujos() {
    }

    public Flujos(Short id_flujo, String descripcion, Short fitipo) {
        this.id_flujo = id_flujo;
        this.descripcion = descripcion;
        this.fitipo = fitipo;
    }

    public Short getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(Short id_flujo) {
        this.id_flujo = id_flujo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getFitipo() {
        return fitipo;
    }

    public void setFitipo(Short fitipo) {
        this.fitipo = fitipo;
    }

    @Override
    public String toString() {
        return "Flujos{" +
                "id_flujo=" + id_flujo +
                ", descripcion='" + descripcion + '\'' +
                ", fitipo='" + fitipo + '\'' +
                '}';
    }
}
