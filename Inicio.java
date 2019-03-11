package com.javierramirez.healthyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    Button btnIngresar;
    EditText Nombre,Edad, Peso, Altura;
    RadioButton IsMujer, IsHombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Enlazar variables con cajas de texto y boton
        btnIngresar=(Button)findViewById(R.id.btnComenzar);
        Nombre=(EditText)findViewById(R.id.UserName);
        Edad=(EditText)findViewById(R.id.EdadActual);
        Peso=(EditText)findViewById(R.id.PesoActual);
        Altura=(EditText)findViewById(R.id.AlturaActual);
        IsMujer=(RadioButton)findViewById(R.id.radioMujer);
        IsHombre=(RadioButton)findViewById(R.id.radioHombre);



        //DATOS INICIALES (DEBEN ESTAR VACIOS PARA EL PRIMER INICIO)
        SharedPreferences preferences = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        final Boolean IsFirstTime= preferences.getBoolean("IsFirstTime", true); //primera vez que se inicia
        //float UltPeso=preferences.getFloat("NuevoPeso", 0);
        Nombre.setText(preferences.getString("NombreUsuario", " "));
        Edad.setText(String.valueOf(preferences.getInt("SuEdad", 0)));
        Altura.setText(String.valueOf(preferences.getFloat("SuAltura", 0)));
        Peso.setText(String.valueOf(preferences.getFloat("SuPeso", 0)));




        if(IsFirstTime==false)
        {
            startActivity(new Intent(Inicio.this, UsuarioInterfaz.class));
            //finish(); //Destruir ventana
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Obtener el texto contenido en las cajas de texto

                String NombreCompleto=Nombre.getText().toString();
                String sEdad=Edad.getText().toString();
                String sPeso=Peso.getText().toString();
                String sAltura=Altura.getText().toString();

                //Convertir datos a enteros y flotantes

                int numEdad=Integer.parseInt(sEdad);
                float numPeso=Float.parseFloat(sPeso);
                float numAltura=Float.parseFloat(sAltura);

                //Guardar los datos usando pares CLAVE-VALOR
                SharedPreferences preferences = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE); //CREAR ARCHIVO XML
                SharedPreferences.Editor editor = preferences.edit(); //EDITAR ARCHIVO "DatosUsuario"

                //AGREGAR DATOS AL ARCHIVO
                editor.putBoolean("IsFirstTime", false); //Cambiar inicio a "false", para no mostrar mas esta ventana
                editor.putString("NombreUsuario", NombreCompleto);
                editor.putInt("SuEdad", numEdad);
                editor.putFloat("SuPeso", numPeso);
                editor.putFloat("SuAltura", numAltura);


                //VALIDAR RADIO BUTTON

                if(IsHombre.isChecked()==true)
                {
                    editor.putBoolean("Sexo", true);
                    startActivity(new Intent(Inicio.this, UsuarioInterfaz.class));
                }
                else if(IsMujer.isChecked()==true)
                {
                    editor.putBoolean("Sexo", true);
                    startActivity(new Intent(Inicio.this, UsuarioInterfaz.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Debes elegir tu sexo", Toast.LENGTH_SHORT).show();
                }



                editor.commit();





            }
        });

    }
}
