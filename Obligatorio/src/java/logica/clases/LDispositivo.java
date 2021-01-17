/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.clases;


import compartidos.beans.datatypes.DTDipositivoAlarma;
import compartidos.beans.datatypes.DTDispositivo;
import compartidos.beans.datatypes.DTDispositivoCamara;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.datatypes.DTServicioCamara;
import compartidos.beans.excepciones.ExcepcionLogica;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import logica.interfaces.ILDispositivo;
import persistencia.FabricaPersistencia;

/**
 *
 * @author Waldemar
 */
public class LDispositivo implements ILDispositivo{
        private static LDispositivo instancia = null;
    
    
    public static LDispositivo getInstancia() {
        if (instancia == null) {
            instancia = new LDispositivo();
        }
        
        return instancia;
    }
    private LDispositivo() {
        
    }
   
    public void validarDispositivo(DTDispositivo ds)
            throws ExcepcionPersonalizada {
      
        if (ds == null) {
            throw new ExcepcionLogica("El Dispositivo es nulo.");
        } 
        
        if (ds.getUbicacion().trim().equals("")) {
            throw new ExcepcionLogica("La Ubicación no puede quedar vacía");
        }
    }
          @Override
    public void agregarD(DTDispositivo d)
            throws ExcepcionPersonalizada {
        validarDispositivo(d);
        if(d instanceof DTDispositivoCamara)
        {
           FabricaPersistencia.getPDCamara().agregarDCamara((DTDispositivoCamara) d);
        }
        if(d instanceof DTDipositivoAlarma)
        {
           FabricaPersistencia.getPDAlarma().agregarDAlarma((DTDipositivoAlarma) d);
        }
    }
        @Override
    public void eliminarD(DTDispositivo d)
            throws ExcepcionPersonalizada {
        validarDispositivo(d);
        if(d instanceof DTDispositivoCamara)
        {
           FabricaPersistencia.getPDCamara().eliminarDCamara((DTDispositivoCamara) d);
        }
         if(d instanceof DTDipositivoAlarma)
        {
           FabricaPersistencia.getPDAlarma().eliminarDAlarma((DTDipositivoAlarma) d);
        }
      
    }
       
            @Override
    public DTDispositivo buscarD(int n)
            throws ExcepcionPersonalizada {
        DTDispositivo d=null;
        d=FabricaPersistencia.getPDCamara().buscarDCamara(n);
        if(d==null)
        {
            d=FabricaPersistencia.getPDAlarma().buscarDAlarma(n);
        }
        return d;
    }
          @Override
    public ArrayList<DTDispositivoCamara> listarDCamaras()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPDCamara().listarDCamaras();
    }
           @Override
    public ArrayList<DTDipositivoAlarma> listarDAlarmas()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPDAlarma().listarDAlarmas();
    }
           @Override
    public ArrayList<DTDipositivoAlarma> contarDAlarmas(DTServicio s)
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPDAlarma().contarSDAlarmas(s);
    }
        @Override
    public ArrayList<DTDispositivoCamara> contarDCamaras(DTServicio s)
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPDCamara().contarSDCamaras(s);
    }
          @Override
    public void registrarSD(DTDispositivo d)
            throws ExcepcionPersonalizada {
        validarDispositivo(d);
        if(d instanceof DTDispositivoCamara)
        {
           FabricaPersistencia.getPDCamara().registrarSDCamara((DTDispositivoCamara) d);
        }
        else if(d instanceof DTDipositivoAlarma)
        {
           FabricaPersistencia.getPDAlarma().registrarSDAlarma((DTDipositivoAlarma) d);
        }     
    }
        @Override
    public void liberarSD(DTDispositivo d)
            throws ExcepcionPersonalizada {
        validarDispositivo(d);
        if(d instanceof DTDispositivoCamara)
        {
           FabricaPersistencia.getPDCamara().liberarSDCamara((DTDispositivoCamara) d);
        }
        else if(d instanceof DTDipositivoAlarma)
        {
           FabricaPersistencia.getPDAlarma().liberarSDAlarma((DTDipositivoAlarma) d);
        }
       
      
    }
     @Override
      public ArrayList<DTDispositivoCamara> listarRDCamaras()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPDCamara().listarSDCamaras();
    }
      @Override
      public ArrayList<DTDipositivoAlarma> listarRDAlarmas()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPDAlarma().listarSDAlarmas();
    }
}
