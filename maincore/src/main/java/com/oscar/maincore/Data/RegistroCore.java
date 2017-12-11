package com.oscar.maincore.Data;

import java.util.Date;

/**
 * Created by oemy9 on 09/12/2017.
 */

public class RegistroCore extends BaseCoreModel {
   private int id;
   private double peso;
   private Date fecha;
   private String notas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "RegistroCore{" +
                "id=" + id +
                ", peso=" + peso +
                ", fecha=" + fecha +
                ", notas='" + notas + '\'' +
                '}';
    }
}
