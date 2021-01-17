/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.clases;



import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;

import compartidos.beans.excepciones.ExcepcionLogica;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.ArrayList;
import logica.interfaces.ILCliente;
import persistencia.FabricaPersistencia;

/**
 *
 * @author Waldemar
 */
public class LCliente implements ILCliente {
        private static LCliente instancia = null;
    
    
    public static LCliente getInstancia() {
        if (instancia == null) {
            instancia = new LCliente();
        }
        
        return instancia;
    }
    private LCliente() {
        
    }

    public void validarCliente(DTCliente cliente)
            throws ExcepcionPersonalizada {
        if (cliente == null) {
            throw new ExcepcionLogica("El Cliente es nulo.");
        }
        
        if (cliente.getCedulaCliente()< 1) {
            throw new ExcepcionLogica("La cédula debe ser mayor o igual a 1.");
        }

        if (cliente.getNomCliente().trim().equals("")) {
            throw new ExcepcionLogica("El nombre no puede quedar vacío.");
        }
        if (cliente.getNomCliente().length()>50) {
            throw new ExcepcionLogica("El Nombre no puede exceder los 50 caracteres de longitud.");
        }
         if (cliente.getDirCliente().trim().equals("")) {
            throw new ExcepcionLogica("La Direccion no puede quedar vacía.");
        }
           if (cliente.getDirCliente().length()>80) {
            throw new ExcepcionLogica("La Direccion no puede exceder los 50 caracteres de longitud.");
        }
        if (cliente.getZonabarrioCliente().length() > 80) {
            throw new ExcepcionLogica("El nombre no puede exceder los 70 caracteres de longitud.");
        }
        if (cliente.getZonabarrioCliente().trim().equals("")) {
            throw new ExcepcionLogica("La Zona o Barrio no puede quedar vacía.");
        }     
    }
   public void validarPropiedad(DTPropiedad p)
            throws ExcepcionPersonalizada {
       if (p.getDirProp().trim().equals("")) {
             throw new ExcepcionLogica("Direccion de Propiedad no puede quedar vácia ");
        }
        if (p.getTipoProp().trim().equals("")) {
             throw new ExcepcionLogica("Tipo de Propiedad no puede quedar vácia ");
        }
    }
     
     
       //Propiedades
       @Override
    public void agregarPropiedad(DTCliente cliente)
            throws ExcepcionPersonalizada {
        validarCliente(cliente);
        validarPropiedad(cliente.getPropCliente().get(cliente.getPropCliente().size()-1));
        FabricaPersistencia.getPPropiedad().agregarPropiedad(cliente);
    }
       @Override
    public void modificarPropiedad(DTCliente cliente,DTPropiedad prop)
            throws ExcepcionPersonalizada {
        validarCliente(cliente);
         validarPropiedad(prop);
        FabricaPersistencia.getPPropiedad().modificarPropiedad(cliente,prop);
    }
    @Override
      public DTPropiedad buscarPropiedad(int id)
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPPropiedad().buscarPropiedad(id);
    }
    
  
       //Clientes
      @Override
    public void agregarCliente(DTCliente cliente)
            throws ExcepcionPersonalizada {
        validarCliente(cliente);
        FabricaPersistencia.getPCliente().agregarCliente(cliente);
    }
      @Override
    public void agregarCP(DTCliente cliente,DTPropiedad p)
            throws ExcepcionPersonalizada {
        validarCliente(cliente);
        validarPropiedad(p);
        FabricaPersistencia.getPCliente().agregarCP(cliente,p);
    }
     @Override
     public void modificarCliente(DTCliente cliente)
            throws ExcepcionPersonalizada {
        validarCliente(cliente);
        FabricaPersistencia.getPCliente().modificarCliente(cliente);
    }
      @Override
    public ArrayList<DTCliente> buscoClientes(String criterio)
            throws ExcepcionPersonalizada {
        if (criterio == null || criterio.length() == 0) {
            return listarCliente();
        }
        
        return FabricaPersistencia.getPCliente().buscoClientes(criterio);
    }
     @Override
    public ArrayList<DTCliente> listarCliente()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPCliente().listarCliente();
    }
     @Override
    public ArrayList<DTCliente> listarcpropiedad()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPCliente().listarcpropiedad();
    }
       @Override
    public DTCliente buscarCliente(long cedula)
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPCliente().buscarCliente(cedula);
    }
    
}
