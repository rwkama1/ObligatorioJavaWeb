/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;


import compartidos.beans.datatypes.DTCobrador;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPCobrador {
     void agregarCobrador(DTCobrador cobrador) throws ExcepcionPersonalizada;
     ArrayList<DTCobrador> buscoCobradores(String criterio) throws ExcepcionPersonalizada;
    DTCobrador buscarCobrador(long cedula) throws ExcepcionPersonalizada;
     void modificarCobrador(DTCobrador cobrador) throws ExcepcionPersonalizada;
     void eliminarCobrador(DTCobrador cobrador) throws ExcepcionPersonalizada;
     ArrayList<DTCobrador> listarCobrador() throws ExcepcionPersonalizada;
    DTCobrador logueocobrador(long cedula,String clave)
            throws ExcepcionPersonalizada;
}
