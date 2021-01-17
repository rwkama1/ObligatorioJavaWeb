/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Interfaces;


import compartidos.beans.datatypes.DTTecnico;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface IPTecnico {
    void agregarTecnico(DTTecnico tecnico) throws ExcepcionPersonalizada;
     ArrayList<DTTecnico> buscoTecnicos(String criterio) throws ExcepcionPersonalizada;
     DTTecnico buscarTecnico(long cedula) throws ExcepcionPersonalizada;
     void modificarTecnico(DTTecnico tecnico) throws ExcepcionPersonalizada;
     void eliminarTecnico(DTTecnico tecnico) throws ExcepcionPersonalizada;
     ArrayList<DTTecnico> listarTecnico() throws ExcepcionPersonalizada;
    DTTecnico logueotecnico(long cedula,String clave)
            throws ExcepcionPersonalizada;
    ArrayList<DTTecnico> tecnicosEC()
            throws ExcepcionPersonalizada;
    ArrayList<DTTecnico> tecnicosEA()
            throws ExcepcionPersonalizada;
}
