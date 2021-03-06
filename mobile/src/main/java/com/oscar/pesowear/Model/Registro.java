package com.oscar.pesowear.Model;
import com.oscar.maincore.Data.RegistroCore;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.util.Date;

/**
 * Created by oemy9 on 08/12/2017.
 */
@Table(database = DataBase.class)
public class Registro extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    @NotNull
    double peso;

    @Column(name = "fecha")
    @NotNull
    Date fecha;

    @Column
    String notas;

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

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

    public Registro(){

    }

    public Registro(double peso, Date fecha, String notas) {
        this.peso = peso;
        this.fecha = fecha;
        this.notas = notas;
    }


    @Override
    public boolean equals(Object obj) {
        Registro r=(Registro)obj;
        return r.getId()==getId();
    }

    public RegistroCore toCoreElement(){
        RegistroCore core=new RegistroCore();
        core.setId(getId());
        core.setFecha(getFecha());
        core.setNotas(getNotas());
        core.setPeso(getPeso());
        return core;
    }

}
