/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.interfaces;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.datatypes.DTServicioCamara;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface ILServicio {
    

  void validarServicio(DTServicio servicio) 
            throws ExcepcionPersonalizada;
    void agregarServicio(DTServicio servicio)
            throws ExcepcionPersonalizada; 
    void desactivarServicio(DTServicio servicio)
            throws ExcepcionPersonalizada;
    ArrayList<DTServicioCamara> listarSCamaras() 
            throws ExcepcionPersonalizada; 
    DTServicio buscarServicio(int idservicio)
            throws ExcepcionPersonalizada;
     ArrayList<DTServicioAlarma> listarSAlarmas()
             throws ExcepcionPersonalizada;
 ArrayList<DTServicio> listarSClientes(DTCliente cedula)
            throws ExcepcionPersonalizada;
void agregarCS(DTServicio servicio,DTCliente cliente)
            throws ExcepcionPersonalizada;
DTServicio buscarSClientes(DTCliente cedula)
            throws ExcepcionPersonalizada;

    
}
