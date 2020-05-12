/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gatos_app;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author sebastian
 */
public class GatosService {
    /**
     * esta funcion trae los datos de la API
     */
    public static void verGatos() throws IOException{
        
        OkHttpClient client;
        client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").method("GET", null).build();
        Response response = client.newCall(request).execute();
        
        //la  respuesta de la api como se visualiza en postman(aplicacion)
        String responseJason=response.body().string();
        
        //cortar los corchetes para que quede en formato json 
        responseJason=responseJason.substring(1,responseJason.length());//seleciona todos los caracteres del vector monos la posicion "0" (como si fuera una ventana)
        responseJason=responseJason.substring(0,responseJason.length()-1);//seleciona todos los caracteres del vector menos la ultima posicion
        
        //crear un objeto de la clase Gson
        Gson gson= new  Gson();
        Gatos gatos=gson.fromJson(responseJason,Gatos.class);//esta haciendo un parceo de json a tipo gato y instanceando el objeto
        
        //redimensionar la imagen (en caso de necesitar)
        Image image=null;
        try {
            URL url=new URL(gatos.getUrl());
            image=ImageIO.read(url);
            
            ImageIcon fondoGato=new ImageIcon(image);//
            
            if(fondoGato.getIconWidth()>800){
                //redimensiona la imagen
                Image fondo=fondoGato.getImage();
                Image modificada=fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);//se esta dimensionando la imagen
                fondoGato=new ImageIcon(modificada);
                
            }
            
            String menu="opciones:\n "+
                    "1. ver otra imagen \n"+
                    "2. favorito \n"+
                    "3. volver \n";
            String[] botones={"ver otra imagen", "favorito","volver"};
            //String id_gato =String.valueOf(gatos.getUrl());
            String id_gato =gatos.getId();
            String opcion=(String) JOptionPane.showInputDialog(null,menu,id_gato,JOptionPane.INFORMATION_MESSAGE,fondoGato,botones,botones[0]);
            
            int seleccion=-1;
            for (int i=0;i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    seleccion=i;
                }
            }
            
            switch(seleccion){
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void favoritoGato(Gatos gatos) {
        
    }
    
}
