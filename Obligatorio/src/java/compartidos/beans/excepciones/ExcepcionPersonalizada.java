/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.excepciones;

import java.io.Serializable;


public class ExcepcionPersonalizada extends Exception implements Serializable {
    
    public ExcepcionPersonalizada() {
        
    }
    
    public ExcepcionPersonalizada(String mensaje) {
        super(mensaje);
    }
    
    public ExcepcionPersonalizada(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
    
}
