/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTAdministrador;
import compartidos.beans.datatypes.DTCobrador;
import compartidos.beans.datatypes.DTEmpleado;
import compartidos.beans.datatypes.DTTecnico;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.FabricaLogica;


public class ControladorInicio extends Controlador {
    
    public void index_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        String clave=request.getParameter("clave");
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            mostrarVista("index",request,response);
            
            return;
        }
        try {
            DTEmpleado empleado = FabricaLogica.getLogica().logueoEmpleado(cedula,clave);
           
            if (empleado instanceof DTAdministrador) {
                DTAdministrador admin=(DTAdministrador) empleado;
                request.getSession().setAttribute("a",admin);
                  mostrarVista("principaladmin",request,response);
            } 
            else if(empleado instanceof DTTecnico)
            {
                  DTTecnico tecnico=(DTTecnico) empleado;
                request.getSession().setAttribute("tecnico", tecnico);
                  mostrarVista("principaltecnico",request,response);
            }
            else if(empleado instanceof DTCobrador)
            {
                  DTCobrador c=(DTCobrador) empleado;
                request.getSession().setAttribute("c", c);
                  mostrarVista("principalcobrador",request,response);
            }
            
            else {
               cargarMensaje("Empleado con Clave o Cedula Incorrecta.",request);
                 mostrarVista("index",request,response);
               
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al loguear el Empleado.",request);
        }
        
       
    }
    public void principaladmin_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("principaladmin", request, response);
    }
    public void principaltecnico_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("principaltecnico", request, response);
    }
    public void principalcobrador_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("principalcobrador", request, response);
    }
     public void logout_get(HttpServletRequest request, HttpServletResponse response) throws IOException {
          HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("inicio");
       
    }
    
}
