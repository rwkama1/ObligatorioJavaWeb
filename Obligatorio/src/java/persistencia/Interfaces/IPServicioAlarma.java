/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPServicioAlarma {
     void agregarServicioAlarma(DTServicioAlarma salarmas) 
             throws ExcepcionPersonalizada;
    void desactivarServicioAlarma(DTServicioAlarma salarmas) 
            throws ExcepcionPersonalizada;
    DTServicioAlarma buscarServicioAlarma(int idservicio) 
            throws ExcepcionPersonalizada;
    ArrayList<DTServicioAlarma> listarSAlarmas() 
            throws ExcepcionPersonalizada;
   ArrayList<DTServicioAlarma> listarSACliente(DTCliente cedula)
            throws ExcepcionPersonalizada;
    void agregarCSA(DTServicioAlarma sa,DTCliente c)
            throws ExcepcionPersonalizada;
     DTServicio buscarSCliente(DTCliente cedula)
            throws ExcepcionPersonalizada;
    
 
}
