/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTDipositivoAlarma;
import compartidos.beans.datatypes.DTDispositivo;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Waldemar
 */
public class ControladorDAlarmas extends Controlador {
      private DTDipositivoAlarma localdalarma;
       
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTDipositivoAlarma> dalarmas = FabricaLogica.getLogicaD().listarDAlarmas();
            request.setAttribute("dalarmas", dalarmas);
            cargarMensaje("Cantidad de  Alarmas: " + dalarmas.size(),request);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar las Alarmas.",request);
        }
       mostrarVista("index", request, response);
    }
     public void agregardalarma_get(HttpServletRequest request, HttpServletResponse response) {
            mostrarVista("agregardalarma",request,response);
    } 
     public void agregardalarma_post(HttpServletRequest request, HttpServletResponse response) {
     int ninventario=0;
       try {
            ninventario = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La Numero de Inventario no es válido.",request);
            
            mostrarVista("agregardalarma",request,response);
            
            return;
        }
        DTDipositivoAlarma dalarma = new DTDipositivoAlarma(null,ninventario,"Sin Servicio",null);
        try {
            FabricaLogica.getLogicaD().agregarD(dalarma);
            cargarMensaje("¡Alarma agregada con éxito!",request.getSession());
            response.sendRedirect("dalarmas");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("agregardalarma",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar la Alarma.",request);
            
            mostrarVista("agregardalarma",request,response);
        }
    }
     public void eliminardalarma_get(HttpServletRequest request, HttpServletResponse response) {
        int n=0;
        
        try {
            n = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero no es válido.",request);
            
            mostrarVista("eliminardalarma",request,response);
            
            return;
        }
        
        try {
           DTDispositivo dis = FabricaLogica.getLogicaD().buscarD(n);
            DTDipositivoAlarma dalarma=(DTDipositivoAlarma) dis;
           localdalarma=dalarma;
            if (dalarma != null) {
                request.setAttribute("dalarma", dalarma);
                cargarMensaje("¡ Alarma encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna  Alarma con el Numero Inventario " + n + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Alarma.",request);
        }
        
        mostrarVista("eliminardalarma",request,response);
    }
      public void eliminardalarma_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            FabricaLogica.getLogicaD().eliminarD(localdalarma);
            
            cargarMensaje("Alarma eliminada con éxito!",request.getSession());
            
            response.sendRedirect("dalarmas");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("eliminardalarma",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar la Alarma.",request);
            
            mostrarVista("eliminardalarma",request,response);
        }
      }
      public void verdalarma_get(HttpServletRequest request, HttpServletResponse response) {
        int n=0;
        
        try {
            n = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero  no es válido.",request);
            
            mostrarVista("verdalarma",request,response);
            
            return;
        }
        
        try {
           DTDispositivo dis= FabricaLogica.getLogicaD().buscarD(n);
        DTDipositivoAlarma dalarma=(DTDipositivoAlarma)dis;
            if (dalarma != null) {
                request.setAttribute("dalarma", dalarma);
                cargarMensaje("¡ Alarma encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna Alarma con Numero " + n + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la alarma.",request);
        }
        
        mostrarVista("verdalarma",request,response);
    }
    
}
