/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.interfaces;

import compartidos.beans.datatypes.DTPrecios;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface ILPrecios {
DTPrecios listarPrecios(String ruta)
            throws ExcepcionPersonalizada;
   void actualizarPrecios(DTPrecios precio,String ruta)
            throws ExcepcionPersonalizada;
}
