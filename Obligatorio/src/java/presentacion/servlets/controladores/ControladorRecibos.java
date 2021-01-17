/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.servlets.controladores;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTDipositivoAlarma;
import compartidos.beans.datatypes.DTDispositivoCamara;
import compartidos.beans.datatypes.DTLineaRecibo;
import compartidos.beans.datatypes.DTPrecios;
import compartidos.beans.datatypes.DTRecibo;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.datatypes.DTServicioCamara;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.FabricaLogica;

/**
 *
 * @author Waldemar
 */
public class ControladorRecibos extends Controlador{
   private ArrayList<DTServicio> servicios=null;
   private DTServicio servicio=null;
  private DTPrecios pr=null;
  DTCliente localcliente =null;
   ArrayList<DTDipositivoAlarma> da =null;
     ArrayList<DTDispositivoCamara> dc=null;
     
     public void index_get(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArrayList<DTCliente> clientes = FabricaLogica.getLogicaCliente().listarcpropiedad();
            
            request.setAttribute("clientes", clientes);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Clientes.",request);
        }
        
       mostrarVista("index", request, response);
    }
     public void generarrecibo_get(HttpServletRequest request, HttpServletResponse response) {
    
    
         long cedula;
        try {
            cedula = Long.parseLong(request.getParameter("cedula"));
        } catch (NumberFormatException ex) {
            cargarMensaje("¡ERROR! La cédula no es válida.",request);
            
            mostrarVista("generarrecibo",request,response);
            
            return;
        }
           try {
             localcliente = FabricaLogica.getLogicaCliente().buscarCliente(cedula);
              
            if (localcliente != null) {
                request.getSession().setAttribute("cliente", localcliente);
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
            try {
        servicios = FabricaLogica.getLogicaServicio().listarSClientes(localcliente);
            
            request.setAttribute("servicios", servicios);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los servicios.",request);
        }
        
                 try {
        servicio = FabricaLogica.getLogicaServicio().buscarSClientes(localcliente);
            
            request.setAttribute("servicio", servicio);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los servicio.",request);
        }
           try {
         
            da = FabricaLogica.getLogicaD().contarDAlarmas(servicio);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los DipositivoAlarma.",request);
        }
         try {
          
           dc = FabricaLogica.getLogicaD().contarDCamaras(servicio);
            request.setAttribute("dc", dc);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los DipositivoCamara.",request);
        }
        
       mostrarVista("generarrecibo", request, response);
    }
     public void generarrecibo_post(HttpServletRequest request, HttpServletResponse response) {
         int cantalarmas=da.size();
         int cantcamaras=dc.size();
         int cantdias=0;
           DTLineaRecibo linea=null; 
             DTRecibo recibo=new DTRecibo();
           double total=0;
           double precioalarma=0;
           double preciocamara=0;
           double tasaalarma=0;
           double tasacamara=0;
           double importe=0;
            double aalarma=0;
          double acamara=0;
         
      try {
     ServletContext context = getServletContext();
     String fullPath = context.getRealPath("/WEB-INF/precios.txt");
      pr = FabricaLogica.getLP().listarPrecios(fullPath);
            request.setAttribute("pr", pr);
           
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Precios.",request);
        }
       precioalarma=pr.getPbaseAlarma();
          preciocamara=pr.getPbaseCamara();
          tasaalarma=pr.getTmonitoreoAlarma();
          tasacamara=pr.getTmonitoreoCamara();
          aalarma=pr.getAdicionalAlarma();
          acamara=pr.getAdicionalCamara();         
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
              try {
              fecha=formatoDelTexto.parse(request.getParameter("fecha"));
          } catch (ParseException ex) {
             cargarMensaje("¡ERROR! La fecha no es válida.",request);
            mostrarVista("generarrecibo",request,response);
            
            return;
          }      
             ArrayList<DTLineaRecibo> lista=new  ArrayList<>();
             for(DTServicio serv:servicios)
             {
                    servicio=serv;
                   recibo.setFechaFacturacion(fecha);
                 cantdias=(int) ((fecha.getTime()-serv.getFechaServicio().getTime())/86400000);
                 if(servicio instanceof DTServicioAlarma){
                      
                     
                       if(serv.isMoniteroServicio()==true)
                       {
                         importe=((((precioalarma/100)*tasaalarma)+(aalarma*cantalarmas))/30)*cantdias;
                       }
                       else
                       importe=((precioalarma+(aalarma*cantalarmas))/30)*cantdias;
                   }
              else if(servicio instanceof  DTServicioCamara)
                   {
                       
                       if(serv.isMoniteroServicio()==true)
                       {
                            importe=((((preciocamara/100)*tasacamara)+(acamara*cantcamaras))/30)*cantdias;
                       }
                       else
                       {
                       importe=((preciocamara+(acamara*cantcamaras))/30)*cantdias;
                       }
                       
                   }
                 linea=new DTLineaRecibo(0,importe,serv);
                  lista.add(linea); 
                 total=total+importe;
              }
              
         
             recibo.setImportetotal(total);
           
             recibo.setLineasRecibo(lista);
             recibo.setCliente(localcliente);
      
     try {
            FabricaLogica.getR().agregarR(recibo);
            cargarMensaje("¡Recibo agregado con éxito!",request.getSession());
            response.sendRedirect("recibos");
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
            
            mostrarVista("generarrecibo",request,response);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al agregar el Recibo.",request);
            
            mostrarVista("generarrecibo",request,response);
        }
     
    }
     public void listarecibo_get(HttpServletRequest request, HttpServletResponse response)
       {
            try {
            ArrayList<DTRecibo> recibos = FabricaLogica.getR().listarRecibos();
            
            request.setAttribute("recibos", recibos);
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al mostrar los Recibos.",request);
        }
        
       mostrarVista("listarecibo", request, response); 
       }
     public void verrecibo_get(HttpServletRequest request, HttpServletResponse response)
     {
             int id;
            id = Integer.parseInt(request.getParameter("id"));
        
        try {
            DTRecibo recibo = FabricaLogica.getR().buscarRecibos(id);
       
            if (recibo != null) {
                request.setAttribute("recibo", recibo);
                cargarMensaje("¡Recibo encontrado!",request);
            } else {
                cargarMensaje("¡ERROR! No se encontró ningún recibo con el id " + id + ".",request);
            }
        } catch (ExcepcionPersonalizada ex) {
            cargarMensaje("¡ERROR! " + ex.getMessage(),request);
        } catch (Exception ex) {
            cargarMensaje("¡ERROR! Se produjo un error al buscar el Recibo.",request);
        }
        
        mostrarVista("verrecibo",request,response);
     }
    
}
