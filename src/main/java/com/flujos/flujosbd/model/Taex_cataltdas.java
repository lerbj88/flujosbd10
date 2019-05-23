package com.flujos.flujosbd.model;

public class Taex_cataltdas {


    private Number fipais;
    private Number ficanal;
    private Number fisucursal;
    private String fcdesc;
    private String fcip;
    private Number fistatus;
    private Number tdamigro;

    public Taex_cataltdas() {
    }

    public Taex_cataltdas(Number fipais, Number ficanal, Number fisucursal, String fcdesc, String fcip, Number fistatus, Number tdamigro) {
        this.fipais = fipais;
        this.ficanal = ficanal;
        this.fisucursal = fisucursal;
        this.fcdesc = fcdesc;
        this.fcip = fcip;
        this.fistatus = fistatus;
        this.tdamigro = tdamigro;
    }

    public Number getFipais() {
        return fipais;
    }

    public void setFipais(Number fipais) {
        this.fipais = fipais;
    }

    public Number getFicanal() {
        return ficanal;
    }

    public void setFicanal(Number ficanal) {
        this.ficanal = ficanal;
    }

    public Number getFisucursal() {
        return fisucursal;
    }

    public void setFisucursal(Number fisucursal) {
        this.fisucursal = fisucursal;
    }

    public String getFcdesc() {
        return fcdesc;
    }

    public void setFcdesc(String fcdesc) {
        this.fcdesc = fcdesc;
    }

    public String getFcip() {
        return fcip;
    }

    public void setFcip(String fcip) {
        this.fcip = fcip;
    }

    public Number getFistatus() {
        return fistatus;
    }

    public void setFistatus(Number fistatus) {
        this.fistatus = fistatus;
    }

    public Number getTdamigro() {
        return tdamigro;
    }

    public void setTdamigro(Number tdamigro) {
        this.tdamigro = tdamigro;
    }

    @Override
    public String toString() {
        return "Taex_cataltdas{" +
                "fipais=" + fipais +
                ", ficanal=" + ficanal +
                ", fisucursal=" + fisucursal +
                ", fcdesc='" + fcdesc + '\'' +
                ", fcip='" + fcip + '\'' +
                ", fistatus=" + fistatus +
                ", tdamigro=" + tdamigro +
                '}';
    }
}
