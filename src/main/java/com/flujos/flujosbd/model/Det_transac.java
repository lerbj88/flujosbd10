package com.flujos.flujosbd.model;

public class Det_transac {

    private Integer fitranno;
    private Integer ficonsdeta;
    private Integer fitiporeg;
    private String fcdatodeta;

    public Det_transac() {
    }

    public Det_transac(Integer fitranno, Integer ficonsdeta, Integer fitiporeg, String fcdatodeta) {
        this.fitranno = fitranno;
        this.ficonsdeta = ficonsdeta;
        this.fitiporeg = fitiporeg;
        this.fcdatodeta = fcdatodeta;
    }

    public Integer getFitranno() {
        return fitranno;
    }

    public void setFitranno(Integer fitranno) {
        this.fitranno = fitranno;
    }

    public Integer getFiconsdeta() {
        return ficonsdeta;
    }

    public void setFiconsdeta(Integer ficonsdeta) {
        this.ficonsdeta = ficonsdeta;
    }

    public Integer getFitiporeg() {
        return fitiporeg;
    }

    public void setFitiporeg(Integer fitiporeg) {
        this.fitiporeg = fitiporeg;
    }

    public String getFcdatodeta() {
        return fcdatodeta;
    }

    public void setFcdatodeta(String fcdatodeta) {
        this.fcdatodeta = fcdatodeta;
    }

    @Override
    public String toString() {
        return "Det_transac{" +
                "fitranno=" + fitranno +
                ", ficonsdeta=" + ficonsdeta +
                ", fitiporeg=" + fitiporeg +
                ", fcdatodeta='" + fcdatodeta + '\'' +
                '}';
    }
}
