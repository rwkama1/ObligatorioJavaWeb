/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;


import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioCamara;
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
public class ControladorSCamaras extends Controlador {
    private DTServicioCamara localscamara;
     
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTServicioCamara> scamaras = FabricaLogica.getLogicaServicio().listarSCamaras();
            
            request.setAttribute("scamaras", scamaras);
            cargarMensaje("Cantidad de Servicios de Camaras: " + scamaras.size(),request);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Servicios de Camaras.",request);
        }
        
       mostrarVista("index", request, response);
    }
       public void agregarscamara_get(HttpServletRequest request, HttpServletResponse response) {
            int id;
        
        try {
            id = Integer.parseInt(request.getParameter("idpropiedad"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El id no es válido.",request);
            
            mostrarVista("agregarscamara",request,response);
            
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
        
        mostrarVista("agregarscamara",request,response);
         long cedula;
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("agregarscamara",request,response);
            
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
        
        mostrarVista("agregarscamara",request,response);

    }
    public void agregarscamara_post(HttpServletRequest request, HttpServletResponse response) {
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        boolean monitoreo=Boolean.parseBoolean(request.getParameter("monitoreo"));
        boolean terminal=Boolean.parseBoolean(request.getParameter("terminal"));
        DTPropiedad p=null;
        DTCliente c=null;
         try {
               c=FabricaLogica.getLogicaCliente().buscarCliente(Long.parseLong(request.getParameter("cedula")));
           } catch (ExcepcionPersonalizada ex) {
                 cargarMensaje("¡ERROR! No se encontro el Cliente",request);
            mostrarVista("agregarscamara",request,response);
            
            return;
           }
           try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("agregarscamara",request,response);
            
            return;
          }
           try {
               p=FabricaLogica.getLogicaCliente().buscarPropiedad(Integer.parseInt(request.getParameter("idpropiedad")));
           } catch (ExcepcionPersonalizada ex) {
                 cargarMensaje("¡ERROR! No se encontro la Propiedad",request);
            mostrarVista("agregarscamara",request,response);
            
            return;
           }
        DTServicioCamara scamara = new DTServicioCamara(terminal,0,fecha,monitoreo,p,c);
        
        try {
            FabricaLogica.getLogicaServicio().agregarServicio(scamara);
            
            cargarMensaje("¡Servicio Camara agregado con éxito!",request.getSession());
            response.sendRedirect("scamaras");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("agregarscamara",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el Servicio Camara.",request);
            
            mostrarVista("agregarscamara",request,response);
        }
    }
     public void desactivarscamara_get(HttpServletRequest request, HttpServletResponse response) {
        int id=0;
        
        try {
            id = Integer.parseInt(request.getParameter("idscamara"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("desactivarscamara",request,response);
            
            return;
        }
        
        try {
           DTServicio serv = FabricaLogica.getLogicaServicio().buscarServicio(id);
            DTServicioCamara scamara=(DTServicioCamara) serv;
           localscamara=scamara;
            if (scamara != null) {
                request.setAttribute("scamara", scamara);
                cargarMensaje("¡Servicio Camara encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Servicio Camara con el id " + id + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el ServicioCamara.",request);
        }
        
        mostrarVista("desactivarscamara",request,response);
    }
      public void desactivarscamara_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            FabricaLogica.getLogicaServicio().desactivarServicio(localscamara);
            
            cargarMensaje("¡Servicio Camara desactivado con éxito!",request.getSession());
            
            response.sendRedirect("scamaras");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("desactivarscamara",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el Servicio Camara.",request);
            
            mostrarVista("desactivarscamara",request,response);
        }
      }
       public void verscamara_get(HttpServletRequest request, HttpServletResponse response) {
        int id=0;
        
        try {
            id = Integer.parseInt(request.getParameter("idscamara"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El ID  no es válido.",request);
            
            mostrarVista("verscamara",request,response);
            
            return;
        }
        
        try {
           DTServicio serv= FabricaLogica.getLogicaServicio().buscarServicio(id);
        DTServicioCamara scamara=(DTServicioCamara)serv;
            if (scamara != null) {
                request.setAttribute("scamara", scamara);
                cargarMensaje("¡Servicio Camara encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Servicio Camara con ID " + id + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Servicio.",request);
        }
        
        mostrarVista("verscamara",request,response);
    }
}
