/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;


import compartidos.beans.datatypes.DTCobrador;
import compartidos.beans.datatypes.DTEmpleado;
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
public class ControladorCobradores extends Controlador {
    
     private DTCobrador cobradorconstante;
    
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTCobrador> cobradores = FabricaLogica.getLogica().buscoCobradores(request.getParameter("buscar"));
            
            request.setAttribute("cobradores", cobradores);
            cargarMensaje("Cantidad de Cobradores: " + cobradores.size(),request);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Cobradores.",request);
        }
        
       mostrarVista("index", request, response);
    }
    
    public void agregarcobrador_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("agregarcobrador",request, response);
    }
    
    public void agregarcobrador_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula = 0;
         String nombre = request.getParameter("nombre");
        String clave=request.getParameter("clave");
        double sueldo = 0;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        String transporte=request.getParameter("transporte");
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("agregarcobrador",request,response);
            
            return;
        }
        try {
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El sueldo no es válido.",request);
            
            mostrarVista("agregarcobrador",request,response);
            
            return;
        }
         
          try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("agregarcobrador",request,response);
            
            return;
          }
   
        
        DTCobrador cobrador = new DTCobrador(transporte,cedula,nombre,clave,sueldo,fecha);
        
        try {
            FabricaLogica.getLogica().agregarEmpleado(cobrador);
            
            cargarMensaje("¡Cobrador agregado con éxito!",request.getSession());
            
            response.sendRedirect("cobradores");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("agregarcobrador",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el Cobrador.",request);
            
            mostrarVista("agregarcobrador",request,response);
        }
    }
    
    public void vercobrador_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("vercobrador",request,response);
            
            return;
        }
        
        try {
            DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTCobrador cobrador=(DTCobrador) empleado;
            if (cobrador != null) {
                request.setAttribute("cobrador", cobrador);
                cargarMensaje("¡Cobrador encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Cobrador con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Cobrador.",request);
        }
        
        mostrarVista("vercobrador",request,response);
    }
    
    public void modificarcobrador_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificarcobrador",request,response);
            
            return;
        }
        
        try {
            DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTCobrador cobrador=(DTCobrador) empleado;
            if (cobrador != null) {
                request.setAttribute("cobrador", cobrador);
                cargarMensaje("¡Cobrador encontrado!",request);
            } else {
                request.setAttribute("ocultarFormulario", true);
                cargarMensaje("¡ERROR! No se encontró ningún Cobrador con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Cobrador.",request);
        }
        
        mostrarVista("modificarcobrador",request,response);
    }
    
    public void modificarcobrador_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
         
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificarcobrador",request,response);
            
            return;
        }
        
        String nombre = request.getParameter("nombre");
        String clave=request.getParameter("clave");
         String transporte=request.getParameter("transporte");
        double sueldo;
        
        try {
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El sueldo no es válido.",request);
            
            mostrarVista("modificarcobrador",request,response);
            
            return;
        }
         try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("modificarcobrador",request,response);
            
            return;
          }
        
        DTCobrador cobrador = new DTCobrador(transporte,cedula,nombre,clave,sueldo,fecha);
        
        try {
            FabricaLogica.getLogica().modificarEmpleado(cobrador);
            
            cargarMensaje("¡Cobradores modificado con éxito!",request.getSession());
            
            response.sendRedirect("cobradores");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("modificarcobrador",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al modificar el cobrador.",request);
            
            mostrarVista("modificarcobrador",request,response);
        }
    }
    
    public void eliminarcobrador_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("eliminarcobrador",request,response);
            
            return;
        }
        
        try {
           DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTCobrador cobrador=(DTCobrador) empleado;
           cobradorconstante=cobrador;
            if (cobrador != null) {
                request.setAttribute("cobrador", cobrador);
                cargarMensaje("¡Cobrador encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Cobrador con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Cobrador.",request);
        }
        
        mostrarVista("eliminarcobrador",request,response);
    }
    
    public void eliminarcobrador_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            FabricaLogica.getLogica().eliminarEmpleado(cobradorconstante);
            
            cargarMensaje("¡Cobrador eliminado con éxito!",request.getSession());
            
            response.sendRedirect("cobradores");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("eliminarcobrador",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el Cobrador.",request);
            
            mostrarVista("eliminarcobrador",request,response);
        }
    }

}
