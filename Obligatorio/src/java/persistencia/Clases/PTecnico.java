/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;



import compartidos.beans.datatypes.DTTecnico;
import compartidos.beans.excepciones.ExcepcionPersistencia;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistencia.Interfaces.IPTecnico;

/**
 *
 * @author Waldemar
 */
public class PTecnico implements IPTecnico {
         private static PTecnico instancia = null;
    
    
    public static PTecnico getInstancia() {
        if (instancia == null) {
            instancia = new PTecnico();
        }
        return instancia;
    }
     private PTecnico() {
        
    }
     @Override
    public ArrayList<DTTecnico> buscoTecnicos(String criterio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Empleado U Inner Join Tecnico a on U.CedulaEmpleado = a.CedulaTecnico Where a.CedulaTecnico=? or u.NomEmpleado LIKE ? and BajaEmpleado=false;");
            
            consulta.setString(1, criterio);
            consulta.setString(2, "%" + criterio + "%");
            
            resultadoConsulta = consulta.executeQuery();
            
            ArrayList<DTTecnico> tecnicos = new ArrayList();
            DTTecnico tecnico;
            
            long cedula;
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;
          boolean espalarmas;
          boolean espcamaras;
      
            while (resultadoConsulta.next()) {
                cedula = resultadoConsulta.getLong("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                 clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                espcamaras=resultadoConsulta.getBoolean("EspCamaras");
                 espalarmas=resultadoConsulta.getBoolean("EspAlarmas");
                tecnico = new DTTecnico(espcamaras,espalarmas,cedula, nombre,clavesql,sueldo,fecha);
                tecnicos.add(tecnico);
            }
            
            return tecnicos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar los Tecnicos.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    @Override
    public void agregarTecnico(DTTecnico tecnico)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            java.sql.Date fechaSql=new java.sql.Date(tecnico.getFechaEmp().getTime());
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("INSERT INTO Empleado VALUES (?, ?, ?, ?, ?,false);");
            consulta2 = conexion.prepareStatement("INSERT INTO Tecnico VALUES (?,?,?);");
            consulta.setLong(1, tecnico.getCedulaEmp());
            consulta.setString(2, tecnico.getNomEmp());
            consulta.setString(3, tecnico.getClaveEmp());
            consulta.setDouble(4, tecnico.getSueldoEmp());
            consulta.setDate(5, fechaSql);
            consulta2.setLong(1, tecnico.getCedulaEmp());
            consulta2.setBoolean(2, tecnico.getEspCamaras());
            consulta2.setBoolean(3,tecnico.getEspAlarmas());
         
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
                
                throw new ExcepcionPersistencia("No se pudo agregar el empleado.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar el tecnico.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
     @Override
   public DTTecnico buscarTecnico(long cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Empleado U Inner Join Tecnico a on U.CedulaEmpleado = a.CedulaTecnico Where a.CedulaTecnico=? and BajaEmpleado=false;");
            
            consulta.setLong(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTTecnico tecnico = null;
            
            
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;;
           boolean espalarmas;
          boolean espcamaras;
            
            if (resultadoConsulta.next()) {
                  cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                espcamaras=resultadoConsulta.getBoolean("EspCamaras");
                 espalarmas=resultadoConsulta.getBoolean("EspAlarmas");
                tecnico = new DTTecnico(espcamaras,espalarmas,cedula, nombre,clavesql,sueldo,fecha);
              
            
            }
            
            return tecnico;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el tecnico.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
   @Override
    public void modificarTecnico(DTTecnico tecnico)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
              conexion.setAutoCommit(false);
              java.sql.Date fechaSql=new java.sql.Date(tecnico.getFechaEmp().getTime());
            consulta = conexion.prepareStatement("UPDATE Empleado SET NomEmpleado= ? ,Clave = ? ,Sueldo= ? ,FechaIngreso= ? WHERE CedulaEmpleado = ?;");
            consulta2=conexion.prepareStatement("update Tecnico set EspCamaras=?,EspAlarmas=? where CedulaTecnico = ?;");
             consulta.setString(1, tecnico.getNomEmp());
            consulta.setString(2, tecnico.getClaveEmp());
            consulta.setDouble(3, tecnico.getSueldoEmp());
            consulta.setDate(4, fechaSql);
            consulta.setLong(5, tecnico.getCedulaEmp());
            consulta2.setBoolean(1, tecnico.getEspCamaras());
            consulta2.setBoolean(2,tecnico.getEspAlarmas());
            consulta2.setLong(3,tecnico.getCedulaEmp());
            int filasAfectadas = consulta.executeUpdate();
             int filasAfectadas2 = consulta2.executeUpdate();
            if (filasAfectadas < 1||filasAfectadas2 <1) {
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
            
            throw new ExcepcionPersistencia("No se pudo modificar el tecnico.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
    @Override
    public void eliminarTecnico(DTTecnico tecnico)
            throws ExcepcionPersonalizada{
        Connection conexion = null;
        PreparedStatement consulta = null;
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement("UPDATE Empleado SET BajaEmpleado=true WHERE CedulaEmpleado = ?;");
            consulta.setLong(1, tecnico.getCedulaEmp());
           
            int filasAfectadas = consulta.executeUpdate();
          
         
            if (filasAfectadas < 1) {
                throw new Exception();
            }
            
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
         
            throw new ExcepcionPersistencia("No se pudo eliminar el Tecnico.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
     @Override
    public ArrayList<DTTecnico> listarTecnico()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery("Select * From Empleado U Inner Join Tecnico a on U.CedulaEmpleado = a.CedulaTecnico and BajaEmpleado=false;");
            
            ArrayList<DTTecnico> tecnicos = new ArrayList();
            DTTecnico tecnico;
            
            long cedula;
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;
           boolean espalarmas;
          boolean espcamaras;
            while (resultadoConsulta.next()) {
                 cedula = resultadoConsulta.getLong("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                 clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                espcamaras=resultadoConsulta.getBoolean("EspCamaras");
                 espalarmas=resultadoConsulta.getBoolean("EspAlarmas");
                tecnico = new DTTecnico(espcamaras,espalarmas,cedula, nombre,clavesql,sueldo,fecha);
                tecnicos.add(tecnico);
            }
            
            return tecnicos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Tecnicos.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
       @Override
     public DTTecnico logueotecnico(long cedula,String clave)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="SELECT * FROM Empleado us inner join Tecnico c ON us.CedulaEmpleado = c.CedulaTecnico WHERE us.CedulaEmpleado =? AND us.Clave =? and BajaEmpleado=false;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setLong(1, cedula);
             consulta.setString(2,clave);
            resultadoConsulta = consulta.executeQuery();
            
            DTTecnico tecnico = null;
            String nombre;
            if (resultadoConsulta.next()) {
                  cedula = resultadoConsulta.getInt("CedulaEmpleado");
                clave = resultadoConsulta.getString("Clave");
               nombre = resultadoConsulta.getString("NomEmpleado");
                tecnico = new DTTecnico(false,false,cedula,nombre,clave,0,null);
            
            }
            
            return tecnico;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el Tecnico.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
        @Override
       public ArrayList<DTTecnico> tecnicosEC()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select distinct t.* from Tecnico t inner join Empleado e on t.CedulaTecnico=e.CedulaEmpleado where t.EspCamaras=true and e.BajaEmpleado=false;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery(sql);
            
            ArrayList<DTTecnico> tecnicos = new ArrayList();
            DTTecnico tecnico;
            
            long cedula;
          
            while (resultadoConsulta.next()) {
                 cedula = resultadoConsulta.getLong("CedulaTecnico");
                tecnico = new DTTecnico(false,false,cedula, "","",0,null);
                tecnicos.add(tecnico);
            }
            
            return tecnicos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Tecnicos.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
          @Override
     public ArrayList<DTTecnico> tecnicosEA()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select distinct t.* from Tecnico t inner join Empleado e on t.CedulaTecnico=e.CedulaEmpleado where t.EspAlarmas=true and e.BajaEmpleado=false;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery(sql);
            
            ArrayList<DTTecnico> tecnicos = new ArrayList();
            DTTecnico tecnico;
            
           long cedula;
          
            while (resultadoConsulta.next()) {
                 cedula = resultadoConsulta.getLong("CedulaTecnico");
                tecnico = new DTTecnico(false,false,cedula, "","",0,null);
                tecnicos.add(tecnico);
            }
            
            return tecnicos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Tecnicos.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
      public DTTecnico buscarTecnico2(long cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("Select * From Empleado U Inner Join Tecnico a on U.CedulaEmpleado = a.CedulaTecnico Where a.CedulaTecnico=?;");
            
            consulta.setLong(1, cedula);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTTecnico tecnico = null;
            
            
            String nombre;
            String clavesql;
            double sueldo;
            Date fecha;;
           boolean espalarmas;
          boolean espcamaras;
            
            if (resultadoConsulta.next()) {
                  cedula = resultadoConsulta.getInt("CedulaEmpleado");
                nombre = resultadoConsulta.getString("NomEmpleado");
                clavesql = resultadoConsulta.getString("Clave");
                sueldo = resultadoConsulta.getDouble("Sueldo");
                fecha=resultadoConsulta.getDate("FechaIngreso");
                espcamaras=resultadoConsulta.getBoolean("EspCamaras");
                 espalarmas=resultadoConsulta.getBoolean("EspAlarmas");
                tecnico = new DTTecnico(espcamaras,espalarmas,cedula, nombre,clavesql,sueldo,fecha);
              
            
            }
            
            return tecnico;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el tecnico.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
