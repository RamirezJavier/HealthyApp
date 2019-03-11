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



public class Dietas extends AppCompatActivity {


            RelativeLayout receta;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietas);

        SharedPreferences preferences = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE); //Acceder al archivo DatosUsuario
        float SuIMC = preferences.getFloat("SuIMC", 0);

        receta=(RelativeLayout)findViewById(R.id.RecetaFondo);

        if ((SuIMC < 18))
        {
            receta.setBackgroundDrawable(getResources().getDrawable(R.drawable.su));

        }
        else if ((SuIMC >= 18) && (SuIMC <= 24.9))
        {

            receta.setBackgroundDrawable(getResources().getDrawable(R.drawable.rmp1));
        }
        else if ((SuIMC >= 25) && (SuIMC <= 27))
        {
            receta.setBackgroundDrawable(getResources().getDrawable(R.drawable.bajarpeso));
        }
        else if ((SuIMC >= 27.1))
        {
            receta.setBackgroundDrawable(getResources().getDrawable(R.drawable.rbp2));
        }



    }


}
