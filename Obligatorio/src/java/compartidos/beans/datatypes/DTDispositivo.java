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
public class DTDispositivo implements Serializable{
      private int ninventario;
    private String ubicacion;
    private DTTecnico tecnico;

    public int getNinventario() {
        return ninventario;
    }

    public void setNinventario(int ninventario) {
        this.ninventario = ninventario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public DTTecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(DTTecnico tecnico) {
        this.tecnico = tecnico;
    }

    public DTDispositivo() {
    }

    public DTDispositivo(int ninventario, String ubicacion, DTTecnico tecnico) {
        setNinventario(ninventario);
      setUbicacion(ubicacion);
        setTecnico(tecnico);
    }

  
    
}
