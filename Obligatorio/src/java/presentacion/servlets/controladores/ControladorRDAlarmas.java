/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;


import compartidos.beans.datatypes.DTDipositivoAlarma;
import compartidos.beans.datatypes.DTDispositivo;
import compartidos.beans.datatypes.DTEmpleado;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.datatypes.DTTecnico;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Waldemar
 */
public class ControladorRDAlarmas extends Controlador {
     public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTDipositivoAlarma> rdalarmas = FabricaLogica.getLogicaD().listarDAlarmas();
            request.setAttribute("rdalarmas",rdalarmas);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Dispositivos de Alarmas.",request);
        }
        
       mostrarVista("index", request, response);
    }
      public void rdalarma_get(HttpServletRequest request, HttpServletResponse response) {
            int numero;       
        try {
            numero = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero no es válido.",request);
            mostrarVista("rdalarma",request,response); 
            return;
        }
        try {
            DTDispositivo d = FabricaLogica.getLogicaD().buscarD(numero);
         DTDipositivoAlarma dc=(DTDipositivoAlarma)d;
            if (dc != null) {
                request.setAttribute("rdalarma", dc);
                cargarMensaje("¡Alarma encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna Alarma con el Numero " + numero + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Alarma.",request);
        }
         try {
            ArrayList<DTServicioAlarma> rsalarmas= FabricaLogica.getLogicaServicio().listarSAlarmas();
            request.getSession().setAttribute("salarmas",rsalarmas);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Servicios de Alarma.",request);
        }
           try {
            ArrayList<DTTecnico> tecnicos = FabricaLogica.getLogica().tecnicoEA();
            request.getSession().setAttribute("tecnicos",tecnicos);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Tecnicos.",request);
        }
        mostrarVista("rdalarma",request,response);
        
     

    }
       public void rdalarma_post(HttpServletRequest request, HttpServletResponse response) {
        
        int numero=Integer.parseInt(request.getParameter("ninventario"));
        String ubicacion=request.getParameter("ubicacion");
        long cedula=Long.parseLong(request.getParameter("cedula"));
        int id=Integer.parseInt(request.getParameter("id"));
          DTEmpleado emp=null;
           DTTecnico t=null;
           DTServicio s=null;
           DTServicioAlarma sc=null;
         try {
             emp=FabricaLogica.getLogica().buscarEmpleado(cedula);
             t=(DTTecnico) emp;
         } catch (ExcepcionPersonalizada ex) {
              cargarMensaje("¡ERROR! No se encontro el Empleado",request);
            mostrarVista("rdalarma",request,response);
            return;
         }
           try {
               s=FabricaLogica.getLogicaServicio().buscarServicio(id);
               sc=(DTServicioAlarma) s;
           } catch (ExcepcionPersonalizada ex) {
                 cargarMensaje("¡ERROR! No se encontro el Servicio",request);
            mostrarVista("rdalarma",request,response);
            return;
           }
       DTDipositivoAlarma dc=new DTDipositivoAlarma();
       dc.setNinventario(numero);
       dc.setServicioAlarma(sc);
       dc.setTecnico(t);
       dc.setUbicacion(ubicacion);
        
        try {
            FabricaLogica.getLogicaD().registrarSD(dc);
            
            cargarMensaje("¡Alarma registrada con éxito!",request.getSession());
            response.sendRedirect("rdalarmas");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("rdalarma",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al registrar la Alarma.",request);
            
            mostrarVista("rdalarma",request,response);
        }
    }
}
