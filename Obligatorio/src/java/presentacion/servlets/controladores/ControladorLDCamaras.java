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
public class ControladorLDCamaras extends Controlador{
    private DTDispositivoCamara localcamara;
      public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTDispositivoCamara> ldcamaras = FabricaLogica.getLogicaD().listarRDCamaras();
            request.setAttribute("ldcamaras",ldcamaras);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Dispositivos de Camaras.",request);
        }
        
       mostrarVista("index", request, response);
    }
      public void ldcamara_get(HttpServletRequest request, HttpServletResponse response) {
            int numero;       
        try {
            numero = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero no es válido.",request);
            mostrarVista("ldcamara",request,response); 
            return;
        }
          try {
           DTDispositivo dis = FabricaLogica.getLogicaD().buscarD(numero);
            DTDispositivoCamara dcamara=(DTDispositivoCamara) dis;
           localcamara=dcamara;
            if (dcamara != null) {
                request.setAttribute("ldcamara", dcamara);
                cargarMensaje("¡ Camara encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna  Camara con el Numero Inventario " + numero + ".",request);
            }
        } 
          catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Camara.",request);
        }
        
        mostrarVista("ldcamara",request,response);
     

    }
      public void ldcamara_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            
            FabricaLogica.getLogicaD().liberarSD(localcamara);
            
            cargarMensaje("¡Camara Liberada de Servicio con éxito!",request.getSession());
            
            response.sendRedirect("ldcamaras"); 
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("ldcamara",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar la Camara.",request);
            
            mostrarVista("ldcamara",request,response);
        }
      }
       public void verldcamaras_get(HttpServletRequest request, HttpServletResponse response) {
            int numero;       
        try {
            numero = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero no es válido.",request);
            mostrarVista("verldcamaras",request,response); 
            return;
        }
          try {
           DTDispositivo dis = FabricaLogica.getLogicaD().buscarD(numero);
            DTDispositivoCamara dcamara=(DTDispositivoCamara) dis;
           localcamara=dcamara;
            if (dcamara != null) {
                request.setAttribute("ldcamara", dcamara);
                cargarMensaje("¡ Camara encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna  Camara con el Numero Inventario " + numero + ".",request);
            }
        } 
          catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Camara.",request);
        }
        
        mostrarVista("verldcamaras",request,response);
     

    }
}
