/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTAdministrador;

import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPAdministrador {
     void agregarAdministrador(DTAdministrador admin) throws ExcepcionPersonalizada;
     ArrayList<DTAdministrador> buscoAdmins(String criterio) throws ExcepcionPersonalizada;
     DTAdministrador buscarAdmin(long cedula) throws ExcepcionPersonalizada;
     void modificarAdmin(DTAdministrador admin) throws ExcepcionPersonalizada;
     void eliminarAdmin(DTAdministrador admin) throws ExcepcionPersonalizada;
     ArrayList<DTAdministrador> listarAdministrador() throws ExcepcionPersonalizada;
     DTAdministrador logueoadmin(long cedula,String clave)
            throws ExcepcionPersonalizada;
      
}
