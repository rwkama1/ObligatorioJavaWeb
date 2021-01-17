/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.clases;

import compartidos.beans.datatypes.DTAdministrador;
import compartidos.beans.datatypes.DTCobrador;
import compartidos.beans.datatypes.DTEmpleado;
import compartidos.beans.datatypes.DTTecnico;
import compartidos.beans.excepciones.ExcepcionLogica;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import logica.interfaces.ILEmpleado;
import persistencia.FabricaPersistencia;


/**
 *
 * @author Waldemar
 */
public class LEmpleado implements ILEmpleado{
     private static LEmpleado instancia = null;
    
    
    public static LEmpleado getInstancia() {
        if (instancia == null) {
            instancia = new LEmpleado();
        }
        
        return instancia;
    }
    private LEmpleado() {
        
    }
     @Override
    public void validarEmpleado(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        if (empleado == null) {
            throw new ExcepcionLogica("El empleado es nulo.");
        }
        
        if (empleado.getCedulaEmp()< 1) {
            throw new ExcepcionLogica("La cédula debe ser mayor o igual a 1.");
        }
        
        
        if (empleado.getNomEmp().trim().equals("")) {
            throw new ExcepcionLogica("El nombre no puede quedar vacío.");
        }
         if (empleado.getClaveEmp().trim().equals("")) {
            throw new ExcepcionLogica("La Clave no puede quedar vacía.");
        }
           if (empleado.getClaveEmp().length()>50) {
            throw new ExcepcionLogica("La clave no puede exceder los 50 caracteres de longitud.");
        }
        if (empleado.getNomEmp().length() > 70) {
            throw new ExcepcionLogica("El nombre no puede exceder los 70 caracteres de longitud.");
        }
        
        if (empleado.getSueldoEmp()< 0) {
            throw new ExcepcionLogica("El sueldo debe ser mayor o igual a 0.");
        }
        if(empleado instanceof DTCobrador)
        {
            if(((DTCobrador) empleado).getTranporteCobrador().length()>50)
            {
             throw new ExcepcionLogica("El transporte no puede exceder los 50 caracteres de longitud.");
            }
        }
        
        
    }
  
      @Override
    public void agregarEmpleado(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        validarEmpleado(empleado);
        if(empleado instanceof DTAdministrador)
        {
           FabricaPersistencia.getPAdministrador().agregarAdministrador((DTAdministrador) empleado);
        }
        else if(empleado instanceof DTCobrador)
        {
            FabricaPersistencia.getPCobrador().agregarCobrador((DTCobrador)empleado);
        }
         else if(empleado instanceof DTTecnico)
        {
            FabricaPersistencia.getPTecnico().agregarTecnico((DTTecnico)empleado);
        }
        
     
    }
     @Override
    public void modificarEmpleado(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        validarEmpleado(empleado);
        
       if(empleado instanceof DTAdministrador)
        {
           FabricaPersistencia.getPAdministrador().modificarAdmin((DTAdministrador) empleado);
        }
        else if(empleado instanceof DTCobrador)
        {
            FabricaPersistencia.getPCobrador().modificarCobrador((DTCobrador)empleado);
        }
        else if(empleado instanceof DTTecnico)
        {
            FabricaPersistencia.getPTecnico().modificarTecnico((DTTecnico)empleado);
        }
    }
     @Override
    public void eliminarEmpleado(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        validarEmpleado(empleado);
        
       if(empleado instanceof DTAdministrador)
        {
           FabricaPersistencia.getPAdministrador().eliminarAdmin((DTAdministrador) empleado);
        }
        else if(empleado instanceof DTCobrador)
        {
            FabricaPersistencia.getPCobrador().eliminarCobrador((DTCobrador)empleado);
        }
        else if(empleado instanceof DTTecnico)
        {
            FabricaPersistencia.getPTecnico().eliminarTecnico((DTTecnico)empleado);
        }
    }
      @Override
    public DTEmpleado buscarEmpleado(long cedula)
            throws ExcepcionPersonalizada {
              DTEmpleado emp = null;
              
             emp = FabricaPersistencia.getPAdministrador().buscarAdmin(cedula);
          
             if(emp==null)
             {
                 emp=FabricaPersistencia.getPCobrador().buscarCobrador(cedula);
             }
            if(emp == null)
             {
                  emp=FabricaPersistencia.getPTecnico().buscarTecnico(cedula);
             }
             return emp;
    
    }
       @Override
     public DTEmpleado logueoEmpleado(long cedula,String clave)
            throws ExcepcionPersonalizada {
              DTEmpleado emp = null;  
             emp = FabricaPersistencia.getPAdministrador().logueoadmin(cedula,clave);
             if(emp==null)
             {emp=FabricaPersistencia.getPTecnico().logueotecnico(cedula, clave);} 
              if(emp == null)
             {
              emp=FabricaPersistencia.getPCobrador().logueocobrador(cedula,clave);
             }
             return emp;
    
    }
    //Tecnicos
    @Override
    public ArrayList<DTTecnico> buscoTecnicos(String criterio)
            throws ExcepcionPersonalizada {
 
        if (criterio == null || criterio.length() == 0) {
            return listarTecnico();
        }
        return FabricaPersistencia.getPTecnico().buscoTecnicos(criterio);
    }
      @Override
    public ArrayList<DTTecnico> listarTecnico()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPTecnico().listarTecnico();
    }
      public ArrayList<DTTecnico> tecnicoEC()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPTecnico().tecnicosEC();
    }
         public ArrayList<DTTecnico> tecnicoEA()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPTecnico().tecnicosEA();
    }
      //Cobradores
      @Override
    public ArrayList<DTCobrador> buscoCobradores(String criterio)
            throws ExcepcionPersonalizada {
 
        if (criterio == null || criterio.length() == 0) {
            return listarCobrador();
        }
        return FabricaPersistencia.getPCobrador().buscoCobradores(criterio);
    }
      @Override
    public ArrayList<DTCobrador> listarCobrador()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPCobrador().listarCobrador();
    }
    
    
    
    //Administradores
     @Override
    public ArrayList<DTAdministrador> buscoAdmins(String criterio)
            throws ExcepcionPersonalizada {
 
        if (criterio == null || criterio.length() == 0) {
            return listarAdmins();
        }
        return FabricaPersistencia.getPAdministrador().buscoAdmins(criterio);
    }
     @Override
    public ArrayList<DTAdministrador> listarAdmins()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPAdministrador().listarAdministrador();
    }
    
    
}
