
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTDispositivo;
import compartidos.beans.datatypes.DTDipositivoAlarma;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

public class ControladorLDAlarmas extends Controlador{
    private DTDipositivoAlarma localalarma;
      public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTDipositivoAlarma> ldalarmas = FabricaLogica.getLogicaD().listarRDAlarmas();
            request.setAttribute("ldalarmas",ldalarmas);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Dispositivos de Alarmas.",request);
        }
        
       mostrarVista("index", request, response);
    }
      public void ldalarma_get(HttpServletRequest request, HttpServletResponse response) {
            int numero;       
        try {
            numero = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero no es válido.",request);
            mostrarVista("ldalarma",request,response); 
            return;
        }
          try {
           DTDispositivo dis = FabricaLogica.getLogicaD().buscarD(numero);
            DTDipositivoAlarma ldalarma=(DTDipositivoAlarma) dis;
           localalarma=ldalarma;
            if (ldalarma != null) {
                request.setAttribute("ldalarma", ldalarma);
                cargarMensaje("¡ Alarma encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna  Alarma con el Numero Inventario " + numero + ".",request);
            }
        } 
          catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Alarma.",request);
        }
        
        mostrarVista("ldalarma",request,response);
     

    }
      public void ldalarma_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            
            FabricaLogica.getLogicaD().liberarSD(localalarma);
            
            cargarMensaje("¡Alarma Liberada de Servicio con éxito!",request.getSession());
            
            response.sendRedirect("ldalarmas"); 
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("ldalarma",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar la Alarma.",request);
            
            mostrarVista("ldalarma",request,response);
        }
      }
    public void verldalarma_get(HttpServletRequest request, HttpServletResponse response) {
            int numero;       
        try {
            numero = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero no es válido.",request);
            mostrarVista("verldalarma",request,response); 
            return;
        }
          try {
           DTDispositivo dis = FabricaLogica.getLogicaD().buscarD(numero);
            DTDipositivoAlarma ldalarma=(DTDipositivoAlarma) dis;
            if (ldalarma != null) {
                request.setAttribute("ldalarma", ldalarma);
                cargarMensaje("¡ Alarma encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna  Alarma con el Numero Inventario " + numero + ".",request);
            }
        } 
          catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Alarma.",request);
        }
        
        mostrarVista("verldalarma",request,response);
     

    }
      
    
    
}
