package com.oscar.pesowear.imc.model;

import com.oscar.maincore.Utils.EstatusDescription;

/**
 * Created by oemy9 on 19/12/2017.
 */

public class IMCResult {
    private Double pesoActual;
    private Double pesoInicio;
    private Double pesoObjetivo;
    private Double IMC;
    private String fechaIncio;
    private String fechaFin;
    private EstatusDescription estatusDescription;
    private String unidadMedida;



    public IMCResult(Double pesoInicio, Double pesoActual, Double IMC) {
        this.pesoInicio = pesoInicio;
        this.IMC = IMC;
        this.pesoActual=pesoActual;
    }

    public Double getPesoRestante() {
        double pesoRestante=0;
        if(pesoObjetivo>pesoInicio){
            pesoRestante=pesoObjetivo-pesoInicio;
        }
        else{
            pesoRestante=pesoInicio-pesoObjetivo;
        }
        return pesoRestante;
    }

    public Double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(Double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public Double getPesoObjetivo() {
        return pesoObjetivo;
    }

    public void setPesoObjetivo(Double pesoObjetivo) {
        this.pesoObjetivo = pesoObjetivo;
    }

    public Double getPesoInicio() {
        return pesoInicio;
    }

    public void setPesoInicio(Double pesoInicio) {
        this.pesoInicio = pesoInicio;
    }


    public Double getImc() {
        return IMC;
    }

    public void setImc(Double imc) {
        this.IMC = imc;
    }

    public EstatusDescription getEstatusDescription() {
        return estatusDescription;
    }

    public void setEstatusDescription(EstatusDescription estatusDescription) {
        this.estatusDescription = estatusDescription;
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
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return "IMCResult{" +
                "pesoInicio=" + pesoInicio +
                ", pesoActual=" + pesoActual +
                ", IMC=" + IMC +
                ", fechaIncio='" + fechaIncio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                '}';
    }
}
