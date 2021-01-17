/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;



import compartidos.beans.datatypes.DTCobrador;
import compartidos.beans.excepciones.ExcepcionPersistencia;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import persistencia.Interfaces.IPCobrador;

/**
 *
 * @author Waldemar
 */
public class PCobrador implements IPCobrador {
       private static PCobrador instancia = null;
    
    
    public static PCobrador getInstancia() {
        if (instancia == null) {
            instancia = new PCobrador();
        }
        return instancia;
    }
     private PCobrador() {
        
    }
        @Override
    public ArrayList<DTCobrador> buscoCobradores(String criterio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Empleado U Inner Join Cobrador a on U.CedulaEmpleado = a.CedulaCobrador Where a.CedulaCobrador=? or u.NomEmpleado LIKE ? and BajaEmpleado=false;");
            
            consulta.setString(1, criterio);
            consulta.setString(2, "%" + criterio + "%");
            
            resultadoConsulta = consulta.executeQuery();
            
            ArrayList<DTCobrador> cobradores = new ArrayList();
            DTCobrador cobrador;
            
            long cedula;
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;
            String transporte;
      
            while (resultadoConsulta.next()) {
                cedula = resultadoConsulta.getLong("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                 clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                transporte=resultadoConsulta.getString("Transporte");
                cobrador = new DTCobrador(transporte,cedula, nombre,clavesql,sueldo,fecha);
                cobradores.add(cobrador);
            }
            
            return cobradores;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar los cobradores.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    @Override
    public void agregarCobrador(DTCobrador cobrador)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            java.sql.Date fechaSql=new java.sql.Date(cobrador.getFechaEmp().getTime());
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("INSERT INTO Empleado VALUES (?, ?, ?, ?, ?,false);");
            consulta2 = conexion.prepareStatement("INSERT INTO Cobrador VALUES (?,?);");
            consulta.setLong(1, cobrador.getCedulaEmp());
            consulta.setString(2, cobrador.getNomEmp());
            consulta.setString(3, cobrador.getClaveEmp());
            consulta.setDouble(4, cobrador.getSueldoEmp());
            consulta.setDate(5, fechaSql);
            consulta2.setLong(1, cobrador.getCedulaEmp());
            consulta2.setString(2, cobrador.getTranporteCobrador());
            int filasAfectadas = consulta.executeUpdate();
            int filasAfectadas2 = consulta2.executeUpdate();
          
            
            if (filasAfectadas < 1||filasAfectadas2 <1) {
                throw new Exception();
            }
              conexion.commit();
            
        } catch (ExcepcionPersonalizada ex) {
         
            throw ex;
            
        } catch (SQLException ex) {
             
            if (ex.getErrorCode() == 1062) {
                throw new ExcepcionPersistencia("El empleado ya existe.", ex);
            } else {
                
                throw new ExcepcionPersistencia("No se pudo agregar el cobrador.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar el cobrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
     @Override
   public DTCobrador buscarCobrador(long cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="Select * From Empleado U Inner Join Cobrador a on U.CedulaEmpleado = a.CedulaCobrador Where a.CedulaCobrador=? and BajaEmpleado=false;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setLong(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTCobrador cobrador = null;
            
            
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;;
            String tranporte;
            
            if (resultadoConsulta.next()) {
                  cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                tranporte=resultadoConsulta.getString("Transporte");
                
                cobrador = new DTCobrador(tranporte,cedula, nombre,clavesql,sueldo,fecha);
            
            }
            
            return cobrador;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el cobrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
   @Override
    public void modificarCobrador(DTCobrador cobrador)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
              conexion.setAutoCommit(false);
              java.sql.Date fechaSql=new java.sql.Date(cobrador.getFechaEmp().getTime());
            consulta = conexion.prepareStatement("UPDATE Empleado SET NomEmpleado= ? ,Clave = ? ,Sueldo= ? ,FechaIngreso= ? WHERE CedulaEmpleado = ?;");
            consulta2=conexion.prepareStatement("UPDATE Cobrador SET Transporte=? where CedulaCobrador=?");
             consulta.setString(1, cobrador.getNomEmp());
            consulta.setString(2, cobrador.getClaveEmp());
            consulta.setDouble(3, cobrador.getSueldoEmp());
            consulta.setDate(4, fechaSql);
            consulta.setLong(5, cobrador.getCedulaEmp());
            consulta2.setString(1, cobrador.getTranporteCobrador());
            consulta2.setLong(2,cobrador.getCedulaEmp());
            int filasAfectadas = consulta.executeUpdate();
              int filasAfectadas2 = consulta2.executeUpdate();
            if (filasAfectadas < 1||filasAfectadas2<1) {
                throw new Exception();
             
            }
               conexion.commit();
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            if(conexion!=null)
            {
                try {
                    conexion.rollback();
                } catch (SQLException ex1) {
                   throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                }
            }
            
            throw new ExcepcionPersistencia("No se pudo modificar el cobrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
    @Override
    public void eliminarCobrador(DTCobrador cobrador)
            throws ExcepcionPersonalizada{
        Connection conexion = null;
        PreparedStatement consulta = null;
        
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
        
            consulta = conexion.prepareStatement("UPDATE Empleado SET BajaEmpleado=true WHERE CedulaEmpleado = ?;");
            
            consulta.setLong(1, cobrador.getCedulaEmp());
       
            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas < 1) {
                throw new Exception();
            }
           
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo eliminar el cobrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
     @Override
    public ArrayList<DTCobrador> listarCobrador()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery("Select * From Empleado U Inner Join Cobrador a on U.CedulaEmpleado = a.CedulaCobrador where BajaEmpleado=false;");
            
            ArrayList<DTCobrador> cobradores = new ArrayList();
            DTCobrador cobrador;
            
            long cedula;
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;
            String transporte;
            while (resultadoConsulta.next()) {
                cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                transporte=resultadoConsulta.getString("Transporte");
                cobrador = new DTCobrador(transporte,cedula, nombre,clavesql,sueldo,fecha);
                cobradores.add(cobrador);
            }
            
            return cobradores;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Cobradores.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
     @Override
     public DTCobrador logueocobrador(long cedula,String clave)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="SELECT * FROM Empleado us inner join Cobrador c ON us.CedulaEmpleado = c.CedulaCobrador WHERE us.CedulaEmpleado =? AND us.Clave =? and BajaEmpleado=false;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setLong(1, cedula);
             consulta.setString(2,clave);
            resultadoConsulta = consulta.executeQuery();
            
            DTCobrador cobrador = null;
             String nombre;
            if (resultadoConsulta.next()) {
                  cedula = resultadoConsulta.getInt("CedulaEmpleado");
                clave = resultadoConsulta.getString("Clave");
                nombre = resultadoConsulta.getString("NomEmpleado");
                cobrador = new DTCobrador("",cedula, nombre,clave,0,null);
            
            }           
            return cobrador;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el Cobrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
      public DTCobrador buscarCobrador2(long cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="Select * From Empleado U Inner Join Cobrador a on U.CedulaEmpleado = a.CedulaCobrador Where a.CedulaCobrador=?;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setLong(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTCobrador cobrador = null;
            
            
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;;
            String tranporte;
            
            if (resultadoConsulta.next()) {
                  cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                tranporte=resultadoConsulta.getString("Transporte");
                
                cobrador = new DTCobrador(tranporte,cedula, nombre,clavesql,sueldo,fecha);
            
            }
            
            return cobrador;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el cobrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
