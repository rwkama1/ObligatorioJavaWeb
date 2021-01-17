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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistencia.Interfaces.IPCliente;

/**
 *
 * @author Waldemar
 */
public class PCliente implements IPCliente{
      private static PCliente instancia = null;
    
    
    public static PCliente getInstancia() {
        if (instancia == null) {
            instancia = new PCliente();
        }
        return instancia;
    }
     private PCliente() {
        
    }
        @Override
    public ArrayList<DTCliente> buscoClientes(String criterio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Cliente where Cliente.CedulaCliente=? or Cliente.NomCliente LIKE ?;");
            
            consulta.setString(1, criterio);
            consulta.setString(2, "%" + criterio + "%");
            
            resultadoConsulta = consulta.executeQuery();
            
            ArrayList<DTCliente> clientes = new ArrayList();
            DTCliente cliente;
            
            long cedula;
            String nombre;
            String dircobro;
            String zonabarrio;
            String telefono;
      
            while (resultadoConsulta.next()) {
                cedula = resultadoConsulta.getLong("CedulaCliente");
                nombre = resultadoConsulta.getString("NomCliente");
                 dircobro = resultadoConsulta.getString("DirCobro");
                zonabarrio = resultadoConsulta.getString("ZonaBarrio");
                telefono=resultadoConsulta.getString("Telefono");
                
                cliente = new DTCliente(cedula, nombre,dircobro,zonabarrio,telefono,PPropiedad.getInstancia().listarPropiedadCliente(cedula));
                clientes.add(cliente);
            }
            
            return clientes;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar los Clientes.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    @Override
      public void agregarCliente(DTCliente cliente)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
    
        
        try {
           
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement("INSERT INTO Cliente VALUES (?, ?, ?, ?, ?);");
            consulta.setLong(1, cliente.getCedulaCliente());
            consulta.setString(2, cliente.getNomCliente());
            consulta.setString(3, cliente.getDirCliente());
            consulta.setString(4, cliente.getZonabarrioCliente());
            consulta.setString(5, cliente.getTelCliente());
        
            int filasAfectadas = consulta.executeUpdate();
           
          
            
            if (filasAfectadas < 1) {
                throw new Exception();
            }
            
            
        } catch (ExcepcionPersonalizada ex) {
         
            throw ex;
            
        } catch (SQLException ex) {
             
            if (ex.getErrorCode() == 1062) {
                throw new ExcepcionPersistencia("El Cliente ya existe.", ex);
            } else {
                
                throw new ExcepcionPersistencia("No se pudo agregar el Cliente.", ex);
            }
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo agregar el Cliente.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
       @Override
       public DTCliente buscarCliente(long cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Cliente where Cliente.CedulaCliente=?;");
            
            consulta.setLong(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTCliente cliente = null;
     
           
            String nombre;
            String dircobro;
            String zonabarrio;
            String telefono;
            
            if (resultadoConsulta.next()) {
            cedula = resultadoConsulta.getLong("CedulaCliente");
                nombre = resultadoConsulta.getString("NomCliente");
                 dircobro = resultadoConsulta.getString("DirCobro");
                zonabarrio = resultadoConsulta.getString("ZonaBarrio");
                telefono=resultadoConsulta.getString("Telefono");
                
             cliente = new DTCliente(cedula, nombre,dircobro,zonabarrio,telefono,PPropiedad.getInstancia().listarPropiedadCliente(cedula));
            
            }
            
            return cliente;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el Cliente.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
         @Override
    public ArrayList<DTCliente> listarCliente()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery("Select * From Cliente;");
            
            ArrayList<DTCliente> clientes = new ArrayList();
            DTCliente cliente;
            
            long cedula;
            String nombre;
            String dircobro;
            String zonabarrio;
            String telefono;
            
            while (resultadoConsulta.next()) {
                 cedula = resultadoConsulta.getLong("CedulaCliente");
                nombre = resultadoConsulta.getString("NomCliente");
                 dircobro = resultadoConsulta.getString("DirCobro");
                zonabarrio = resultadoConsulta.getString("ZonaBarrio");
                telefono=resultadoConsulta.getString("Telefono");
                
                cliente = new DTCliente(cedula, nombre,dircobro,zonabarrio,telefono,PPropiedad.getInstancia().listarPropiedadCliente(cedula));
                clientes.add(cliente);
            }
            
            return clientes;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Cliente.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
     @Override
    public void modificarCliente(DTCliente cliente)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement("update Cliente set NomCliente=?,DirCobro=?,ZonaBarrio=?,Telefono=? where CedulaCliente=?;");
             consulta.setString(1, cliente.getNomCliente());
            consulta.setString(2, cliente.getDirCliente());
            consulta.setString(3, cliente.getZonabarrioCliente());
            consulta.setString(4,cliente.getTelCliente());
             consulta.setLong(5, cliente.getCedulaCliente());
            
            int filasAfectadas = consulta.executeUpdate();
            
            if (filasAfectadas < 1) {
                throw new Exception();
            }
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo modificar el Cliente.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
        @Override
    public ArrayList<DTCliente> listarcpropiedad()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="SELECT distinct c.*\n" +
        "FROM Cliente c inner join Propiedad p\n" +
         "on c.CedulaCliente=p.CedCliente \n" +
         "LEFT JOIN ReciboCobro r ON r.ClienteR = c.CedulaCliente\n" +
          "where r.ClienteR IS NULL;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery(sql);
            
            ArrayList<DTCliente> clientes = new ArrayList();
            DTCliente cliente;
            
            long cedula;
            String nombre;
            String dircobro;
            String zonabarrio;
            String telefono;
            
            while (resultadoConsulta.next()) {
                 cedula = resultadoConsulta.getLong("CedulaCliente");
                nombre = resultadoConsulta.getString("NomCliente");
                 dircobro = resultadoConsulta.getString("DirCobro");
                zonabarrio = resultadoConsulta.getString("ZonaBarrio");
                telefono=resultadoConsulta.getString("Telefono");
                
                cliente = new DTCliente(cedula, nombre,dircobro,zonabarrio,telefono,PPropiedad.getInstancia().listarPropiedadCliente(cedula));
                clientes.add(cliente);
            }
            
            return clientes;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Cliente.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
       @Override
    public void agregarCP(DTCliente c,DTPropiedad p)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
       
          PreparedStatement consulta = null;   
          String sql="INSERT INTO Cliente VALUES (?,?,?,?,?);";
          String sql2="insert into Propiedad values (1,?,?,?);";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement(sql);
          consulta.setLong(1, c.getCedulaCliente());
          consulta.setString(2, c.getNomCliente());
          consulta.setString(3, c.getDirCliente());
          consulta.setString(4, c.getZonabarrioCliente());
          consulta.setString(5, c.getTelCliente());
            consulta.executeUpdate();
            consulta = conexion.prepareStatement(sql2); 
           consulta.setString(1,p.getTipoProp());
           consulta.setString(2,p.getDirProp());
           consulta.setLong(3, c.getCedulaCliente());
            consulta.executeUpdate();
        
           conexion.commit();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new ExcepcionPersistencia("El Cliente ya existe.", ex);
            } else  {
                
                throw new ExcepcionPersistencia("Error", ex);
            }
        }
        catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar la Transaccion.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
}
