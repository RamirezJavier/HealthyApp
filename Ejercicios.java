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



public class Ejercicios extends AppCompatActivity {


    RelativeLayout ejercicio;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicios);

        SharedPreferences preferences = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE); //Acceder al archivo DatosUsuario
        float SuIMC = preferences.getFloat("SuIMC", 0);

        ejercicio=(RelativeLayout)findViewById(R.id.EjercicioFondo);

        if ((SuIMC < 18))
        {
            ejercicio.setBackgroundDrawable(getResources().getDrawable(R.drawable.caminar));

        }
        else if ((SuIMC >= 18) && (SuIMC <= 24.9))
        {

            ejercicio.setBackgroundDrawable(getResources().getDrawable(R.drawable.abdomen));
        }
        else if ((SuIMC >= 25) && (SuIMC <= 27))
        {
            ejercicio.setBackgroundDrawable(getResources().getDrawable(R.drawable.run));
        }
        else if ((SuIMC >= 27.1))
        {
            ejercicio.setBackgroundDrawable(getResources().getDrawable(R.drawable.escaleras));
        }



    }


}
