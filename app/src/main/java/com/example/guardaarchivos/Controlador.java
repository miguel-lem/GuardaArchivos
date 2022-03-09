package com.example.guardaarchivos;

import android.app.Activity;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class Controlador {
 public boolean existeArchivo(String[] archivos, String buscado){
   for(int i=0; i<archivos.length;i++)
    if(archivos[i].compareToIgnoreCase(buscado)==0)return true;
   return false;

 }
 public void guardar(OutputStreamWriter arch, String datos){
     try {
         arch.write(datos+ "\n");
         arch.flush();
         arch.close();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}
