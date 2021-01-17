/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.clases;

import compartidos.beans.datatypes.DTRecibo;
import compartidos.beans.excepciones.ExcepcionLogica;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import logica.interfaces.ILRecibo;
import persistencia.FabricaPersistencia;



/**
 *
 * @author Waldemar
 */
public class LRecibo implements ILRecibo{
      private static LRecibo instancia = null;
    
    
    public static LRecibo getInstancia() {
        if (instancia == null) {
            instancia = new LRecibo();
        }
        return instancia;
    }
     private LRecibo() {
        
    }
     
         @Override
    public void agregarR(DTRecibo r)
            throws ExcepcionPersonalizada {
       
           FabricaPersistencia.getrecibo().agregarRecibo(r);
    }
       @Override
      public ArrayList<DTRecibo> listarRecibos()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getrecibo().listarRecibos();
    }
      @Override
      public DTRecibo buscarRecibos(int id)
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getrecibo().buscarRecibos(id);
    }
           @Override
    public void cobrarR(DTRecibo r)
            throws ExcepcionPersonalizada {
       
           FabricaPersistencia.getrecibo().cobrarRecibo(r);
    }
     @Override
      public ArrayList<DTRecibo> listarRSC()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getrecibo().listarRSC();
    }
       @Override
      public ArrayList<DTRecibo> listarRCC()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getrecibo().listarRCC();
    }
         @Override
    public ArrayList<DTRecibo> buscoRecibos(String criterio)
            throws ExcepcionPersonalizada {
        if (criterio == null || criterio.length() == 0) {
            return listarRSC();
        }
        
        return FabricaPersistencia.getrecibo().buscoRecibos(criterio);
    }
}
