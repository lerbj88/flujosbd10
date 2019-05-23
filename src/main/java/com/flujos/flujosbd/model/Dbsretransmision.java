package com.flujos.flujosbd.model;

public class Dbsretransmision {

    private Short fiid;
    private Short fitipo;
    private String fcdescripcion;
    private String fcdatabase;
    private String fcservicename;
    private String fcip;
    private String fcusuario;
    private String fcpassword;
    private String fcpassword1;
    private String fcpassword2;

    public Dbsretransmision() {
    }

    public Dbsretransmision(Short fiid, Short fitipo, String fcdescripcion, String fcdatabase, String fcservicename, String fcip, String fcusuario, String fcpassword, String fcpassword1, String fcpassword2) {
        this.fiid = fiid;
        this.fitipo = fitipo;
        this.fcdescripcion = fcdescripcion;
        this.fcdatabase = fcdatabase;
        this.fcservicename = fcservicename;
        this.fcip = fcip;
        this.fcusuario = fcusuario;
        this.fcpassword = fcpassword;
        this.fcpassword1 = fcpassword1;
        this.fcpassword2 = fcpassword2;
    }

    public Short getFiid() {
        return fiid;
    }

    public void setFiid(Short fiid) {
        this.fiid = fiid;
    }

    public Short getFitipo() {
        return fitipo;
    }

    public void setFitipo(Short fitipo) {
        this.fitipo = fitipo;
    }

    public String getFcdescripcion() {
        return fcdescripcion;
    }

    public void setFcdescripcion(String fcdescripcion) {
        this.fcdescripcion = fcdescripcion;
    }

    public String getFcdatabase() {
        return fcdatabase;
    }

    public void setFcdatabase(String fcdatabase) {
        this.fcdatabase = fcdatabase;
    }

    public String getFcservicename() {
        return fcservicename;
    }

    public void setFcservicename(String fcservicename) {
        this.fcservicename = fcservicename;
    }

    public String getFcip() {
        return fcip;
    }

    public void setFcip(String fcip) {
        this.fcip = fcip;
    }

    public String getFcusuario() {
        return fcusuario;
    }

    public void setFcusuario(String fcusuario) {
        this.fcusuario = fcusuario;
    }

    public String getFcpassword() {
        return fcpassword;
    }

    public void setFcpassword(String fcpassword) {
        this.fcpassword = fcpassword;
    }

    public String getFcpassword1() {
        return fcpassword1;
    }

    public void setFcpassword1(String fcpassword1) {
        this.fcpassword1 = fcpassword1;
    }

    public String getFcpassword2() {
        return fcpassword2;
    }

    public void setFcpassword2(String fcpassword2) {
        this.fcpassword2 = fcpassword2;
    }

    @Override
    public String toString() {
        return "Dbsretransmision{" +
                "fiid=" + fiid +
                ", fitipo=" + fitipo +
                ", fcdescripcion='" + fcdescripcion + '\'' +
                ", fcdatabase='" + fcdatabase + '\'' +
                ", fcservicename='" + fcservicename + '\'' +
                ", fcip='" + fcip + '\'' +
                ", fcusuario='" + fcusuario + '\'' +
                ", fcpassword='" + fcpassword + '\'' +
                ", fcpassword1='" + fcpassword1 + '\'' +
                ", fcpassword2='" + fcpassword2 + '\'' +
                '}';
    }
}
