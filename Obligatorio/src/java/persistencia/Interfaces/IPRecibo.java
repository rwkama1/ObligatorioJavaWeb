/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;

import compartidos.beans.datatypes.DTRecibo;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPRecibo {
     void agregarRecibo(DTRecibo recibo)
            throws ExcepcionPersonalizada;
    ArrayList<DTRecibo> listarRecibos()
            throws ExcepcionPersonalizada;
 DTRecibo buscarRecibos(int id)
            throws ExcepcionPersonalizada;
    void cobrarRecibo(DTRecibo recibo)
            throws ExcepcionPersonalizada;
    ArrayList<DTRecibo> listarRSC()
            throws ExcepcionPersonalizada;
    ArrayList<DTRecibo> listarRCC()
            throws ExcepcionPersonalizada;
    ArrayList<DTRecibo> buscoRecibos(String criterio)
            throws ExcepcionPersonalizada;
    
}
