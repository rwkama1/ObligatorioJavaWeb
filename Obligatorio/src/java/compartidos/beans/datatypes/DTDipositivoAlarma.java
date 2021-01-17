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
public class DTDipositivoAlarma extends DTDispositivo implements Serializable{
     private DTServicioAlarma servicioAlarma;

    public DTServicioAlarma getServicioAlarma() {
        return servicioAlarma;
    }

    public void setServicioAlarma(DTServicioAlarma servicioAlarma) {
        this.servicioAlarma = servicioAlarma;
    }

    public DTDipositivoAlarma() {
        super();
    }

    public DTDipositivoAlarma(DTServicioAlarma servicioAlarma, int ninventario, String ubicacion, DTTecnico tecnico) {
        super(ninventario, ubicacion, tecnico);
        setServicioAlarma(servicioAlarma);
    }
     
}
