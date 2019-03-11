package com.javierramirez.healthyapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by javierramirez on 03/12/17.
 */

public class InterfazHistorial extends AppCompatActivity {


    ListView ListHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        ListHistorial=(ListView)findViewById(R.id.lvHistorial);

        //List<Historial> HistorialLIsta= Historial.find(Historial.class, "imc=?");
        //Historial historial2 =HistorialLIsta.get(0);


        List<Historial> resultadoConsulta = Historial.listAll(Historial.class);
        ArrayList<String> lista1=new ArrayList<String>();
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista1);

        for (int i=0; i<resultadoConsulta.size(); i++)
        {
            Historial result = resultadoConsulta.get(i);
            lista1.add(""+result.getFecha()+" |  PESO: "+result.getPeso()+" Kg | IMC: "+result.getImc());
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista1);
        ListHistorial.setAdapter(adaptador);



    }

}
