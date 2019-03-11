package com.javierramirez.healthyapp;

import android.support.v7.app.AppCompatActivity;

import com.orm.SugarApp;
import com.orm.SugarRecord;


public class Historial extends SugarRecord {

    String  fecha, peso, imc;

    public Historial() {
    }


    public Historial(String fecha, String peso, String imc) {
        //this.data_id = data_id;
        this.fecha = fecha;
        this.peso = peso;
        this.imc = imc;
    }


    //public String getData_id() {
    //    return data_id;
    //}

    //public void setData_id(String data_id) {
    //    this.data_id = data_id;
    //}

    public String getFecha() {

        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }
}
