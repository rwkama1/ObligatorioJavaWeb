/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.clases;


import compartidos.beans.datatypes.DTPrecios;
import compartidos.beans.excepciones.ExcepcionLogica;
import compartidos.beans.excepciones.ExcepcionPersonalizada;


import logica.interfaces.ILPrecios;
import persistencia.FabricaPersistencia;

/**
 *
 * @author Waldemar
 */
public class LPrecios implements ILPrecios{
       private static LPrecios instancia = null;

    public static LPrecios getInstancia() {
        if (instancia == null) {
            instancia = new LPrecios();
        }
        return instancia;
    }
     private LPrecios() {
         
     }
    public void validarPrecios(DTPrecios p)
            throws ExcepcionPersonalizada {

        if (p.getPbaseCamara()< 1) {
            throw new ExcepcionLogica("El Precio Base de Camara no puede ser negativo");
        }
        if (p.getPbaseAlarma()< 1) {
            throw new ExcepcionLogica("El Precio Base de Alarma no puede ser negativo");
        }
        if (p.getAdicionalAlarma()< 1) {
            throw new ExcepcionLogica("El Adicional de Alarma no puede ser negativo");
        }
        if (p.getAdicionalCamara()< 1) {
            throw new ExcepcionLogica("El Adicional de Camara no puede ser negativo");
        }
        if (p.getTmonitoreoAlarma()<1) {
            throw new ExcepcionLogica("La Tasa de Monitoreo de Alarma no puede ser negativo");
        }
          if (p.getTmonitoreoAlarma()>100) {
            throw new ExcepcionLogica("La Tasa de Monitoreo de Alarma no puede superar el 100%");
        }
      if (p.getTmonitoreoCamara()<1) {
            throw new ExcepcionLogica("La Tasa de Monitoreo de Camara no puede ser negativo");
        }
          if (p.getTmonitoreoCamara()>100) {
            throw new ExcepcionLogica("La Tasa de Monitoreo de Camara no puede superar el 100%");
        }
        
    }
       @Override
      public  DTPrecios listarPrecios(String ruta)
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPPrecios().listarPrecios(ruta);
    }
      @Override
     public void actualizarPrecios(DTPrecios precio,String ruta)    
            throws ExcepcionPersonalizada {
         validarPrecios(precio);
       FabricaPersistencia.getPPrecios().actualizarPrecios(precio,ruta);
    }
    
}
