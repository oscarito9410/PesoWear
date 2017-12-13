package com.oscar.pesowear.Data;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

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
    private double pesoMeta;

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
    public double getPesoMeta() {
        return pesoMeta;
    }
    public void setPesoMeta(double pesoMeta) {
        this.pesoMeta = pesoMeta;
    }
}
