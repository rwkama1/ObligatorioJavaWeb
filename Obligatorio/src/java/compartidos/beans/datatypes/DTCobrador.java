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
public class DTCobrador  extends DTEmpleado implements Serializable{
      private String tranporteCobrador;

    public String getTranporteCobrador() {
        return tranporteCobrador;
    }

    public void setTranporteCobrador(String tranporteCobrador) {
        this.tranporteCobrador = tranporteCobrador;
    }

    public DTCobrador() {
       super();
    }

    public DTCobrador(String tranporteCobrador, long cedulaEmp, String nomEmp, String claveEmp, double sueldoEmp, Date fechaEmp) {
        super(cedulaEmp, nomEmp, claveEmp, sueldoEmp, fechaEmp);
          setTranporteCobrador(tranporteCobrador); 
    }

   
    
}
