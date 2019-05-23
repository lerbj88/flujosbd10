package com.flujos.flujosbd.model;

import java.sql.Timestamp;

public class CabtraRecepcion {

private Integer fisucursal;
private Integer fitranno;
private Integer fitrantipo;
private Integer ficonsectipo;
private Timestamp Fdtransfechr;
private String fctranws;
private String fctranusr;
private Integer fitrannoreg;
private Integer fltransmit;

    public CabtraRecepcion() {
    }

    public CabtraRecepcion(Integer fisucursal, Integer fitranno, Integer fitrantipo, Integer ficonsectipo, Timestamp fdtransfechr, String fctranws, String fctranusr, Integer fitrannoreg, Integer fltransmit) {
        this.fisucursal = fisucursal;
        this.fitranno = fitranno;
        this.fitrantipo = fitrantipo;
        this.ficonsectipo = ficonsectipo;
        Fdtransfechr = fdtransfechr;
        this.fctranws = fctranws;
        this.fctranusr = fctranusr;
        this.fitrannoreg = fitrannoreg;
        this.fltransmit = fltransmit;
    }

    public Integer getFisucursal() {
        return fisucursal;
    }

    public void setFisucursal(Integer fisucursal) {
        this.fisucursal = fisucursal;
    }

    public Integer getFitranno() {
        return fitranno;
    }

    public void setFitranno(Integer fitranno) {
        this.fitranno = fitranno;
    }

    public Integer getFitrantipo() {
        return fitrantipo;
    }

    public void setFitrantipo(Integer fitrantipo) {
        this.fitrantipo = fitrantipo;
    }

    public Integer getFiconsectipo() {
        return ficonsectipo;
    }

    public void setFiconsectipo(Integer ficonsectipo) {
        this.ficonsectipo = ficonsectipo;
    }

    public Timestamp getFdtransfechr() {
        return Fdtransfechr;
    }

    public void setFdtransfechr(Timestamp fdtransfechr) {
        Fdtransfechr = fdtransfechr;
    }

    public String getFctranws() {
        return fctranws;
    }

    public void setFctranws(String fctranws) {
        this.fctranws = fctranws;
    }

    public String getFctranusr() {
        return fctranusr;
    }

    public void setFctranusr(String fctranusr) {
        this.fctranusr = fctranusr;
    }

    public Integer getFitrannoreg() {
        return fitrannoreg;
    }

    public void setFitrannoreg(Integer fitrannoreg) {
        this.fitrannoreg = fitrannoreg;
    }

    public Integer getFltransmit() {
        return fltransmit;
    }

    public void setFltransmit(Integer fltransmit) {
        this.fltransmit = fltransmit;
    }

    @Override
    public String toString() {
        return "CabtraRecepcion{" +
                "fisucursal=" + fisucursal +
                ", fitranno=" + fitranno +
                ", fitrantipo=" + fitrantipo +
                ", ficonsectipo=" + ficonsectipo +
                ", Fdtransfechr=" + Fdtransfechr +
                ", fctranws='" + fctranws + '\'' +
                ", fctranusr='" + fctranusr + '\'' +
                ", fitrannoreg=" + fitrannoreg +
                ", fltransmit=" + fltransmit +
                '}';
    }
}
