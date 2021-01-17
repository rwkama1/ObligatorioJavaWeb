/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.interfaces;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface ILCliente {

    void agregarCliente(DTCliente cliente)throws ExcepcionPersonalizada;
    void modificarCliente(DTCliente cliente)throws ExcepcionPersonalizada;
    ArrayList<DTCliente> buscoClientes(String criterio)throws ExcepcionPersonalizada;
    ArrayList<DTCliente> listarCliente()throws ExcepcionPersonalizada;
    DTCliente buscarCliente(long cedula)throws ExcepcionPersonalizada;
    void agregarPropiedad(DTCliente cliente) throws ExcepcionPersonalizada;
    DTPropiedad buscarPropiedad(int id) throws ExcepcionPersonalizada;
    void modificarPropiedad(DTCliente cliente,DTPropiedad prop)throws ExcepcionPersonalizada;
     ArrayList<DTCliente> listarcpropiedad()
            throws ExcepcionPersonalizada;
    void agregarCP(DTCliente cliente,DTPropiedad p)
            throws ExcepcionPersonalizada;
}
