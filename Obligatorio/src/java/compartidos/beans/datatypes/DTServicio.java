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
public class DTServicio implements Serializable{
    private int idServicio;
    private Date fechaServicio;
    private boolean moniteroServicio;
    private DTPropiedad propServicio;
    private DTCliente clienteServicio;
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public boolean isMoniteroServicio() {
        return moniteroServicio;
    }

    public void setMoniteroServicio(boolean moniteroServicio) {
        this.moniteroServicio = moniteroServicio;
    }

    public DTPropiedad getPropServicio() {
        return propServicio;
    }

    public void setPropServicio(DTPropiedad propServicio) {
        this.propServicio = propServicio;
    }

    public DTCliente getClienteServicio() {
        return clienteServicio;
    }

    public void setClienteServicio(DTCliente clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    public DTServicio() {
    }

    public DTServicio(int idServicio, Date fechaServicio, boolean moniteroServicio, DTPropiedad propServicio, DTCliente clienteServicio) {
       setIdServicio(idServicio);
       setFechaServicio(fechaServicio);
       setMoniteroServicio(moniteroServicio);
       setPropServicio(propServicio);
       setClienteServicio(clienteServicio);
    }

     
    
}
