/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.clases;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.datatypes.DTServicioCamara;
import compartidos.beans.excepciones.ExcepcionLogica;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import logica.interfaces.ILServicio;
import persistencia.FabricaPersistencia;

/**
 *
 * @author Waldemar
 */
public class LServicio implements ILServicio {
    private static LServicio instancia = null;

    public static LServicio getInstancia() {
        if (instancia == null) {
            instancia = new LServicio();
        }
        return instancia;
    }
     private LServicio() {
         
     }
       @Override
    public void validarServicio(DTServicio servicio)
            throws ExcepcionPersonalizada {
        if (servicio == null) {
            throw new ExcepcionLogica("El servicio es nulo.");
        }
        }
   
        @Override
    public void agregarCS(DTServicio servicio,DTCliente cliente)
            throws ExcepcionPersonalizada {
        validarServicio(servicio);
           FabricaPersistencia.getPSAlarma().agregarCSA((DTServicioAlarma) servicio,cliente);
       
    }
          @Override
    public void agregarServicio(DTServicio servicio)
            throws ExcepcionPersonalizada {
        validarServicio(servicio);
        if(servicio instanceof DTServicioCamara)
        {
           FabricaPersistencia.getPSCamara().agregarServicioCamara((DTServicioCamara) servicio);
        }
        else if(servicio instanceof DTServicioAlarma)
        {
           FabricaPersistencia.getPSAlarma().agregarServicioAlarma((DTServicioAlarma) servicio);
        }
       
        
     
    }
     @Override
    public void desactivarServicio(DTServicio servicio)
            throws ExcepcionPersonalizada {
        validarServicio(servicio);
        if(servicio instanceof DTServicioCamara)
        {
           FabricaPersistencia.getPSCamara().desactivarServicioCamara((DTServicioCamara) servicio);
        }
         else if(servicio instanceof DTServicioAlarma)
        {
           FabricaPersistencia.getPSAlarma().desactivarServicioAlarma((DTServicioAlarma) servicio);
        }
    }
        @Override
    public ArrayList<DTServicioCamara> listarSCamaras()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPSCamara().listarSCamaras();
    }
       @Override
    public  ArrayList<DTServicio> listarSClientes(DTCliente cedula)
            throws ExcepcionPersonalizada {
        ArrayList<DTServicio> lista=new ArrayList<DTServicio>();
        lista.addAll(FabricaPersistencia.getPSAlarma().listarSACliente(cedula));
        lista.addAll(FabricaPersistencia.getPSCamara().listarSCClientes(cedula));
        return lista;
    }
        @Override
    public DTServicio buscarSClientes(DTCliente cedula)
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPSAlarma().buscarSCliente(cedula);
    }
    @Override
      public ArrayList<DTServicioAlarma> listarSAlarmas()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPSAlarma().listarSAlarmas();
    }
        
        @Override
    public DTServicio buscarServicio(int idservicio)
            throws ExcepcionPersonalizada {
              DTServicio ser = null;
              
             ser = FabricaPersistencia.getPSCamara().buscarServicioCamara(idservicio);
              if(ser==null)
             {
                 ser=FabricaPersistencia.getPSAlarma().buscarServicioAlarma(idservicio);
             }
             return ser;
    
    }
  
    }
  


    

