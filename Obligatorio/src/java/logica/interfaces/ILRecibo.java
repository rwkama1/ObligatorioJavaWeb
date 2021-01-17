/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.interfaces;

import compartidos.beans.datatypes.DTRecibo;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface ILRecibo {
    
     void agregarR(DTRecibo r)
            throws ExcepcionPersonalizada;
   ArrayList<DTRecibo> listarRecibos()
            throws ExcepcionPersonalizada;
   DTRecibo buscarRecibos(int id)
            throws ExcepcionPersonalizada;
   void cobrarR(DTRecibo r)
            throws ExcepcionPersonalizada;
   ArrayList<DTRecibo> listarRSC()
            throws ExcepcionPersonalizada;
    ArrayList<DTRecibo> listarRCC()
            throws ExcepcionPersonalizada;
    ArrayList<DTRecibo> buscoRecibos(String criterio)
            throws ExcepcionPersonalizada; 
}
