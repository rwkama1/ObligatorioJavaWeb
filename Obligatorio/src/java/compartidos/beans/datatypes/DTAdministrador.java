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
public class DTAdministrador extends DTEmpleado implements Serializable{

    public DTAdministrador() {
        super();
    }

    public DTAdministrador(long cedulaEmp, String nomEmp, String claveEmp, double sueldoEmp, Date fechaEmp) {
        super(cedulaEmp, nomEmp, claveEmp, sueldoEmp, fechaEmp);
    }
    
}
