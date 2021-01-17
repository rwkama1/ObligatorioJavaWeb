/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTDispositivoCamara;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioCamara;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPDispositivoCamara {
    void agregarDCamara(DTDispositivoCamara dcamaras)
            throws ExcepcionPersonalizada;
    void eliminarDCamara(DTDispositivoCamara dcamaras)
            throws ExcepcionPersonalizada;
    DTDispositivoCamara buscarDCamara(int numero)
            throws ExcepcionPersonalizada;
    ArrayList<DTDispositivoCamara> listarDCamaras()
            throws ExcepcionPersonalizada;
    void registrarSDCamara(DTDispositivoCamara dcamaras)
            throws ExcepcionPersonalizada;
    void liberarSDCamara(DTDispositivoCamara dcamaras)
            throws ExcepcionPersonalizada;
    ArrayList<DTDispositivoCamara> listarSDCamaras()
            throws ExcepcionPersonalizada;
    ArrayList<DTDispositivoCamara> contarSDCamaras(DTServicio s)
            throws ExcepcionPersonalizada;
}
