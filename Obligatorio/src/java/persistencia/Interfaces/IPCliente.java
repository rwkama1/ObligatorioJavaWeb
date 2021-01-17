/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPCliente {
    ArrayList<DTCliente> buscoClientes(String criterio) throws ExcepcionPersonalizada;
     void agregarCliente(DTCliente cliente) throws ExcepcionPersonalizada;
     DTCliente buscarCliente(long cedula) throws ExcepcionPersonalizada;
     ArrayList<DTCliente> listarCliente() throws ExcepcionPersonalizada;
     void modificarCliente(DTCliente cliente) throws ExcepcionPersonalizada;
     ArrayList<DTCliente> listarcpropiedad()throws ExcepcionPersonalizada;
     void agregarCP(DTCliente c,DTPropiedad p)
            throws ExcepcionPersonalizada;
}
