/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.interfaces;

import compartidos.beans.datatypes.DTAdministrador;
import compartidos.beans.datatypes.DTCobrador;
import compartidos.beans.datatypes.DTEmpleado;
import compartidos.beans.datatypes.DTTecnico;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public interface ILEmpleado {
     ArrayList<DTCobrador> buscoCobradores(String criterio)throws ExcepcionPersonalizada;
    ArrayList<DTCobrador> listarCobrador()throws ExcepcionPersonalizada;
    ArrayList<DTAdministrador> buscoAdmins(String criterio)throws ExcepcionPersonalizada;
    ArrayList<DTAdministrador> listarAdmins()throws ExcepcionPersonalizada;
       void validarEmpleado(DTEmpleado empleado) throws ExcepcionPersonalizada;
    void agregarEmpleado(DTEmpleado empleado)throws ExcepcionPersonalizada;
    void modificarEmpleado(DTEmpleado empleado)throws ExcepcionPersonalizada;
    void eliminarEmpleado(DTEmpleado empleado)throws ExcepcionPersonalizada;
    DTEmpleado buscarEmpleado(long cedula)throws ExcepcionPersonalizada;
    ArrayList<DTTecnico> buscoTecnicos(String criterio)throws ExcepcionPersonalizada;
     ArrayList<DTTecnico> listarTecnico()throws ExcepcionPersonalizada;
     DTEmpleado logueoEmpleado(long cedula,String clave)
            throws ExcepcionPersonalizada;
     ArrayList<DTTecnico> tecnicoEC()
            throws ExcepcionPersonalizada;
     ArrayList<DTTecnico> tecnicoEA()
            throws ExcepcionPersonalizada;
}
