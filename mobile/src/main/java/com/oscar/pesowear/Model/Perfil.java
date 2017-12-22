package com.oscar.pesowear.Model;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by oemy9 on 12/12/2017.
 */
@Table(database = DataBase.class)
public class Perfil extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    private String nombre;
    @Column
    private int objetivo;
    @Column
    private double pesoInicio;
    @Column
    private double pesoObjetivo;
    @Column
    private String unidadMedida;
    @Column
    private int estatura;

    @Column
    private Date fechaInicio;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }
    public double getPesoInicio() {
        return pesoInicio;
    }
    public void setPesoInicio(double pesoInicio) {
        this.pesoInicio = pesoInicio;
    }
    public double getPesoObjetivo() {
        return pesoObjetivo;
    }
    public void setPesoObjetivo(double pesoObjetivo) {
        this.pesoObjetivo = pesoObjetivo;
    }
    public String getUnidadMedida() {return unidadMedida;}
    public int getEstatura() {return estatura;}
    public void setEstatura(int estatura) {this.estatura = estatura;}
    public void setUnidadMedida(String unidadMedida) {this.unidadMedida = unidadMedida;}
}
