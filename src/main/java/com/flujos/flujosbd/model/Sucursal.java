package com.flujos.flujosbd.model;

public class Sucursal {
    
    private Integer fisucursal;
    private String fcdirip;

    public Sucursal() {
    }

    public Sucursal(Integer fisucursal, String fcdirip) {
        this.fisucursal = fisucursal;
        this.fcdirip = fcdirip;
    }

    public Integer getFisucursal() {
        return fisucursal;
    }

    public void setFisucursal(Integer fisucursal) {
        this.fisucursal = fisucursal;
    }

    public String getFcdirip() {
        return fcdirip;
    }

    public void setFcdirip(String fcdirip) {
        this.fcdirip = fcdirip;
    }

    @Override
    public String toString() {
        return "Sucursales{" +
                "fisucursal=" + fisucursal +
                ", fcdirip='" + fcdirip + '\'' +
                '}';
    }
}
