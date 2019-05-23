package com.flujos.flujosbd.model;

public class Control {

    private Short finotienda;
    private String fctdanombre;
    private Short fiidcia;
    private String fccianombre;
    private Short fiidcanal;
    private Short fiidpais;
    private Integer fiarea;
    private Integer firegion;
    private Integer fiareaop;
    private Integer firegionop;


    public Control() {
    }

    public Control(Short finotienda, Short fiidcanal, Short fiidpais) {
    }

    public Control(Short finotienda, String fctdanombre, Short fiidcia, String fccianombre, Short fiidcanal, Short fiidpais, Integer fiarea, Integer firegion, Integer fiareaop, Integer firegionop) {
        this.finotienda = finotienda;
        this.fctdanombre = fctdanombre;
        this.fiidcia = fiidcia;
        this.fccianombre = fccianombre;
        this.fiidcanal = fiidcanal;
        this.fiidpais = fiidpais;
        this.fiarea = fiarea;
        this.firegion = firegion;
        this.fiareaop = fiareaop;
        this.firegionop = firegionop;
    }

    public Short getFinotienda() {
        return finotienda;
    }

    public void setFinotienda(Short finotienda) {
        this.finotienda = finotienda;
    }

    public String getFctdanombre() {
        return fctdanombre;
    }

    public void setFctdanombre(String fctdanombre) {
        this.fctdanombre = fctdanombre;
    }

    public Short getFiidcia() {
        return fiidcia;
    }

    public void setFiidcia(Short fiidcia) {
        this.fiidcia = fiidcia;
    }

    public String getFccianombre() {
        return fccianombre;
    }

    public void setFccianombre(String fccianombre) {
        this.fccianombre = fccianombre;
    }

    public Short getFiidcanal() {
        return fiidcanal;
    }

    public void setFiidcanal(Short fiidcanal) {
        this.fiidcanal = fiidcanal;
    }

    public Short getFiidpais() {
        return fiidpais;
    }

    public void setFiidpais(Short fiidpais) {
        this.fiidpais = fiidpais;
    }

    public Integer getFiarea() {
        return fiarea;
    }

    public void setFiarea(Integer fiarea) {
        this.fiarea = fiarea;
    }

    public Integer getFiregion() {
        return firegion;
    }

    public void setFiregion(Integer firegion) {
        this.firegion = firegion;
    }

    public Integer getFiareaop() {
        return fiareaop;
    }

    public void setFiareaop(Integer fiareaop) {
        this.fiareaop = fiareaop;
    }

    public Integer getFiregionop() {
        return firegionop;
    }

    public void setFiregionop(Integer firegionop) {
        this.firegionop = firegionop;
    }

    @Override
    public String toString() {
        return "Control{" +
                "finotienda=" + finotienda +
                ", fctdanombre='" + fctdanombre + '\'' +
                ", fiidcia=" + fiidcia +
                ", fccianombre='" + fccianombre + '\'' +
                ", fiidcanal=" + fiidcanal +
                ", fiidpais=" + fiidpais +
                ", fiarea=" + fiarea +
                ", firegion=" + firegion +
                ", fiareaop=" + fiareaop +
                ", firegionop=" + firegionop +
                '}';
    }
}
