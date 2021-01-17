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
public class DTServicioAlarma extends DTServicio implements Serializable{
      private int codigoanulacion;

    public int getCodigoanulacion() {
        return codigoanulacion;
    }

    public void setCodigoanulacion(int codigoanulacion) {
        this.codigoanulacion = codigoanulacion;
    }

    public DTServicioAlarma() {
        super();
    }

    public DTServicioAlarma(int codigoanulacion, int idServicio, Date fechaServicio, boolean moniteroServicio, DTPropiedad propServicio, DTCliente clienteServicio) {
        super(idServicio, fechaServicio, moniteroServicio, propServicio, clienteServicio);
       setCodigoanulacion(codigoanulacion);
    }

  
    
}
