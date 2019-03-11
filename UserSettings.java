package com.javierramirez.healthyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class UserSettings extends AppCompatActivity {

    TextView tvNombre, tvPeso, tvIMC, ActDate;
    Button Actualizar;
    EditText NuevoPeso;
    RelativeLayout color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersettings);

        tvNombre=(TextView)findViewById(R.id.tvNombre);
        tvPeso=(TextView)findViewById(R.id.tvSuPesoActual);
        tvIMC=(TextView)findViewById(R.id.tvSuIMCActual);
        Actualizar=(Button)findViewById(R.id.btnActualizar);
        NuevoPeso=(EditText)findViewById(R.id.NuevoPeso);
        ActDate=(TextView)findViewById(R.id.actDate);

        SharedPreferences preferences = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE); //Acceder al archivo DatosUsuario
        String Nombre= preferences.getString("NombreUsuario", " ");
        float PesoActual=preferences.getFloat("SuPeso", 0); //Obtener valor en SuPeso
        float IMCActual=preferences.getFloat("IMC_Actual", 0);
        final float Altura=preferences.getFloat("SuAltura", 0); //Obtener valor en SuAltura
        //float PesoA=preferences.getFloat("SuPeso", PesoActual);
        String Time=preferences.getString("Actualizacion", " ");



        tvNombre.setText(Nombre);
        ActDate.setText(Time);
        tvPeso.setText(String.valueOf(PesoActual));
        tvIMC.setText(String.format("%.2f", IMCActual));
        tvPeso.setText(String.valueOf(PesoActual));



        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtener valor del nuevo peso ingresado
                String ActualPeso= NuevoPeso.getText().toString();
                float NumActPeso= Float.parseFloat(ActualPeso);



                //Calcular nuevo IMC con el nuevo peso
                SharedPreferences preferences = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE); //Acceder a los archivos
                SharedPreferences.Editor editor = preferences.edit(); //Editar archivo

                //GUARDAR NUEVO PESO
                editor.putFloat("SuPeso", NumActPeso);

                //GUARDAR FECHA DE MODIFICACIÓN DE PESO
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); //Formato hora
                SimpleDateFormat formatFecha=new SimpleDateFormat("dd/MM/yyy"); //Formato dia
                Date diaActual= Calendar.getInstance().getTime(); //Dia y hora del celular
                String Dia=formatFecha.format(diaActual);
                Date hora = Calendar.getInstance().getTime();//Hora Actual
                String HoraActual = formatoHora.format(hora);
                //Save Data Time
                ActDate.setText(String.format("Actualizaste tu peso el %s a las: %s",  Dia, HoraActual));
                String SaveTime = ActDate.getText().toString();
                editor.putString("Actualizacion", SaveTime); //Guardar en Actualizacion



                //Calcular nuevo IMC
                float IMC= (NumActPeso/(Altura*Altura));
                //Guardar nuevo IMC
                editor.putFloat("IMC_Actual", IMC);
                editor.commit();



                //BASE DE DATOS
                //Historial historial = new Historial(Dia, tvPeso.getText().toString(), tvIMC.getText().toString()); //Guarda datos anteriores a la actualizacion
                Historial historial = new Historial(Dia, String.valueOf(NumActPeso), String.format("%.2f",IMC)); //Guarda datos recien ingresados
                historial.save();




                //Mostrar los nuevos datos
                tvPeso.setText(String.valueOf(NumActPeso));
                tvIMC.setText(String.format("%.2f",IMC));
                ActDate.setText(String.format("Actualizaste tu peso el %s a las: %s",  Dia, HoraActual));

                startActivity(new Intent(UserSettings.this, UsuarioInterfaz.class));





            }
        });


    }
}
