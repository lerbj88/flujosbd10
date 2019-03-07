package com.flujos.flujosbd.model;

public class Responsablesflujos {
    private Integer finumempleado;
    private String fcnombre;
    private String fccorreo;
    private Integer ficelular;


    public Responsablesflujos() {
    }

    public Responsablesflujos(Integer finumempleado, String fcnombre, String fccorreo, Integer ficelular) {
        this.finumempleado = finumempleado;
        this.fcnombre = fcnombre;
        this.fccorreo = fccorreo;
        this.ficelular = ficelular;
    }

    public Integer getFinumempleado() {
        return finumempleado;
    }

    public void setFinumempleado(Integer finumempleado) {
        this.finumempleado = finumempleado;
    }

    public String getFcnombre() {
        return fcnombre;
    }

    public void setFcnombre(String fcnombre) {
        this.fcnombre = fcnombre;
    }

    public String getFccorreo() {
        return fccorreo;
    }

    public void setFccorreo(String fccorreo) {
        this.fccorreo = fccorreo;
    }

    public Integer getFicelular() {
        return ficelular;
    }

    public void setFicelular(Integer ficelular) {
        this.ficelular = ficelular;
    }

    @Override
    public String toString() {
        return "Responsablesflujos{" +
                "finumempleado=" + finumempleado +
                ", fcnombre='" + fcnombre + '\'' +
                ", fccorreo='" + fccorreo + '\'' +
                ", ficelular=" + ficelular +
                '}';
    }
}
