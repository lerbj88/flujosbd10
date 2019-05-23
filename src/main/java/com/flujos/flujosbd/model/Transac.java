package com.flujos.flujosbd.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Transac {

    private Integer fitranno;
    private Short fitrantipo ;
    private Integer ficonsectipo;
    private Timestamp fdtranfechr;
    private String fctranws;
    private String fctranusr;
    private Integer fitrannoreg;
    private String fcaplicacion;
    private Short fitransmit;
    private Timestamp fdiniciotran;
    private Timestamp fdfintran;

    public Transac() {
    }

    public Transac(Integer fitranno, Short fitrantipo, Integer ficonsectipo, Timestamp fdtranfechr, String fctranws, String fctranusr, Integer fitrannoreg, String fcaplicacion, Short fitransmit, Timestamp fdiniciotran, Timestamp fdfintran) {
        this.fitranno = fitranno;
        this.fitrantipo = fitrantipo;
        this.ficonsectipo = ficonsectipo;
        this.fdtranfechr = fdtranfechr;
        this.fctranws = fctranws;
        this.fctranusr = fctranusr;
        this.fitrannoreg = fitrannoreg;
        this.fcaplicacion = fcaplicacion;
        this.fitransmit = fitransmit;
        this.fdiniciotran = fdiniciotran;
        this.fdfintran = fdfintran;
    }

    public Integer getFitranno() {
        return fitranno;
    }

    public void setFitranno(Integer fitranno) {
        this.fitranno = fitranno;
    }

    public Short getFitrantipo() {
        return fitrantipo;
    }

    public void setFitrantipo(Short fitrantipo) {
        this.fitrantipo = fitrantipo;
    }

    public Integer getFiconsectipo() {
        return ficonsectipo;
    }

    public void setFiconsectipo(Integer ficonsectipo) {
        this.ficonsectipo = ficonsectipo;
    }

    public Timestamp getFdtranfechr() {
        return fdtranfechr;
    }

    public void setFdtranfechr(Timestamp fdtranfechr) {
        this.fdtranfechr = fdtranfechr;
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

    public String getFcaplicacion() {
        return fcaplicacion;
    }

    public void setFcaplicacion(String fcaplicacion) {
        this.fcaplicacion = fcaplicacion;
    }

    public Short getFitransmit() {
        return fitransmit;
    }

    public void setFitransmit(Short fitransmit) {
        this.fitransmit = fitransmit;
    }

    public Timestamp getFdiniciotran() {
        return fdiniciotran;
    }

    public void setFdiniciotran(Timestamp fdiniciotran) {
        this.fdiniciotran = fdiniciotran;
    }

    public Timestamp getFdfintran() {
        return fdfintran;
    }

    public void setFdfintran(Timestamp fdfintran) {
        this.fdfintran = fdfintran;
    }

    @Override
    public String toString() {
        return "Transac{" +
                "fitranno=" + fitranno +
                ", fitrantipo=" + fitrantipo +
                ", ficonsectipo=" + ficonsectipo +
                ", fdtranfechr=" + fdtranfechr +
                ", fctranws='" + fctranws + '\'' +
                ", fctranusr='" + fctranusr + '\'' +
                ", fitrannoreg=" + fitrannoreg +
                ", fcaplicacion='" + fcaplicacion + '\'' +
                ", fitransmit=" + fitransmit +
                ", fdiniciotran=" + fdiniciotran +
                ", fdfintran=" + fdfintran +
                '}';
    }
}
