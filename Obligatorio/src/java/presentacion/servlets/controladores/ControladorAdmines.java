/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTAdministrador;
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
public class ControladorAdmines extends Controlador {
    private DTAdministrador adminconstante;
     
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTAdministrador> admines = FabricaLogica.getLogica().buscoAdmins(request.getParameter("buscar"));
            
            request.setAttribute("admines", admines);
            cargarMensaje("Cantidad de Administradores: " + admines.size(),request);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Administradores.",request);
        }
        
       mostrarVista("index", request, response);
    }
    
    public void agregaradmin_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("agregaradmin",request, response);
    }
    
    public void agregaradmin_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula = 0;
         String nombre = request.getParameter("nombre");
        String clave=request.getParameter("clave");
        double sueldo = 0;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("agregaradmin",request,response);
            
            return;
        }
        try {
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El sueldo no es válido.",request);
            
            mostrarVista("agregaradmin",request,response);
            
            return;
        }
         
          try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("agregaradmin",request,response);
            
            return;
          }
   
        
        DTAdministrador admin = new DTAdministrador(cedula,nombre,clave,sueldo,fecha);
        
        try {
            FabricaLogica.getLogica().agregarEmpleado(admin);
            
            cargarMensaje("¡Administrador agregado con éxito!",request.getSession());
            
            response.sendRedirect("admines");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("agregaradmin",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el Administrador.",request);
            
            mostrarVista("agregaradmin",request,response);
        }
    }
    
    public void veradmin_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("veradmin",request,response);
            
            return;
        }
        
        try {
            DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTAdministrador admin=(DTAdministrador) empleado;
            if (admin != null) {
                request.setAttribute("admin", admin);
                cargarMensaje("¡Administrador encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Administrador con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Administrador.",request);
        }
        
        mostrarVista("veradmin",request,response);
    }
    
    public void modificaradmin_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificaradmin",request,response);
            
            return;
        }
        
        try {
            DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTAdministrador admin=(DTAdministrador) empleado;
            if (admin != null) {
                request.setAttribute("admin", admin);
                cargarMensaje("¡Administrador encontrado!",request);
            } else {
                request.setAttribute("ocultarFormulario", true);
                cargarMensaje("¡ERROR! No se encontró ningún administrador con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el administrador.",request);
        }
        
        mostrarVista("modificaradmin",request,response);
    }
    
    public void modificaradmin_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
         
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificaradmin",request,response);
            
            return;
        }
        
        String nombre = request.getParameter("nombre");
        String clave=request.getParameter("clave");
        double sueldo;
        
        try {
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El sueldo no es válido.",request);
            
            mostrarVista("modificaradmin",request,response);
            
            return;
        }
         try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("modificaradmin",request,response);
            
            return;
          }
        
        DTAdministrador admin = new DTAdministrador(cedula,nombre,clave,sueldo,fecha);
        
        try {
            FabricaLogica.getLogica().modificarEmpleado(admin);
            
            cargarMensaje("¡Administrador modificado con éxito!",request.getSession());
            
            response.sendRedirect("admines");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("modificaradmin",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al modificar el Administrador.",request);
            
            mostrarVista("modificaradmin",request,response);
        }
    }
    
    public void eliminaradmin_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("eliminaradmin",request,response);
            
            return;
        }
        
        try {
           DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTAdministrador admin=(DTAdministrador) empleado;
            adminconstante=admin;
            if (admin != null) {
                request.setAttribute("admin", admin);
                cargarMensaje("¡Administrador encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Administrador con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Administrador.",request);
        }
        
        mostrarVista("eliminaradmin",request,response);
    }
    
    public void eliminaradmin_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            FabricaLogica.getLogica().eliminarEmpleado(adminconstante);
            
            cargarMensaje("¡Administrador eliminado con éxito!",request.getSession());
            
            response.sendRedirect("admines");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("eliminaradmin",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el Administrador.",request);
            
            mostrarVista("eliminaradmin",request,response);
        }
    }
    
    
}
