/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;


import compartidos.beans.datatypes.DTPrecios;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Waldemar
 */
public class ControladorPrecios extends Controlador{
    ;
       public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
        ServletContext context = getServletContext();
     String fullPath = context.getRealPath("/WEB-INF/precios.txt");
        DTPrecios precios = FabricaLogica.getLP().listarPrecios(fullPath);
            
            request.setAttribute("precios", precios);
           
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Precios.",request);
        }
        
       mostrarVista("index", request, response);
    }
        public void actualizarprecios_get(HttpServletRequest request, HttpServletResponse response)
        {
             try {
        ServletContext context = getServletContext();
     String fullPath = context.getRealPath("/WEB-INF/precios.txt");
        DTPrecios pr = FabricaLogica.getLP().listarPrecios(fullPath);
            
            request.setAttribute("pr", pr);
           
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Precios.",request);
        }
             
             mostrarVista("actualizarprecios",request, response);
        }
      public void actualizarprecios_post(HttpServletRequest request, HttpServletResponse response) {
     ServletContext context = getServletContext();
     String fullPath = context.getRealPath("/WEB-INF/precios.txt");
      double pbaseAlarma;
     double  pbaseCamara;
     double aAlarma;
     double aCamara;
     double tmonitoreoAlarma;
     double tmonitoreoCamara; 
       
       try {
            pbaseAlarma = Double.parseDouble(request.getParameter("pbalarma"));
            pbaseCamara=Double.parseDouble(request.getParameter("pbcamara"));
            aAlarma=Double.parseDouble(request.getParameter("aalarma"));
            aCamara=Double.parseDouble(request.getParameter("acamara")); 
            tmonitoreoAlarma=Double.parseDouble(request.getParameter("tmalarma"));
            tmonitoreoCamara=Double.parseDouble(request.getParameter("tmcamara")); 
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR!Tasa de Monitoreo o Precios Incorrectos.",request);
            
            mostrarVista("actualizarprecios",request,response);
            
            return;
        }
       DTPrecios p=new DTPrecios();
       p.setPbaseAlarma(pbaseAlarma);
       p.setPbaseCamara(pbaseCamara);
      p.setTmonitoreoAlarma(tmonitoreoAlarma);
      p.setTmonitoreoCamara(tmonitoreoCamara);
      p.setAdicionalAlarma(aAlarma);
      p.setAdicionalCamara(aCamara);
        try {
            FabricaLogica.getLP().actualizarPrecios(p,fullPath);
            
            cargarMensaje("¡Precios Actualizados con éxito!",request.getSession());
            response.sendRedirect("precios");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("actualizarprecios",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al Actualizadar los Precios.",request);
            
            mostrarVista("actualizarprecios",request,response);
        }
    }
}
