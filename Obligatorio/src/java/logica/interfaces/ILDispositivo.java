/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.interfaces;

import compartidos.beans.datatypes.DTDipositivoAlarma;
import compartidos.beans.datatypes.DTDispositivo;
import compartidos.beans.datatypes.DTDispositivoCamara;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.datatypes.DTServicioCamara;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface ILDispositivo {
    void agregarD(DTDispositivo dcamara)
            throws ExcepcionPersonalizada;
    void eliminarD(DTDispositivo dcamara)
            throws ExcepcionPersonalizada;
    DTDispositivo buscarD(int n)
            throws ExcepcionPersonalizada;
    ArrayList<DTDispositivoCamara> listarDCamaras() throws ExcepcionPersonalizada;
    ArrayList<DTDipositivoAlarma> listarDAlarmas()
            throws ExcepcionPersonalizada;
    void registrarSD(DTDispositivo d)
            throws ExcepcionPersonalizada;
    void liberarSD(DTDispositivo d)
            throws ExcepcionPersonalizada;
    ArrayList<DTDispositivoCamara> listarRDCamaras()
            throws ExcepcionPersonalizada;
    ArrayList<DTDipositivoAlarma> listarRDAlarmas()
            throws ExcepcionPersonalizada;
    ArrayList<DTDipositivoAlarma> contarDAlarmas(DTServicio s)
            throws ExcepcionPersonalizada;
    ArrayList<DTDispositivoCamara> contarDCamaras(DTServicio s)
            throws ExcepcionPersonalizada;
    
}
