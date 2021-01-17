/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTDipositivoAlarma;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPDispositivoAlarma {
     void agregarDAlarma(DTDipositivoAlarma dalarma)
            throws ExcepcionPersonalizada;
     void eliminarDAlarma(DTDipositivoAlarma dalarma)
            throws ExcepcionPersonalizada;
     DTDipositivoAlarma buscarDAlarma(int numero)
            throws ExcepcionPersonalizada;
     ArrayList<DTDipositivoAlarma> listarDAlarmas()
            throws ExcepcionPersonalizada;
    void registrarSDAlarma(DTDipositivoAlarma dalarmas)
            throws ExcepcionPersonalizada;
    void liberarSDAlarma(DTDipositivoAlarma dalarmas)
            throws ExcepcionPersonalizada;
    ArrayList<DTDipositivoAlarma> listarSDAlarmas()
            throws ExcepcionPersonalizada;
    ArrayList<DTDipositivoAlarma> contarSDAlarmas(DTServicio s)
            throws ExcepcionPersonalizada;
}
