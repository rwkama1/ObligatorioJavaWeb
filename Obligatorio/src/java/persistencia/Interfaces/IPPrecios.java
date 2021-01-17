/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTPrecios;
import compartidos.beans.excepciones.ExcepcionPersistencia;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPPrecios {
   DTPrecios listarPrecios(String ruta) throws ExcepcionPersistencia;
    void actualizarPrecios(DTPrecios p,String ruta) throws ExcepcionPersistencia;
}
