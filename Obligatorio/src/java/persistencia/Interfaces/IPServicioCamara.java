/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTServicioCamara;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPServicioCamara {
    

    void agregarServicioCamara(DTServicioCamara scamaras)
            throws ExcepcionPersonalizada;
    void desactivarServicioCamara(DTServicioCamara scamaras) 
            throws ExcepcionPersonalizada;
    DTServicioCamara buscarServicioCamara(int idservicio) 
            throws ExcepcionPersonalizada;
    ArrayList<DTServicioCamara> listarSCamaras() 
            throws ExcepcionPersonalizada;
  void agregarCSC(DTServicioCamara sc,DTCliente c)
            throws ExcepcionPersonalizada;
    ArrayList<DTServicioCamara> listarSCClientes(DTCliente cedula)
            throws ExcepcionPersonalizada ;
}
