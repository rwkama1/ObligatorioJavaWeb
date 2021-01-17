/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTTecnico;
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
public class ControladorTecnicos extends Controlador{
       private DTTecnico tecnicoconstante;
    
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTTecnico> tecnicos = FabricaLogica.getLogica().buscoTecnicos(request.getParameter("buscar"));
            
            request.setAttribute("tecnicos", tecnicos);
            cargarMensaje("Cantidad de Tecnicos: " + tecnicos.size(),request);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Tecnico.",request);
        }
        
       mostrarVista("index", request, response);
    }
    
    public void agregartecnico_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("agregartecnico",request, response);
    }
    
    public void agregartecnico_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula = 0;
         String nombre = request.getParameter("nombre");
        String clave=request.getParameter("clave");
        double sueldo = 0;
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
         boolean espcamaras=false;
         boolean espalarmas=false;
        espcamaras= Boolean.parseBoolean(request.getParameter("espcamaras"));
        espalarmas =Boolean.parseBoolean(request.getParameter("espalarmas"));
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("agregartecnico",request,response);
            
            return;
        }
        try {
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El sueldo no es válido.",request);
            
            mostrarVista("agregartecnico",request,response);
            
            return;
        }
         
          try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("agregartecnico",request,response);
            
            return;
          }
   
        
        DTTecnico tecnico = new DTTecnico(espcamaras,espalarmas,cedula,nombre,clave,sueldo,fecha);
        
        try {
            FabricaLogica.getLogica().agregarEmpleado(tecnico);
            
            cargarMensaje("¡Tecnico agregado con éxito!",request.getSession());
            
            response.sendRedirect("tecnicos");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("agregartecnico",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el Tecnico.",request);
            
            mostrarVista("agregartecnico",request,response);
        }
    }
    
    public void vertecnico_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("vertecnico",request,response);
            
            return;
        }
        
        try {
            DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTTecnico tecnico=(DTTecnico) empleado;
            if (tecnico != null) {
                request.setAttribute("tecnico", tecnico);
                cargarMensaje("¡Tecnico encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Tecnico con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Tecnico.",request);
        }
        
        mostrarVista("vertecnico",request,response);
    }
    
    public void modificartecnico_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificartecnico",request,response);
            
            return;
        }
        
        try {
            DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTTecnico tecnico=(DTTecnico) empleado;
            tecnicoconstante=tecnico;
            if (tecnico != null) {
                request.setAttribute("tecnico", tecnico);
                cargarMensaje("¡Tecnico encontrado!",request);
            } else {
                request.setAttribute("ocultarFormulario", true);
                cargarMensaje("¡ERROR! No se encontró ningún Tecnico con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Tecnico.",request);
        }
        
        mostrarVista("modificartecnico",request,response);
    }
    
    public void modificartecnico_post(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
         SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
           boolean espcamaras=false;
         boolean espalarmas=false;
        espcamaras= Boolean.parseBoolean(request.getParameter("espcamaras"));
        espalarmas =Boolean.parseBoolean(request.getParameter("espalarmas"));
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
         
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificartecnico",request,response);
            
            return;
        }
        
        String nombre = request.getParameter("nombre");
        String clave=request.getParameter("clave");
      
        double sueldo;
        
        try {
            sueldo = Double.parseDouble(request.getParameter("sueldo"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El sueldo no es válido.",request);
            
            mostrarVista("modificartecnico",request,response);
            
            return;
        }
         try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("modificartecnico",request,response);
            
            return;
          }
        
        DTTecnico tecnico = new DTTecnico(espcamaras,espalarmas,cedula,nombre,clave,sueldo,fecha);
        tecnicoconstante=tecnico;
        try {
            FabricaLogica.getLogica().modificarEmpleado(tecnicoconstante);
            
            cargarMensaje("¡Tecnico modificado con éxito!",request.getSession());
            
            response.sendRedirect("tecnicos");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("modificartecnico",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al modificar el tecnico.",request);
            
            mostrarVista("modificartecnico",request,response);
        }
    }
    
    public void eliminartecnico_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("eliminartecnico",request,response);
            
            return;
        }
        
        try {
           DTEmpleado empleado = FabricaLogica.getLogica().buscarEmpleado(cedula);
            DTTecnico tecnico=(DTTecnico) empleado;
           tecnicoconstante=tecnico;
            if (tecnico != null) {
                request.setAttribute("tecnico", tecnico);
                cargarMensaje("¡Tecnico encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Tecnico con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Tecnico.",request);
        }
        
        mostrarVista("eliminartecnico",request,response);
    }
    
    public void eliminartecnico_post(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            FabricaLogica.getLogica().eliminarEmpleado(tecnicoconstante);
            
            cargarMensaje("¡Tecnico eliminado con éxito!",request.getSession());
            
            response.sendRedirect("tecnicos");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("eliminartecnico",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al eliminar el Tecnico.",request);
            
            mostrarVista("eliminartecnico",request,response);
        }
        /*
         <td>Especialista Camaras:</td>
                        </td>

            <c:choose>
                        <c:when test="${tecnico.espCamaras}">
                            <td><input type="checkbox" name="espcamaras" value="${!empty tecnico ? tecnico.espCamaras : param.espcamaras}" checked /></td>
                        </c:when>
                        <c:otherwise>
                           <td><input type="checkbox" name="espcamaras" value="${!empty tecnico ? tecnico.espCamaras : param.espcamaras}"/></td>
                        </c:otherwise>
           </c:choose>
                   </td>
        */
      
    }
    
}
