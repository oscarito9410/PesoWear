package com.oscar.pesowear.Data;

/**
 * Created by oemy9 on 19/12/2017.
 */

public class IMCResult {
    private Double pesoInicio;
    private Double pesoFin;
    private Double IMC;
    private String fechaIncio;
    private String fechaFin;
    private String descripcion;

    public IMCResult(Double pesoInicio, Double pesoUltimo, Double IMC, String descripcion) {
        this.pesoInicio = pesoInicio;
        this.pesoFin = pesoUltimo;
        this.IMC = IMC;
        this.descripcion = descripcion;
    }

    public Double getPesoInicio() {
        return pesoInicio;
    }

    public void setPesoInicio(Double pesoInicio) {
        this.pesoInicio = pesoInicio;
    }

    public Double getPesoFin() {
        return pesoFin;
    }

    public void setPesoFin(Double pesoFin) {
        this.pesoFin = pesoFin;
    }

    public Double getImc() {
        return IMC;
    }

    public void setImc(Double imc) {
        this.IMC = imc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(String fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }    @Override


    public String toString() {
        return "IMCResult{" +
                "pesoInicio=" + pesoInicio +
                ", pesoFin=" + pesoFin +
                ", IMC=" + IMC +
                ", fechaIncio='" + fechaIncio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
