package com.flujos.flujosbd.model;

import java.sql.Timestamp;

public class Taex_auditoria {

    private Integer fnflujoid;
    private Integer fnpaisdest;
    private Integer fncanaldest;
    private Integer fnsucursaldest;
    private String fcfolioconsec;
    private Timestamp fdfechaproceso;
    private Integer fnnointentos;
    private Integer fnstatus;
    private String fccadenaejec;
    private String fcdescerror;
    private String fcproceso;
    private String fcusuario;
    private String fcworkstation;
    private Timestamp fdfecha;

    public Taex_auditoria() {
    }

    public Taex_auditoria(Integer fnflujoid, Integer fnpaisdest, Integer fncanaldest, Integer fnsucursaldest, String fcfolioconsec, Timestamp fdfechaproceso, Integer fnnointentos, Integer fnstatus, String fccadenaejec, String fcdescerror, String fcproceso, String fcusuario, String fcworkstation, Timestamp fdfecha) {
        this.fnflujoid = fnflujoid;
        this.fnpaisdest = fnpaisdest;
        this.fncanaldest = fncanaldest;
        this.fnsucursaldest = fnsucursaldest;
        this.fcfolioconsec = fcfolioconsec;
        this.fdfechaproceso = fdfechaproceso;
        this.fnnointentos = fnnointentos;
        this.fnstatus = fnstatus;
        this.fccadenaejec = fccadenaejec;
        this.fcdescerror = fcdescerror;
        this.fcproceso = fcproceso;
        this.fcusuario = fcusuario;
        this.fcworkstation = fcworkstation;
        this.fdfecha = fdfecha;
    }

    public Integer getFnflujoid() {
        return fnflujoid;
    }

    public void setFnflujoid(Integer fnflujoid) {
        this.fnflujoid = fnflujoid;
    }

    public Integer getFnpaisdest() {
        return fnpaisdest;
    }

    public void setFnpaisdest(Integer fnpaisdest) {
        this.fnpaisdest = fnpaisdest;
    }

    public Integer getFncanaldest() {
        return fncanaldest;
    }

    public void setFncanaldest(Integer fncanaldest) {
        this.fncanaldest = fncanaldest;
    }

    public Integer getFnsucursaldest() {
        return fnsucursaldest;
    }

    public void setFnsucursaldest(Integer fnsucursaldest) {
        this.fnsucursaldest = fnsucursaldest;
    }

    public String getFcfolioconsec() {
        return fcfolioconsec;
    }

    public void setFcfolioconsec(String fcfolioconsec) {
        this.fcfolioconsec = fcfolioconsec;
    }

    public Timestamp getFdfechaproceso() {
        return fdfechaproceso;
    }

    public void setFdfechaproceso(Timestamp fdfechaproceso) {
        this.fdfechaproceso = fdfechaproceso;
    }

    public Integer getFnnointentos() {
        return fnnointentos;
    }

    public void setFnnointentos(Integer fnnointentos) {
        this.fnnointentos = fnnointentos;
    }

    public Integer getFnstatus() {
        return fnstatus;
    }

    public void setFnstatus(Integer fnstatus) {
        this.fnstatus = fnstatus;
    }

    public String getFccadenaejec() {
        return fccadenaejec;
    }

    public void setFccadenaejec(String fccadenaejec) {
        this.fccadenaejec = fccadenaejec;
    }

    public String getFcdescerror() {
        return fcdescerror;
    }

    public void setFcdescerror(String fcdescerror) {
        this.fcdescerror = fcdescerror;
    }

    public String getFcproceso() {
        return fcproceso;
    }

    public void setFcproceso(String fcproceso) {
        this.fcproceso = fcproceso;
    }

    public String getFcusuario() {
        return fcusuario;
    }

    public void setFcusuario(String fcusuario) {
        this.fcusuario = fcusuario;
    }

    public String getFcworkstation() {
        return fcworkstation;
    }

    public void setFcworkstation(String fcworkstation) {
        this.fcworkstation = fcworkstation;
    }

    public Timestamp getFdfecha() {
        return fdfecha;
    }

    public void setFdfecha(Timestamp fdfecha) {
        this.fdfecha = fdfecha;
    }

    @Override
    public String toString() {
        return "Taex_auditoria{" +
                "fnflujoid=" + fnflujoid +
                ", fnpaisdest=" + fnpaisdest +
                ", fncanaldest=" + fncanaldest +
                ", fnsucursaldest=" + fnsucursaldest +
                ", fcfolioconsec='" + fcfolioconsec + '\'' +
                ", fdfechaproceso=" + fdfechaproceso +
                ", fnnointentos=" + fnnointentos +
                ", fnstatus=" + fnstatus +
                ", fccadenaejec='" + fccadenaejec + '\'' +
                ", fcdescerror='" + fcdescerror + '\'' +
                ", fcproceso='" + fcproceso + '\'' +
                ", fcusuario='" + fcusuario + '\'' +
                ", fcworkstation='" + fcworkstation + '\'' +
                ", fdfecha=" + fdfecha +
                '}';
    }
}
