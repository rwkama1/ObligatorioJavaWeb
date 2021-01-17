/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;



import compartidos.beans.datatypes.DTCobrador;
import compartidos.beans.datatypes.DTEmpleado;
import compartidos.beans.datatypes.DTRecibo;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Waldemar
 */
public class ControladorCobrar extends Controlador {
      int id=0;
        public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTRecibo> reci = FabricaLogica.getR().buscoRecibos(request.getParameter("buscar"));
            request.setAttribute("reci", reci);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Recibos.",request);
        }
        
       mostrarVista("index", request, response);
    }
    public void cobrarecibo_get(HttpServletRequest request, HttpServletResponse response) {
        
           
         id=Integer.parseInt(request.getParameter("id"));
        try {
            DTRecibo recibo = FabricaLogica.getR().buscarRecibos(id);
       
            if (recibo != null) {
                request.setAttribute("recibo", recibo);
                cargarMensaje("¡Recibo encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún recibo con el id " + id + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Recibo.",request);
        }
         try {
            ArrayList<DTCobrador> cobradores = FabricaLogica.getLogica().listarCobrador();
            request.setAttribute("cobradores", cobradores);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Cobradores.",request);
        }
        
        mostrarVista("cobrarecibo",request,response);
     }
    public void cobrarecibo_post(HttpServletRequest request, HttpServletResponse response) {
        
    DTEmpleado emp=null;
    DTCobrador c=null;
        long cedula=Long.parseLong(request.getParameter("cedula"));
       id=Integer.parseInt(request.getParameter("id"));
         try {
             emp=FabricaLogica.getLogica().buscarEmpleado(cedula);
             c=(DTCobrador) emp;
         } catch (ExcepcionPersonalizada ex) {
              cargarMensaje("¡ERROR! No se encontro el Empleado",request);
            mostrarVista("cobrarecibo",request,response);
            return;
         }
    DTRecibo r=new DTRecibo();
      r.setCobrador(c);
      r.setIdRecibo(id);
        try {
            FabricaLogica.getR().cobrarR(r);
            
            cargarMensaje("¡Recibo Cobrado con éxito!",request.getSession());
            response.sendRedirect("cobrar");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("cobrarecibo",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al cobrar el Recibo.",request);
            
            mostrarVista("cobrarecibo",request,response);
        }
    }
      public void listarc_get(HttpServletRequest request, HttpServletResponse response) {
        
           
       try {
            ArrayList<DTRecibo> recibo = FabricaLogica.getR().listarRCC();
            request.setAttribute("reci", recibo);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Recibos.",request);
        }
        
       mostrarVista("listarc", request, response);
        
     
     }
}
