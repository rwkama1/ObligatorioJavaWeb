/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTDispositivo;
import compartidos.beans.datatypes.DTDispositivoCamara;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Waldemar
 */
public class ControladorDCamaras extends Controlador {
      private DTDispositivoCamara localdcamara;
      
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTDispositivoCamara> dcamaras = FabricaLogica.getLogicaD().listarDCamaras();
            request.setAttribute("dcamaras", dcamaras);
            cargarMensaje("Cantidad de  Camaras: " + dcamaras.size(),request);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar las Camaras.",request);
        }
       mostrarVista("index", request, response);
    }
     public void agregardcamara_get(HttpServletRequest request, HttpServletResponse response) {
            mostrarVista("agregardcamara",request,response);
    } 
     public void agregardcamara_post(HttpServletRequest request, HttpServletResponse response) {
     int ninventario=0;
       try {
            ninventario = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La Numero de Inventario no es válido.",request);
            
            mostrarVista("agregardcamara",request,response);
            
            return;
        }
        DTDispositivoCamara dcamara = new DTDispositivoCamara("Sin Servicio",null,ninventario,"Sin Servicio",null);
      
        
        try {
            FabricaLogica.getLogicaD().agregarD(dcamara);
            
            cargarMensaje("¡ Camara agregada con éxito!",request.getSession());
            response.sendRedirect("dcamaras");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("agregardcamara",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar la Camara.",request);
            
            mostrarVista("agregardcamara",request,response);
        }
    }
     public void eliminardcamara_get(HttpServletRequest request, HttpServletResponse response) {
        int n=0;
        
        try {
            n = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero no es válido.",request);
            
            mostrarVista("eliminardcamara",request,response);
            
            return;
        }
        
        try {
           DTDispositivo dis = FabricaLogica.getLogicaD().buscarD(n);
            DTDispositivoCamara dcamara=(DTDispositivoCamara) dis;
           localdcamara=dcamara;
            if (dcamara != null) {
                request.setAttribute("dcamara", dcamara);
                cargarMensaje("¡ Camara encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna  Camara con el Numero Inventario " + n + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Camara.",request);
        }
        
        mostrarVista("eliminardcamara",request,response);
    }
      public void eliminardcamara_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            FabricaLogica.getLogicaD().eliminarD(localdcamara);
            
            cargarMensaje("Camara eliminada con éxito!",request.getSession());
            
            response.sendRedirect("dcamaras");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("eliminardcamara",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar la Camara.",request);
            
            mostrarVista("eliminardcamara",request,response);
        }
      }
      public void verdcamara_get(HttpServletRequest request, HttpServletResponse response) {
        int n=0;
        
        try {
            n = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero  no es válido.",request);
            
            mostrarVista("verdcamara",request,response);
            
            return;
        }
        
        try {
           DTDispositivo dis= FabricaLogica.getLogicaD().buscarD(n);
        DTDispositivoCamara dcamara=(DTDispositivoCamara)dis;
            if (dcamara != null) {
                request.setAttribute("dcamara", dcamara);
                cargarMensaje("¡ Camara encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna Camara con Numero " + n + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la camara.",request);
        }
        
        mostrarVista("verdcamara",request,response);
    }
      
}
