/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.datatypes;

import java.io.Serializable;

/**
 *
 * @author Waldemar
 */
public class DTLineaRecibo implements Serializable{
         private int idLinea;
      private double importeLinea;
      private DTServicio servicelinea;

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public double getImporteLinea() {
        return importeLinea;
    }

    public void setImporteLinea(double importeLinea) {
        this.importeLinea = importeLinea;
    }

    public DTServicio getServicelinea() {
        return servicelinea;
    }

    public void setServicelinea(DTServicio servicelinea) {
        this.servicelinea = servicelinea;
    }

    public DTLineaRecibo() {
    }

    public DTLineaRecibo(int idLinea, double importeLinea, DTServicio servicelinea) {
      setIdLinea(idLinea);
      setImporteLinea(importeLinea);
      setServicelinea(servicelinea);
    }
    
}
