/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Waldemar
 */
public class ControladorSAlarmas extends Controlador{
     private DTServicioAlarma localsalarma;
     
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTServicioAlarma> salarmas = FabricaLogica.getLogicaServicio().listarSAlarmas();
            request.setAttribute("salarmas",salarmas);
            cargarMensaje("Cantidad de Servicios de Alarmas: " + salarmas.size(),request);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Servicios de Alarmas.",request);
        }
        
       mostrarVista("index", request, response);
    }
   public void agregarsalarma_get(HttpServletRequest request, HttpServletResponse response) {
            int id;
        
        try {
            id = Integer.parseInt(request.getParameter("idpropiedad"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El id no es válido.",request);
            
            mostrarVista("agregarsalarma",request,response);
            
            return;
        }
        
        try {
            DTPropiedad propiedad = FabricaLogica.getLogicaCliente().buscarPropiedad(id);
         
            if (propiedad != null) {
                request.setAttribute("propiedad", propiedad);
                cargarMensaje("¡Propiedad encontrada!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningúna Propiedad con el ID " + id + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar la Propiedad.",request);
        }
        
        mostrarVista("agregarsalarma",request,response);
         long cedula;
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("agregarsalarma",request,response);
            
            return;
        }
        
        try {
            DTCliente cliente = FabricaLogica.getLogicaCliente().buscarCliente(cedula);
              
            if (cliente != null) {
                request.setAttribute("cliente", cliente);
                cargarMensaje("¡Cliente encontrado!",request);
            } else {
                request.setAttribute("ocultarFormulario", true);
                cargarMensaje("¡ERROR! No se encontró ningún cliente con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el cliente.",request);
        }
        
        mostrarVista("agregarsalarma",request,response);

    }
    public void agregarsalarma_post(HttpServletRequest request, HttpServletResponse response) {
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        boolean monitoreo=Boolean.parseBoolean(request.getParameter("monitoreo"));
       int codigo=0;
        DTPropiedad p=null;
        DTCliente c=null;
        
        try {
            codigo = Integer.parseInt(request.getParameter("codigo"));;
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El codigo de Anulación no es válido.",request);
            
            mostrarVista("agregarsalarma",request,response);
            
            return;
        }
         try {
               c=FabricaLogica.getLogicaCliente().buscarCliente(Long.parseLong(request.getParameter("cedula")));
           } catch (ExcepcionPersonalizada ex) {
                 cargarMensaje("¡ERROR! No se encontro el Cliente",request);
            mostrarVista("agregarsalarma",request,response);
            
            return;
           }
           try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("agregarsalarma",request,response);
            
            return;
          }
           try {
               p=FabricaLogica.getLogicaCliente().buscarPropiedad(Integer.parseInt(request.getParameter("idpropiedad")));
           } catch (ExcepcionPersonalizada ex) {
                 cargarMensaje("¡ERROR! No se encontro la Propiedad",request);
            mostrarVista("agregarsalarma",request,response);
            
            return;
           }
        DTServicioAlarma salarma = new DTServicioAlarma(codigo,0,fecha,monitoreo,p,c);
        
        try {
            FabricaLogica.getLogicaServicio().agregarServicio(salarma);
            
            cargarMensaje("¡Servicio Alarma agregado con éxito!",request.getSession());
            response.sendRedirect("salarmas");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("agregarsalarma",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el Servicio Alarma.",request);
            
            mostrarVista("agregarsalarma",request,response);
        }
    }
     public void desactivarsalarma_get(HttpServletRequest request, HttpServletResponse response) {
        int id=0;
        
        try {
            id = Integer.parseInt(request.getParameter("idsalarma"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("desactivarsalarma",request,response);
            
            return;
        }
        
        try {
           DTServicio serv = FabricaLogica.getLogicaServicio().buscarServicio(id);
            DTServicioAlarma salarma=(DTServicioAlarma) serv;
           localsalarma=salarma;
            if (salarma != null) {
                request.setAttribute("salarma", salarma);
                cargarMensaje("¡Servicio Alarma encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Servicio Alarma con el id " + id + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el ServicioAlarma.",request);
        }
        
        mostrarVista("desactivarsalarma",request,response);
    }
      public void desactivarsalarma_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            FabricaLogica.getLogicaServicio().desactivarServicio(localsalarma);
            
            cargarMensaje("¡Servicio Alarma desactivado con éxito!",request.getSession());
            
            response.sendRedirect("salarmas");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("desactivarsalarma",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el Servicio Alarma.",request);
            
            mostrarVista("desactivarsalarma",request,response);
        }
      }
     public void versalarma_get(HttpServletRequest request, HttpServletResponse response) {
        int id=0;
        
        try {
            id = Integer.parseInt(request.getParameter("idsalarma"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El ID  no es válido.",request);
            
            mostrarVista("versalarma",request,response);
            
            return;
        }
        
        try {
           DTServicio serv= FabricaLogica.getLogicaServicio().buscarServicio(id);
        DTServicioAlarma salarma=(DTServicioAlarma)serv;
            if (salarma != null) {
                request.setAttribute("salarma", salarma);
                cargarMensaje("¡Servicio Alarma encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Servicio Alarma con ID " + id + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Servicio.",request);
        }
        
        mostrarVista("versalarma",request,response);
    }
}
