/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;

import compartidos.beans.datatypes.DTPrecios;
import compartidos.beans.excepciones.ExcepcionPersistencia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import java.util.ArrayList;

import persistencia.Interfaces.IPPrecios;

/**
 *
 * @author Waldemar
 */
public class PPrecios implements IPPrecios {
       private static PPrecios instancia = null;

    public static PPrecios getInstancia() {
        if (instancia == null) {
            instancia = new PPrecios();
        }
        return instancia;
    }
     private PPrecios() {
         
     }
       @Override
      public DTPrecios listarPrecios(String ruta) throws ExcepcionPersistencia{
      FileReader fr = null;
      BufferedReader br = null;
      File file = new File(ruta);
      try {
            try {
                fr = new FileReader (file);
            } catch (FileNotFoundException ex) {
                throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al leer el archivo de Precios.",ex);
            }
         br = new BufferedReader(fr);
       
         DTPrecios precio=null;
         double palarma=0;
         double pcamara=0;
         double aalarma=0;
         double acamara=0;
         double talarma=0;
         double tcamara=0;
       
            palarma=Double.parseDouble(br.readLine());
            pcamara=Double.parseDouble(br.readLine());
            aalarma=Double.parseDouble(br.readLine());
            acamara=Double.parseDouble(br.readLine());
            talarma=Double.parseDouble(br.readLine());
            tcamara=Double.parseDouble(br.readLine());
            precio=new DTPrecios(palarma,pcamara,talarma,tcamara,aalarma,acamara);
         
         return precio;
      }    
      catch (IOException ex) {
               throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al leer el archivo de Precios.",ex);
           }
      finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }
           catch (Exception ex) {
        throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al leer el archivo de Precios.",ex);
            }
        }
         
      }    
       @Override
      public void actualizarPrecios(DTPrecios p,String ruta) throws ExcepcionPersistencia{
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.println(p.getPbaseAlarma());
            pw.println(p.getPbaseCamara());
            pw.println(p.getAdicionalAlarma());
            pw.println(p.getAdicionalCamara());
            pw.println(p.getTmonitoreoAlarma());
            pw.println(p.getTmonitoreoCamara());
        } catch (Exception ex) {
             throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al leer el archivo de Precios.",ex);
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception ex) {
            throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al leer el archivo de Precios.",ex);
           }
        }
    }
}

