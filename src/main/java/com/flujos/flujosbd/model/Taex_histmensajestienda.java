package com.flujos.flujosbd.model;


import java.sql.Timestamp;
//import java.util.Timestamp;

public class Taex_histmensajestienda {

    private Number fnflujoid;
    private Number fnpaisdest;
    private Number fncanaldest;
    private Number fnsucursaldest;
    private String fnfolioconsec;
    private Timestamp fdfechaproceso;
    private Number fnpaisorig;
    private Number fncanalorig;
    private Number fnsucursalorig;
    private Timestamp fdfechainsercion;
    private Timestamp fdfechaenviotda;
    private Number fnstatus;
    private Number fnnotransaccion;
    private String fccadenaejec;
    private Number fnnointentos;
    private Number total;
    private Number counter;


    public Taex_histmensajestienda() {
    }

    public Taex_histmensajestienda(Number fnflujoid, Number fnpaisdest, Number fncanaldest, Number fnsucursaldest, String fnfolioconsec, Timestamp fdfechaproceso, Number fnpaisorig, Number fncanalorig, Number fnsucursalorig, Timestamp fdfechainsercion, Timestamp fdfechaenviotda, Number fnstatus, Number fnnotransaccion, String fccadenaejec, Number fnnointentos, Number total, Number counter) {
        this.fnflujoid = fnflujoid;
        this.fnpaisdest = fnpaisdest;
        this.fncanaldest = fncanaldest;
        this.fnsucursaldest = fnsucursaldest;
        this.fnfolioconsec = fnfolioconsec;
        this.fdfechaproceso = fdfechaproceso;
        this.fnpaisorig = fnpaisorig;
        this.fncanalorig = fncanalorig;
        this.fnsucursalorig = fnsucursalorig;
        this.fdfechainsercion = fdfechainsercion;
        this.fdfechaenviotda = fdfechaenviotda;
        this.fnstatus = fnstatus;
        this.fnnotransaccion = fnnotransaccion;
        this.fccadenaejec = fccadenaejec;
        this.fnnointentos = fnnointentos;
        this.total = total;
        this.counter = counter;
    }

    public Number getFnflujoid() {
        return fnflujoid;
    }

    public void setFnflujoid(Number fnflujoid) {
        this.fnflujoid = fnflujoid;
    }

    public Number getFnpaisdest() {
        return fnpaisdest;
    }

    public void setFnpaisdest(Number fnpaisdest) {
        this.fnpaisdest = fnpaisdest;
    }

    public Number getFncanaldest() {
        return fncanaldest;
    }

    public void setFncanaldest(Number fncanaldest) {
        this.fncanaldest = fncanaldest;
    }

    public Number getFnsucursaldest() {
        return fnsucursaldest;
    }

    public void setFnsucursaldest(Number fnsucursaldest) {
        this.fnsucursaldest = fnsucursaldest;
    }

    public String getFnfolioconsec() {
        return fnfolioconsec;
    }

    public void setFnfolioconsec(String fnfolioconsec) {
        this.fnfolioconsec = fnfolioconsec;
    }

    public Timestamp getFdfechaproceso() {
        return fdfechaproceso;
    }

    public void setFdfechaproceso(Timestamp fdfechaproceso) {
        this.fdfechaproceso = fdfechaproceso;
    }

    public Number getFnpaisorig() {
        return fnpaisorig;
    }

    public void setFnpaisorig(Number fnpaisorig) {
        this.fnpaisorig = fnpaisorig;
    }

    public Number getFncanalorig() {
        return fncanalorig;
    }

    public void setFncanalorig(Number fncanalorig) {
        this.fncanalorig = fncanalorig;
    }

    public Number getFnsucursalorig() {
        return fnsucursalorig;
    }

    public void setFnsucursalorig(Number fnsucursalorig) {
        this.fnsucursalorig = fnsucursalorig;
    }

    public Timestamp getFdfechainsercion() {
        return fdfechainsercion;
    }

    public void setFdfechainsercion(Timestamp fdfechainsercion) {
        this.fdfechainsercion = fdfechainsercion;
    }

    public Timestamp getFdfechaenviotda() {
        return fdfechaenviotda;
    }

    public void setFdfechaenviotda(Timestamp fdfechaenviotda) {
        this.fdfechaenviotda = fdfechaenviotda;
    }

    public Number getFnstatus() {
        return fnstatus;
    }

    public void setFnstatus(Number fnstatus) {
        this.fnstatus = fnstatus;
    }

    public Number getFnnotransaccion() {
        return fnnotransaccion;
    }

    public void setFnnotransaccion(Number fnnotransaccion) {
        this.fnnotransaccion = fnnotransaccion;
    }

    public String getFccadenaejec() {
        return fccadenaejec;
    }

    public void setFccadenaejec(String fccadenaejec) {
        this.fccadenaejec = fccadenaejec;
    }

    public Number getFnnointentos() {
        return fnnointentos;
    }

    public void setFnnointentos(Number fnnointentos) {
        this.fnnointentos = fnnointentos;
    }

    public Number getTotal() {
        return total;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    public Number getCounter() {
        return counter;
    }

    public void setCounter(Number counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "Taex_histmensajestienda{" +
                "fnflujoid=" + fnflujoid +
                ", fnpaisdest=" + fnpaisdest +
                ", fncanaldest=" + fncanaldest +
                ", fnsucursaldest=" + fnsucursaldest +
                ", fnfolioconsec='" + fnfolioconsec + '\'' +
                ", fdfechaproceso=" + fdfechaproceso +
                ", fnpaisorig=" + fnpaisorig +
                ", fncanalorig=" + fncanalorig +
                ", fnsucursalorig=" + fnsucursalorig +
                ", fdfechainsercion=" + fdfechainsercion +
                ", fdfechaenviotda=" + fdfechaenviotda +
                ", fnstatus=" + fnstatus +
                ", fnnotransaccion=" + fnnotransaccion +
                ", fccadenaejec='" + fccadenaejec + '\'' +
                ", fnnointentos=" + fnnointentos +
                ", total=" + total +
                ", counter=" + counter +
                '}';
    }
}
