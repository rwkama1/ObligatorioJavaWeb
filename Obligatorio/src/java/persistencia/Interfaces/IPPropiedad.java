/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.excepciones.ExcepcionPersonalizada;


/**
 *
 * @author Waldemar
 */
public interface IPPropiedad {
    void agregarPropiedad(DTCliente cliente)throws ExcepcionPersonalizada;
    DTPropiedad buscarPropiedad(int id)throws ExcepcionPersonalizada;
    void modificarPropiedad(DTCliente cliente,DTPropiedad prop)throws ExcepcionPersonalizada;
}
