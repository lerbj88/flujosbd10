package com.flujos.flujosbd.model;


import java.util.Date;

public class Usuario {

    private Integer fiusuario;
    private String fcnombre;
    private String fcpassword;
    private String fcpassword1;
    private String fcpassword2;
    private String fccorreo;
    private String fctelefono;
    private Integer fiidrole;
    private String fcrol;
    private Date fdfecha;

    public Usuario() {
    }

    public Usuario(Integer fiusuario, String fcnombre, String fcpassword, String fcpassword1, String fcpassword2, String fccorreo,String fctelefono, Integer fiidrole, String fcrol, Date fdfecha) {
        this.fiusuario = fiusuario;
        this.fcnombre = fcnombre;
        this.fcpassword = fcpassword;
        this.fcpassword = fcpassword1;
        this.fcpassword = fcpassword2;
        this.fcpassword = fccorreo;
        this.fcpassword = fctelefono;
        this.fiidrole = fiidrole;
        this.fcrol = fcrol;
        this.fdfecha = fdfecha;
    }

    public Integer getFiusuario() {
        return fiusuario;
    }

    public void setFiusuario(Integer fiusuario) {
        this.fiusuario = fiusuario;
    }

    public String getFcnombre() {
        return fcnombre;
    }

    public void setFcnombre(String fcnombre) {
        this.fcnombre = fcnombre;
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

    public String getFccorreo() {
        return fccorreo;
    }

    public void setFccorreo(String fccorreo) {
        this.fccorreo = fccorreo;
    }

    public String getFctelefono() {
        return fctelefono;
    }

    public void setFctelefono(String fctelefono) {
        this.fctelefono = fctelefono;
    }

    public Integer getFiidrole() {
        return fiidrole;
    }

    public void setFiidrole(Integer fiidrole) {
        this.fiidrole = fiidrole;
    }

    public String getFcrol() {
        return fcrol;
    }

    public void setFcrol(String fcrol) {
        this.fcrol = fcrol;
    }

    public Date getFdfecha() {
        return fdfecha;
    }

    public void setFdfecha(Date fdfecha) {
        this.fdfecha = fdfecha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "fiusuario=" + fiusuario +
                ", fcnombre='" + fcnombre + '\'' +
                ", fcpassword='" + fcpassword + '\'' +
                ", fccorreo='" + fccorreo + '\'' +
                ", fctelefono='" + fctelefono + '\'' +
                ", idrol=" + fiidrole +
                ", fcrol='" + fcrol + '\'' +
                ", fdfecha=" + fdfecha +
                '}';
    }
}
