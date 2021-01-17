/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.excepciones;

import java.io.Serializable;

public class ExcepcionPersistencia extends ExcepcionPersonalizada implements Serializable {
    
    public ExcepcionPersistencia() {
        
    }
    
    public ExcepcionPersistencia(String mensaje) {
        super(mensaje);
    }
    
    public ExcepcionPersistencia(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
    
}
