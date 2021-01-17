/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.excepciones;

import java.io.Serializable;


public class ExcepcionPresentacion extends ExcepcionPersonalizada implements Serializable {
    
    public ExcepcionPresentacion() {
        
    }
    
    public ExcepcionPresentacion(String mensaje) {
        super(mensaje);
    }
    
    public ExcepcionPresentacion(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
    
}
