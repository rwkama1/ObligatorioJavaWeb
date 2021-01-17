/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;


import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Waldemar
 */
public class ControladorClientes extends Controlador{
    private DTCliente clienteconstante;
    private DTCliente clienteconstante2;
    private DTPropiedad propiedadconst;
       
    public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTCliente> clientes = FabricaLogica.getLogicaCliente().buscoClientes(request.getParameter("buscar"));
            
            request.setAttribute("clientes", clientes);
            cargarMensaje("Cantidad de Clientes: " + clientes.size(),request);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Clientes.",request);
        }
        
       mostrarVista("index", request, response);
    }
   public void vercliente_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("vercliente",request,response);
            
            return;
        }
        
        try {
            DTCliente cliente = FabricaLogica.getLogicaCliente().buscarCliente(cedula);
          clienteconstante=cliente;
            if (cliente != null) {
                request.setAttribute("cliente", cliente);
                cargarMensaje("¡Cliente encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Cliente con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Cliente.",request);
        }
        
        mostrarVista("vercliente",request,response);
    }
    public void modificarcliente_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificarcliente",request,response);
            
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
        
        mostrarVista("modificarcliente",request,response);
    }  
    public void modificarcliente_post(HttpServletRequest request, HttpServletResponse response) {
         long cedula = 0;
         String nombre = request.getParameter("nombre");
        String zonabarrio=request.getParameter("zonabarrio");
       String dircliente=request.getParameter("dircliente");
       String telefono=request.getParameter("telefono");
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
         
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificarcliente",request,response);
            
            return;
        }
        DTCliente cliente = new DTCliente(cedula,nombre,dircliente,zonabarrio,telefono,null);
        
        try {
            FabricaLogica.getLogicaCliente().modificarCliente(cliente);
            
            cargarMensaje("¡Cliente modificado con éxito!",request.getSession());
            
            response.sendRedirect("clientes");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("modificarcliente",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al modificar el Cliente.",request);
            
            mostrarVista("modificarcliente",request,response);
        }
    }
    public void verpropiedad_get(HttpServletRequest request, HttpServletResponse response) {
        int id;
        
        try {
            id = Integer.parseInt(request.getParameter("idpropiedad"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El id no es válido.",request);
            
            mostrarVista("verpropiedad",request,response);
            
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
        
        mostrarVista("verpropiedad",request,response);
    }
   public void agregarpropiedad_get(HttpServletRequest request, HttpServletResponse response) {
        long cedula;
        
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("agregarpropiedad",request,response);
            
            return;
        }
        
        try {
            DTCliente cliente = FabricaLogica.getLogicaCliente().buscarCliente(cedula);
                clienteconstante=cliente;
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
        
        mostrarVista("agregarpropiedad",request,response);
    } 
    public void  agregarpropiedad_post(HttpServletRequest request, HttpServletResponse response) {
        
         String direccionp = request.getParameter("dirpropiedad");
        String tipop=request.getParameter("tipopropiedad");
     DTPropiedad p=new DTPropiedad(0,direccionp,tipop);
     clienteconstante.getPropCliente().add(p);
    
        try {
            FabricaLogica.getLogicaCliente().agregarPropiedad(clienteconstante);
            
            cargarMensaje("¡Propiedad agregada con éxito!",request.getSession());
            
             mostrarVista("agregarpropiedad",request,response);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("agregarpropiedad",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar la Propiedad.",request);
            
            mostrarVista("agregarpropiedad",request,response);
        }
    }
    public void modificarpropiedad_get(HttpServletRequest request, HttpServletResponse response) {
         long cedula;
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("modificarpropiedad",request,response);
            
            
            return;
        }
        
        try {
            DTCliente cliente = FabricaLogica.getLogicaCliente().buscarCliente(cedula);
          clienteconstante2=cliente;
            if (cliente != null) {
                request.setAttribute("cliente", cliente);
                cargarMensaje("¡Cliente encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún Cliente con la cédula " + cedula + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Cliente.",request);
        }
        
        int id;
        
        try {
            id = Integer.parseInt(request.getParameter("idpropiedad"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! El id no es válido.",request);
            
            mostrarVista("modificarpropiedad",request,response);
            
            return;
        }
        
        try {
            DTPropiedad propiedad = FabricaLogica.getLogicaCliente().buscarPropiedad(id);
         propiedadconst=propiedad;
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
        
        mostrarVista("modificarpropiedad",request,response);
    }
     public void modificarpropiedad_post(HttpServletRequest request, HttpServletResponse response) {
         int id=Integer.parseInt(request.getParameter("idpropiedad"));
         String direccionp = request.getParameter("dirpropiedad");
        String tipop=request.getParameter("tipopropiedad");
     propiedadconst.setIdpropiedad(id);
    propiedadconst.setDirProp(direccionp);
      propiedadconst.setTipoProp(tipop);
        try {
            FabricaLogica.getLogicaCliente().modificarPropiedad(clienteconstante2,propiedadconst);
            
            cargarMensaje("¡Propiedad modificada con éxito!",request.getSession());
            
             mostrarVista("modificarpropiedad",request,response);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("modificarpropiedad",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al Modificar la Propiedad.",request);
            
            mostrarVista("modificarpropiedad",request,response);
        }
    }
    public void agregarclientepropiedad_get(HttpServletRequest request, HttpServletResponse response) {
        mostrarVista("agregarclientepropiedad",request, response);
    }
    public void agregarclientepropiedad_post(HttpServletRequest request, HttpServletResponse response) {
          long cedula = 0;
         String nombre = request.getParameter("nombre");
        String zonabarrio=request.getParameter("zonabarrio");
       String dircliente=request.getParameter("dircliente");
       String telefono=request.getParameter("telefono");
           String direccionp = request.getParameter("dirpropiedad");
        String tipop=request.getParameter("tipopropiedad");
       
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("agregarclientepropiedad",request,response);
            
            return;
        }
      
        DTCliente cliente = new DTCliente();
        cliente.setCedulaCliente(cedula);
        cliente.setNomCliente(nombre);
        cliente.setDirCliente(dircliente);
        cliente.setZonabarrioCliente(zonabarrio);
        cliente.setTelCliente(telefono);
         DTPropiedad p=new DTPropiedad();
         p.setDirProp(direccionp);
         p.setTipoProp(tipop);
       
        try {
            FabricaLogica.getLogicaCliente().agregarCP(cliente,p);
            cargarMensaje("¡Cliente con Propiedad agregado con éxito!",request.getSession());
            response.sendRedirect("clientes");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            mostrarVista("agregarclientepropiedad",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el Cliente.",request);
             mostrarVista("agregarclientepropiedad",request,response);
        }
    }
}
