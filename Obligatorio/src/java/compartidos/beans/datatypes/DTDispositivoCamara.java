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
public class DTDispositivoCamara extends DTDispositivo implements Serializable {
        private String exteriorinterior;
    private DTServicioCamara servicioCamara;

    public String getExteriorinterior() {
        return exteriorinterior;
    }

    public void setExteriorinterior(String exteriorinterior) {
        this.exteriorinterior = exteriorinterior;
    }

    public DTServicioCamara getServicioCamara() {
        return servicioCamara;
    }

    public void setServicioCamara(DTServicioCamara servicioCamara) {
        this.servicioCamara = servicioCamara;
    }

    public DTDispositivoCamara() {
        super();
    }

    public DTDispositivoCamara(String exteriorinterior, DTServicioCamara servicioCamara, int ninventario, String ubicacion, DTTecnico tecnico) {
        super(ninventario, ubicacion, tecnico);
      setExteriorinterior(exteriorinterior);
       setServicioCamara(servicioCamara);
    }
    
    
}
