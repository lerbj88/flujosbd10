package com.flujos.flujosbd.model;


public class Usuario {

    private Integer usuario;
    private String fcnombre;
    private String password;

    public Usuario() {
    }

    public Usuario(Integer usuario, String fcnombre, String password) {
        this.usuario = usuario;
        this.fcnombre = fcnombre;
        this.password = password;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getFcnombre() {
        return fcnombre;
    }

    public void setFcnombre(String fcnombre) {
        this.fcnombre = fcnombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void crearUsuario(Integer usuario, String password) {
    }
}
