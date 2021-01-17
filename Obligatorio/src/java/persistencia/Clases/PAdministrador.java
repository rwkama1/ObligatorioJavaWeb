/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;

import compartidos.beans.datatypes.DTAdministrador;

import compartidos.beans.excepciones.ExcepcionPersistencia;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistencia.Interfaces.IPAdministrador;


/**
 *
 * @author Waldemar
 */
public class PAdministrador implements IPAdministrador {
       private static PAdministrador instancia = null;
    
    
    public static PAdministrador getInstancia() {
        if (instancia == null) {
            instancia = new PAdministrador();
        }
        return instancia;
    }
     private PAdministrador() {
        
    }
      @Override
    public ArrayList<DTAdministrador> buscoAdmins(String criterio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Empleado U Inner Join Administrador a on U.CedulaEmpleado = a.CedulaAdmin Where a.CedulaAdmin=? or u.NomEmpleado LIKE ?;");
            
            consulta.setString(1, criterio);
            consulta.setString(2, "%" + criterio + "%");
            
            resultadoConsulta = consulta.executeQuery();
            
            ArrayList<DTAdministrador> admines = new ArrayList();
            DTAdministrador admin;
            
            long cedula;
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;
      
            while (resultadoConsulta.next()) {
                cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                 clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                
                admin = new DTAdministrador(cedula, nombre,clavesql,sueldo,fecha);
                admines.add(admin);
            }
            
            return admines;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar los empleados.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    @Override
    public void agregarAdministrador(DTAdministrador admin)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            java.sql.Date fechaSql=new java.sql.Date(admin.getFechaEmp().getTime());
            conexion = UtilidadesPersistencia.getConexion();
             conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("INSERT INTO Empleado VALUES (?, ?, ?, ?, ?,false);");
            consulta2 = conexion.prepareStatement("INSERT INTO Administrador VALUES (?);");
            consulta.setLong(1, admin.getCedulaEmp());
            consulta.setString(2, admin.getNomEmp());
            consulta.setString(3, admin.getClaveEmp());
            consulta.setDouble(4, admin.getSueldoEmp());
            consulta.setDate(5, fechaSql);
            consulta2.setLong(1, admin.getCedulaEmp());
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
                
                throw new ExcepcionPersistencia("No se pudo agregar el administrador.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar el administrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
     @Override
   public DTAdministrador buscarAdmin(long cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Empleado U Inner Join Administrador a on U.CedulaEmpleado = a.CedulaAdmin Where a.CedulaAdmin=?;");
            
            consulta.setLong(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTAdministrador admin = null;
            
            
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;;
            
            if (resultadoConsulta.next()) {
                  cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                
                admin = new DTAdministrador(cedula, nombre,clavesql,sueldo,fecha);
            
            }
            
            return admin;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el administrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
   @Override
    public void modificarAdmin(DTAdministrador admin)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
              java.sql.Date fechaSql=new java.sql.Date(admin.getFechaEmp().getTime());
            consulta = conexion.prepareStatement("UPDATE Empleado SET NomEmpleado= ? ,Clave = ? ,Sueldo= ? ,FechaIngreso= ? WHERE CedulaEmpleado = ?;");
             consulta.setString(1, admin.getNomEmp());
            consulta.setString(2, admin.getClaveEmp());
            consulta.setDouble(3, admin.getSueldoEmp());
            consulta.setDate(4, fechaSql);
             consulta.setLong(5, admin.getCedulaEmp());
            
            int filasAfectadas = consulta.executeUpdate();
            
            if (filasAfectadas < 1) {
                throw new Exception();
            }
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo modificar el administrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
    @Override
    public void eliminarAdmin(DTAdministrador admin)
            throws ExcepcionPersonalizada{
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
              conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("delete from Administrador where CedulaAdmin = ?;");
            consulta2 = conexion.prepareStatement("delete from Empleado where CedulaEmpleado = ?;");
            
            consulta.setLong(1, admin.getCedulaEmp());
            consulta2.setLong(1, admin.getCedulaEmp());
            int filasAfectadas = consulta.executeUpdate();
            int filasAfectadas2 = consulta2.executeUpdate();
         
            if (filasAfectadas < 1||filasAfectadas2<1) {
                throw new Exception();
            }
             conexion.commit();
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
          if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo eliminar el Administrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
     @Override
    public ArrayList<DTAdministrador> listarAdministrador()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery("Select * From Empleado U Inner Join Administrador a on U.CedulaEmpleado = a.CedulaAdmin;");
            
            ArrayList<DTAdministrador> admines = new ArrayList();
            DTAdministrador admin;
            
            long cedula;
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;
            
            while (resultadoConsulta.next()) {
                cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                
                admin = new DTAdministrador(cedula, nombre,clavesql,sueldo,fecha);
                admines.add(admin);
            }
            
            return admines;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Administradores.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
     @Override
     public DTAdministrador logueoadmin(long cedula,String clave)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="SELECT * FROM Empleado us inner join Administrador c ON us.CedulaEmpleado = c.CedulaAdmin WHERE us.CedulaEmpleado =? AND us.Clave =?;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setLong(1, cedula);
             consulta.setString(2,clave);
            resultadoConsulta = consulta.executeQuery();
            
            DTAdministrador admin = null;
            
            
            String nombre;
            double sueldo;
            Date fecha;
            
            if (resultadoConsulta.next()) {
                  cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                clave = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                
                admin = new DTAdministrador(cedula, nombre,clave,sueldo,fecha);
            
            }
            
            return admin;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el administrador.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
