package com.flujos.flujosbd.model;

public class Ctrlmstsuc {

    private Short fipais;
    private Short ficanal;
    private Integer adnsuc;
    private Integer adnutr;
    private String fcnegocio;
    private Integer totfaltantes;
    private String rangofaltante;

    public Ctrlmstsuc() {
    }


    public Ctrlmstsuc(Short fipais, Short ficanal, Integer adnsuc, Integer adnutr, String fcnegocio) {
        this.fipais = fipais;
        this.ficanal = ficanal;
        this.adnsuc = adnsuc;
        this.adnutr = adnutr;
        this.fcnegocio = fcnegocio;
    }

    public Short getFipais() {
        return fipais;
    }

    public void setFipais(Short fipais) {
        this.fipais = fipais;
    }

    public Short getFicanal() {
        return ficanal;
    }

    public void setFicanal(Short ficanal) {
        this.ficanal = ficanal;
    }

    public Integer getAdnsuc() {
        return adnsuc;
    }

    public void setAdnsuc(Integer adnsuc) {
        this.adnsuc = adnsuc;
    }

    public Integer getAdnutr() {
        return adnutr;
    }

    public void setAdnutr(Integer adnutr) {
        this.adnutr = adnutr;
    }

    public String getFcnegocio() {
        return fcnegocio;
    }

    public void setFcnegocio(String fcnegocio) {
        this.fcnegocio = fcnegocio;
    }

    public Integer getTotfaltantes() {
        return totfaltantes;
    }

    public void setTotfaltantes(Integer totfaltantes) {
        this.totfaltantes = totfaltantes;
    }

    public String getRangofaltante() {
        return rangofaltante;
    }

    public void setRangofaltante(String rangofaltante) {
        this.rangofaltante = rangofaltante;
    }

    @Override
    public String toString() {
        return "Ctrlmstsuc{" +
                "fipais=" + fipais +
                ", ficanal=" + ficanal +
                ", adnsuc=" + adnsuc +
                ", adnutr=" + adnutr +
                ", fcnegocio='" + fcnegocio + '\'' +
                ", totfaltantes=" + totfaltantes +
                ", rangofaltante='" + rangofaltante + '\'' +
                '}';
    }
}
