/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Waldemar
 */
public class DTRecibo implements Serializable{
     private int idRecibo;
  private double importetotal;
  private Date fechaFacturacion;
  private ArrayList<DTLineaRecibo> lineasRecibo;
private DTCliente cliente;
private DTCobrador cobrador;

    public DTCliente getCliente() {
        return cliente;
    }

    public void setCliente(DTCliente cliente) {
        this.cliente = cliente;
    }
        
    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public double getImportetotal() {
        return importetotal;
    }

    public void setImportetotal(double importetotal) {
        this.importetotal=importetotal;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public ArrayList<DTLineaRecibo> getLineasRecibo() {
        return lineasRecibo;
    }

    public void setLineasRecibo(ArrayList<DTLineaRecibo> lineasRecibo) {
        this.lineasRecibo = lineasRecibo;
    }

    public DTCobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(DTCobrador cobrador) {
        this.cobrador = cobrador;
    }

    public DTRecibo() {
    }

    public DTRecibo(int idRecibo, double importetotal, Date fechaFacturacion, ArrayList<DTLineaRecibo> lineasRecibo, DTCliente cliente,DTCobrador cobrador) {
      
      setIdRecibo(idRecibo);
       setImportetotal(importetotal);
       setFechaFacturacion(fechaFacturacion);
        setLineasRecibo(lineasRecibo);
        setCliente(cliente);
        setCobrador(cobrador);
    }

  

  
  
}
