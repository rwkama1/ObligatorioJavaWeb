/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTDispositivo;
import compartidos.beans.datatypes.DTDispositivoCamara;
import compartidos.beans.datatypes.DTEmpleado;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioCamara;
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
public class ControladorRDCamaras extends Controlador {
     public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTDispositivoCamara> rdcamaras = FabricaLogica.getLogicaD().listarDCamaras();
            request.setAttribute("rdcamaras",rdcamaras);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Dispositivos de Camaras.",request);
        }
        
       mostrarVista("index", request, response);
    }
      public void rdcamara_get(HttpServletRequest request, HttpServletResponse response) {
            int numero;       
        try {
            numero = Integer.parseInt(request.getParameter("ninventario"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El numero no es válido.",request);
            mostrarVista("rdcamara",request,response); 
            return;
        }
        try {
            DTDispositivo d = FabricaLogica.getLogicaD().buscarD(numero);
         DTDispositivoCamara dc=(DTDispositivoCamara)d;
            if (dc != null) {
                request.setAttribute("rdcamara", dc);
                cargarMensaje("¡Camara encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna Camara con el Numero " + numero + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Camara.",request);
        }
         try {
            ArrayList<DTServicioCamara> rscamaras = FabricaLogica.getLogicaServicio().listarSCamaras();
            request.getSession().setAttribute("scamaras",rscamaras);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Servicios de Camaras.",request);
        }
           try {
            ArrayList<DTTecnico> tecnicos = FabricaLogica.getLogica().tecnicoEC();
           request.getSession().setAttribute("tecnicos",tecnicos);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Tecnicos.",request);
        }
        mostrarVista("rdcamara",request,response);
        
     

    }
      public void rdcamara_post(HttpServletRequest request, HttpServletResponse response) {
        
        int numero=Integer.parseInt(request.getParameter("ninventario"));
        String ubicacion=request.getParameter("ubicacion");
        String ei=request.getParameter("exi");
        long cedula=Long.parseLong(request.getParameter("cedula"));
        int id=Integer.parseInt(request.getParameter("id"));
          DTEmpleado emp=null;
           DTTecnico t=null;
           DTServicio s=null;
           DTServicioCamara sc=null;
         try {
             emp=FabricaLogica.getLogica().buscarEmpleado(cedula);
             t=(DTTecnico) emp;
         } catch (ExcepcionPersonalizada ex) {
              cargarMensaje("¡ERROR! No se encontro el Empleado",request);
            mostrarVista("rdcamara",request,response);
            return;
         }
           try {
               s=FabricaLogica.getLogicaServicio().buscarServicio(id);
               sc=(DTServicioCamara) s;
           } catch (ExcepcionPersonalizada ex) {
                 cargarMensaje("¡ERROR! No se encontro el Servicio",request);
            mostrarVista("rdcamara",request,response);
            return;
           }
       DTDispositivoCamara dc=new DTDispositivoCamara();
       dc.setNinventario(numero);
       dc.setExteriorinterior(ei);
       dc.setServicioCamara(sc);
       dc.setTecnico(t);
       dc.setUbicacion(ubicacion);
        
        try {
            FabricaLogica.getLogicaD().registrarSD(dc);
            
            cargarMensaje("¡Camara registrado con éxito!",request.getSession());
            response.sendRedirect("rdcamaras");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("rdcamara",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al registrar la Camara.",request);
            
            mostrarVista("rdcamara",request,response);
        }
    }
}
