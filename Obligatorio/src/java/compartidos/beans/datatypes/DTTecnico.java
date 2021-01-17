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
public class DTTecnico extends DTEmpleado implements Serializable{
        private boolean espCamaras;
    private boolean espAlarmas;

    public boolean getEspCamaras() {
        return espCamaras;
    }

    public void setEspCamaras(boolean espCamaras) {
        this.espCamaras = espCamaras;
    }

    public boolean getEspAlarmas() {
        return espAlarmas;
    }

    public void setEspAlarmas(boolean espAlarmas) {
        this.espAlarmas = espAlarmas;
    }

    public DTTecnico() {
        super();
    }

    public DTTecnico(boolean espCamaras, boolean espAlarmas, long cedulaEmp, String nomEmp, String claveEmp, double sueldoEmp, Date fechaEmp) {
        super(cedulaEmp, nomEmp, claveEmp, sueldoEmp, fechaEmp);
       setEspCamaras(espCamaras);
       setEspAlarmas(espAlarmas);
    }

   
    
    
    
}
