/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.excepciones.ExcepcionPersistencia;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import persistencia.Interfaces.IPPropiedad;

/**
 *
 * @author Waldemar
 */
public class PPropiedad implements IPPropiedad{
    
      private static PPropiedad instancia = null;
    
    
    public static PPropiedad getInstancia() {
        if (instancia == null) {
            instancia = new PPropiedad();
        }
        return instancia;
    }
     private PPropiedad() {
        
    }
       
      @Override
      public void agregarPropiedad(DTCliente cliente)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
          CallableStatement consulta = null;
         ArrayList<DTPropiedad> lista=cliente.getPropCliente();
        try {
           
            conexion = UtilidadesPersistencia.getConexion();
           consulta = conexion.prepareCall("{ CALL AgregarPropiedad(?, ?, ?) }");
            consulta.setString(1,lista.get(lista.size()-1).getTipoProp());
            consulta.setString(2,lista.get(lista.size()-1).getDirProp());
            consulta.setLong(3, cliente.getCedulaCliente());
            int filasAfectadas = consulta.executeUpdate();
            if (filasAfectadas < 1) {
                throw new Exception();
            }
        } catch (ExcepcionPersonalizada ex) {
         throw ex;
        } catch (Exception ex) {     
          } 
         finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
        @Override
       public DTPropiedad buscarPropiedad(int id)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Propiedad where Propiedad.IDPropiedad=?;");
            
            consulta.setLong(1, id);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTPropiedad propiedad = null;
     
              int idpropiedad;
            String tipo;
            String dirpropiedad;
            
            if (resultadoConsulta.next()) {
                idpropiedad = resultadoConsulta.getInt("IDPropiedad");
                tipo = resultadoConsulta.getString("Tipo");
                 dirpropiedad = resultadoConsulta.getString("DirPropiedad");
                 
                propiedad = new DTPropiedad(idpropiedad,dirpropiedad,tipo);
                
            
            }
            
            return propiedad;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar la Propiedad.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
     ArrayList<DTPropiedad> listarPropiedadCliente(long cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();          
            consulta = conexion.prepareStatement("Select * From Propiedad where CedCliente=?;");
            consulta.setLong(1,cedula);
            resultadoConsulta = consulta.executeQuery();
            
            ArrayList<DTPropiedad> propiedades = new ArrayList();
            DTPropiedad propiedad;
            
            int idpropiedad;
            String tipo;
            String dirpropiedad;
           
          
            
            while (resultadoConsulta.next()) {
                 idpropiedad = resultadoConsulta.getInt("IDPropiedad");
                tipo = resultadoConsulta.getString("Tipo");
                 dirpropiedad = resultadoConsulta.getString("DirPropiedad");
                propiedad = new DTPropiedad(idpropiedad,dirpropiedad,tipo);
                propiedades.add(propiedad);
            }
            
            return propiedades;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar las Propiedades.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
     @Override
    public void modificarPropiedad(DTCliente cliente,DTPropiedad prop)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement("update Propiedad set Tipo=?,DirPropiedad=? where CedCliente=? and IDPropiedad=?;");
             consulta.setString(1,prop.getTipoProp());
            consulta.setString(2,prop.getDirProp());
            consulta.setLong(3,cliente.getCedulaCliente());
             consulta.setInt(4,prop.getIdpropiedad());
            int filasAfectadas = consulta.executeUpdate();
            
            if (filasAfectadas < 1) {
                throw new Exception();
             
            }
             
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
        
            throw new ExcepcionPersistencia("No se pudo modificar la Propiedad.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
    
}
