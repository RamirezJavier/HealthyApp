package com.javierramirez.healthyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class UsuarioInterfaz extends AppCompatActivity {


    TextView MostrarNombre, ShowIMC, Riesgo, NextPaso, txtIndice, txtBienvenido, tvNext;
    ImageButton UserSettings, Dietas, Ejercicios, Info, Historial;
    RelativeLayout color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz);

        NextPaso=(TextView)  findViewById(R.id.tvNextStatus);
        UserSettings= (ImageButton) findViewById(R.id.btnUserSettings);
        Dietas=(ImageButton)findViewById(R.id.btnDietas);
        MostrarNombre=(TextView) findViewById(R.id.tvSuNombre);
        ShowIMC=(TextView)findViewById(R.id.tvIMC);
        Riesgo=(TextView)findViewById(R.id.tvRiesgo);
        color=(RelativeLayout)findViewById(R.id.UserInterface);
        txtBienvenido=(TextView)findViewById(R.id.txtBienvenido);
        txtIndice=(TextView)findViewById(R.id.txtIndice);
        Ejercicios=(ImageButton)findViewById(R.id.btnEjercicios);
        tvNext=(TextView)findViewById(R.id.txtNext);
        Info=(ImageButton)findViewById(R.id.btnInfo);
        Historial=(ImageButton)findViewById(R.id.btnHistorial);

        SharedPreferences preferences = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE); //Acceder al archivo DatosUsuario
        String SuNombre= preferences.getString("NombreUsuario", ""); //Solicitar informacion con clave NombreUsuario
        MostrarNombre.setText(SuNombre); //Mostrar su nombre

        float Peso=preferences.getFloat("SuPeso", 0); //Obtener valor en SuPeso
        float Altura=preferences.getFloat("SuAltura", 0); //Obtener valor en SuAltura
        //float NewIMC=preferences.getFloat("IMC_Actual", 0);

        //CALCULAR IMC
        float SuIMC= (Peso/(Altura*Altura));

        ShowIMC.setText(String.format("%.3f", SuIMC)); //Muestra el IMC

        //Guardar IMC en DatosUsuario
        SharedPreferences.Editor editor = preferences.edit(); //EDITAR ARCHIVO "DatosUsuario"
        editor.putFloat("SuIMC", SuIMC);




        //DEFINIR ESTADO DE RIESGO SEGUN IMC
        if((SuIMC<18))
        {
            Riesgo.setText("PESO BAJO");
            NextPaso.setText("Llegar a ESTABLE");
            editor.putString("Status", "PESO BAJO");

            //color.setBackgroundColor(getResources().getColor(R.color.yellow));
            color.setBackground(getResources().getDrawable(R.drawable.blue));


            //Color de texto
            ShowIMC.setTextColor(getResources().getColor(R.color.negro));
            Riesgo.setTextColor(getResources().getColor(R.color.negro));
            NextPaso.setTextColor(getResources().getColor(R.color.blanco));
            MostrarNombre.setTextColor(getResources().getColor(R.color.negro));
            txtBienvenido.setTextColor(getResources().getColor(R.color.negro));
            txtIndice.setTextColor(getResources().getColor(R.color.negro));
            tvNext.setTextColor(getResources().getColor(R.color.blanco));

        }
        else if((SuIMC>=18)&&(SuIMC<=24.9))
        {
            Riesgo.setText("ESTABLE");
            NextPaso.setText("Mantenerte en ESTABLE");
            editor.putString("Status", "ESTABLE");

            //color.setBackgroundColor(getResources().getColor(R.color.green));
            color.setBackground(getResources().getDrawable(R.drawable.green));


            //Color de texto
            ShowIMC.setTextColor(getResources().getColor(R.color.negro));
            Riesgo.setTextColor(getResources().getColor(R.color.negro));
            NextPaso.setTextColor(getResources().getColor(R.color.negro));
            MostrarNombre.setTextColor(getResources().getColor(R.color.negro));
            txtBienvenido.setTextColor(getResources().getColor(R.color.negro));
            txtIndice.setTextColor(getResources().getColor(R.color.negro));
            tvNext.setTextColor(getResources().getColor(R.color.negro));

        }
        else if((SuIMC>=25)&&(SuIMC<=27))
        {
            Riesgo.setText("SOBREPESO");
            NextPaso.setText("Bajar a ESTABLE");
            editor.putString("Status", "SOBREPESO");

            //color.setBackgroundColor(getResources().getColor(R.color.orange));
            color.setBackground(getResources().getDrawable(R.drawable.orangee));


            //Color de texto
            ShowIMC.setTextColor(getResources().getColor(R.color.blanco));
            Riesgo.setTextColor(getResources().getColor(R.color.blanco));
            NextPaso.setTextColor(getResources().getColor(R.color.blanco));
            MostrarNombre.setTextColor(getResources().getColor(R.color.blanco));
            txtBienvenido.setTextColor(getResources().getColor(R.color.blanco));
            txtIndice.setTextColor(getResources().getColor(R.color.blanco));
            tvNext.setTextColor(getResources().getColor(R.color.blanco));

        }
        else if ((SuIMC>=27.1))
        {
            Riesgo.setText("OBESIDAD");
            NextPaso.setText("Bajar a SOBREPESO");
            editor.putString("Status", "OBESIDAD");

           // color.setBackgroundColor(getResources().getColor(R.color.red));
            color.setBackground(getResources().getDrawable(R.drawable.red));

            //Color de texto
            ShowIMC.setTextColor(getResources().getColor(R.color.blanco));
            Riesgo.setTextColor(getResources().getColor(R.color.blanco));
            NextPaso.setTextColor(getResources().getColor(R.color.blanco));
            MostrarNombre.setTextColor(getResources().getColor(R.color.blanco));
            txtBienvenido.setTextColor(getResources().getColor(R.color.blanco));
            txtIndice.setTextColor(getResources().getColor(R.color.blanco));
            tvNext.setTextColor(getResources().getColor(R.color.blanco));

        }

        editor.commit();



        UserSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UsuarioInterfaz.this, UserSettings.class));

            }
        });

        Dietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UsuarioInterfaz.this, Dietas.class));
            }
        });


        Ejercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UsuarioInterfaz.this, Ejercicios.class));
            }
        });


        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UsuarioInterfaz.this, Info.class));
            }
        });



        Historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UsuarioInterfaz.this, InterfazHistorial.class));
            }
        });








    }
}
