/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import logica.clases.LCliente;
import logica.clases.LDispositivo;
import logica.clases.LEmpleado;
import logica.clases.LPrecios;
import logica.clases.LRecibo;
import logica.clases.LServicio;
import logica.interfaces.ILCliente;
import logica.interfaces.ILDispositivo;
import logica.interfaces.ILEmpleado;
import logica.interfaces.ILPrecios;
import logica.interfaces.ILRecibo;
import logica.interfaces.ILServicio;



/**
 *
 * @author Waldemar
 */
public class FabricaLogica {
      public static ILEmpleado getLogica() {
        return LEmpleado.getInstancia();
    }
        public static ILCliente getLogicaCliente() {
        return LCliente.getInstancia();
    }
         public static ILServicio getLogicaServicio() {
        return LServicio.getInstancia();
    }
           public static ILDispositivo getLogicaD() {
        return LDispositivo.getInstancia();
    }
         public static ILPrecios getLP() {
        return LPrecios.getInstancia();
    }
              public static ILRecibo getR() {
        return LRecibo.getInstancia();
    }
}
