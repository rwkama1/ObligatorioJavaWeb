/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.datatypes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Waldemar
 */
public class DTServicioCamara extends DTServicio implements Serializable{
      private boolean terminalgrabacion;

    public boolean isTerminalgrabacion() {
        return terminalgrabacion;
    }

    public void setTerminalgrabacion(boolean terminalgrabacion) {
        this.terminalgrabacion = terminalgrabacion;
    }

    public DTServicioCamara() {
        super();
    }

    public DTServicioCamara(boolean terminalgrabacion, int idServicio, Date fechaServicio, boolean moniteroServicio, DTPropiedad propServicio, DTCliente clienteServicio) {
        super(idServicio, fechaServicio, moniteroServicio, propServicio, clienteServicio);
        setTerminalgrabacion(terminalgrabacion);
    }

  
    
    
}
