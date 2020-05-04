/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gatos_app;

import javax.swing.JOptionPane;

/**
 *
 * @author sebastian
 */
public class Inicio {
    
    public static void main(String[] args) {
        
        int opcion_menu=-1;
        String[] botones={
            "1. ver gatos",
            "2. ver perros",
            "3. salir"
        };
        
        do {
            //menu principal            
            String opcion=(String)JOptionPane.showInputDialog(null,"Mascotas","Menu principal",JOptionPane.INFORMATION_MESSAGE,
                    null,botones,botones[0]);
            
            //valida que opcion selecciona el usuario
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    opcion_menu=i;
                }
            }
            
            switch(opcion_menu){
                case 0:
                    break;
                case 1:
                    break;
                default:
                    break;
            }
            
            
            
        } while (opcion_menu!=2);
        
    }
    
}
