/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import modelo.equipo;
import java.sql.*;
import gui.importa_archivo;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;


/**
 *
 * @author jmartin
 */
public class valida {
    ArrayList<equipo> equipos;
    importa_archivo pantalla;
    public valida ( ArrayList<equipo> equipos , importa_archivo pantalla)
           
    { 
        this.equipos = equipos;
        this.pantalla = pantalla;
    }
    
    public void  checa()
    {
        int size,x=0;
        equipo eq;
        size = equipos.size();
        int no_alta= 0 ;                
        Connection conector;
        String serie;
        String query;
        int id;
        Statement s; 
        ResultSet rs;
        System.out.println(size);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
           Class.forName("com.mysql.jdbc.Driver");
           conector = DriverManager.getConnection ("jdbc:mysql://172.16.3.49/snipeit","root", "Peritas01");
           s = conector.createStatement();
           fichero = new FileWriter ("/home/jmartin/salida.txt");
           pw = new PrintWriter (fichero);
           
           while (x<size)
           {
                serie = equipos.get(x).getSerie();
                id = equipos.get(x).getId();
                query = "Select * from assets where serial = '"+serie+"' AND assigned_to is NULL" ;
                rs = s.executeQuery(query);
                if ( rs.next())
                     equipos.get(x).setExiste(true);
                else
                { equipos.get(x).setExiste(false);
                     no_alta = no_alta+1;
                }     
                pantalla.texto(equipos.get(x).toString()+"\n");
                x = x+1;
               
           }
           
           int rep = JOptionPane.showConfirmDialog(null, 
   ("Se detectaron :" + no_alta + " Equipos sin alta o asignados, Continuar :"),null, JOptionPane.YES_NO_OPTION);
           if (rep == JOptionPane.YES_OPTION)
              {
                 System.out.println(" Gabamos");
                 x=0;
                 while (x<size)
                 {
                     if (equipos.get(x).getExiste())
                     {
                         query = "update assets set assigned_to ='"+equipos.get(x).getId()
                                 + "' where serial = '"+ equipos.get(x).getSerie()+"'";
                         System.out.println(query);                         
                         pantalla.texto("Grabando : \n"+ equipos.get(x).toString() +"\n" );
                         s.execute(query);
                         
                     }
                     else
                     {
                         pw.println(equipos.get(x).getSerie() + ";" +equipos.get(x).getId() + "\n");
                     }
                     x=x+1;
                 }
                 
                 
                 
              }
           
        conector.close();   
        fichero.close();
        } catch (Exception e)
          {
            e.printStackTrace();
          }


        
        /* Checando si existe en la base de datos */
        
        
        
        
        
    }  
    
    
}
