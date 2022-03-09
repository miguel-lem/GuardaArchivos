package com.example.guardaarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView eTNombre, eTDireccion, eTEmail, eTTelefono, tVTodo;
    String[] archivos;
    ArrayList<String> todos = new ArrayList<String>();
    int cont=0;
    Controlador c= new Controlador();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTNombre = findViewById(R.id.eTNombre);
        eTDireccion = findViewById(R.id.eTDireccion);
        eTEmail = findViewById(R.id.eTEmail);
        eTTelefono = findViewById(R.id.eTTelefono);
        tVTodo=findViewById(R.id.tVTodo);
        //String datos;
        if(c.existeArchivo(fileList(),"contactos.txt")){
            Toast.makeText(this, "Archivo contactos.txt si existe", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "No hay archivos", Toast.LENGTH_LONG).show();
        }

        archivos = fileList();
        for (int i = 0; i < archivos.length; i++) {
            Toast.makeText(this, archivos[i], Toast.LENGTH_LONG).show();
        }
        if (archivos.length == 0)
            Toast.makeText(this, "No hay archivos", Toast.LENGTH_LONG).show();

    }

    public void guardar(View view) {
        try {
            OutputStreamWriter arch = new OutputStreamWriter(openFileOutput("contactos.txt", Activity.MODE_APPEND));
            c.guardar(arch,eTNombre.getText().toString() + ";" + eTDireccion.getText().toString() + ";" + eTEmail.getText().toString()
                    + ";" + eTTelefono.getText().toString() + "\n");
            eTNombre.setText("");
            eTDireccion.setText("");
            eTEmail.setText("");
            eTTelefono.setText("");
            Toast.makeText(this, "La información ha sido almacenada", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscar(View view) {
        todos.clear();
        if (archivos.length != 0) {
            try {

                InputStreamReader aux = new InputStreamReader(openFileInput("contactos.txt"));
                BufferedReader br = new BufferedReader(aux);
                String linea = br.readLine();
                //Maria;su casa;maria@mimail.com;098876655
                Toast.makeText(this, "afuera " + linea, Toast.LENGTH_LONG).show();
                while (linea != null) {
                    Toast.makeText(this, "dentro " + linea, Toast.LENGTH_LONG).show();
                    if (linea.split(";")[0].compareToIgnoreCase(eTNombre.getText().toString()) == 0) {
                        todos.add(linea);
                    }
                    linea = br.readLine();
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (todos.size() != 0) {
                eTNombre.setText(todos.get(0).split(";")[0]);
                eTDireccion.setText(todos.get(0).split(";")[1]);
                eTEmail.setText(todos.get(0).split(";")[2]);
                eTTelefono.setText(todos.get(0).split(";")[3]);
                int cuantos = todos.size() + 1;
                Toast.makeText(this, "Existen " + cuantos + " contactos que coinciden con el nombre " + eTNombre.getText().toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No existe ningun contacto de nombre " + eTNombre.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void desplegar(View view) {
        if (archivos.length != 0) {
            String todoA = "";
            try {
                InputStreamReader aux = new InputStreamReader(openFileInput("contactos.txt"));
                BufferedReader br = new BufferedReader(aux);
                String linea = br.readLine();
                //Maria;su casa;maria@mimail.com;098876655
                //todoA=linea;
                while (linea != null) {
                    todoA = todoA + linea;
                    if (linea.split(";")[0].compareToIgnoreCase(eTNombre.getText().toString()) == 0) {
                        todos.add(linea);
                    }
                    linea = br.readLine();
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Toast.makeText(this, todoA, Toast.LENGTH_LONG).show();
        }
    }

    public void desplegar1(View view) {
        String todoA="";
            for(int i=0;i<todos.size();i++){
                todoA=todoA+todos.get(i)+"\n";
            }
        tVTodo.setText(todoA);
           // Toast.makeText(this, todoA, Toast.LENGTH_LONG).show();
    }
    public void siguiente (View view){
        if(cont< todos.size()-1){
            cont++;
            eTNombre.setText(todos.get(cont).split(";")[0]);
            eTDireccion.setText(todos.get(cont).split(";")[1]);
            eTEmail.setText(todos.get(cont).split(";")[2]);
            eTTelefono.setText(todos.get(cont).split(";")[3]);
        }else{
            Toast.makeText(this,"No existen más contactos con el nombre "+eTNombre.getText().toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void anterior(View view){
        if(cont>=1){
            cont--;
            eTNombre.setText(todos.get(cont).split(";")[0]);
            eTDireccion.setText(todos.get(cont).split(";")[1]);
            eTEmail.setText(todos.get(cont).split(";")[2]);
            eTTelefono.setText(todos.get(cont).split(";")[3]);
        }else
            Toast.makeText(this,"No existen más contactos con el nombre "+eTNombre.getText().toString(),Toast.LENGTH_LONG).show();
    }
}
