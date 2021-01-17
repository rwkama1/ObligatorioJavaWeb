/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;

import compartidos.beans.datatypes.DTDipositivoAlarma;
import compartidos.beans.datatypes.DTServicio;

import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.datatypes.DTTecnico;
import compartidos.beans.excepciones.ExcepcionPersistencia;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistencia.Interfaces.IPDispositivoAlarma;

/**
 *
 * @author Waldemar
 */
public class PDispositivoAlarma implements IPDispositivoAlarma {
      private static PDispositivoAlarma instancia = null;

    public static PDispositivoAlarma getInstancia() {
        if (instancia == null) {
            instancia = new PDispositivoAlarma();
        }
        return instancia;
    }
     private PDispositivoAlarma() {
         
     }
      
     @Override
    public void agregarDAlarma(DTDipositivoAlarma dalarma)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        try {
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("INSERT INTO Dispositivo VALUES (?,'Sin Servicio',null);");
            consulta2 = conexion.prepareStatement("INSERT INTO DispositivoAlarma VALUES (?,null);");
            consulta.setInt(1, dalarma.getNinventario());
            consulta2.setInt(1,dalarma.getNinventario());
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
                throw new ExcepcionPersistencia("El Dispositivo Alarma ya existe.", ex);
            } else {
                
                throw new ExcepcionPersistencia("No se pudo agregar el Dispositivo Alarma.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar el Dispositivo Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
    
      @Override
    public void eliminarDAlarma(DTDipositivoAlarma dalarma)
            throws ExcepcionPersonalizada{
        Connection conexion = null;
       CallableStatement consulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();  
            consulta = conexion.prepareCall("{ CALL EliminarDAlarma(?) }");
            consulta.setInt(1, dalarma.getNinventario());
          int filasAfectadas = consulta.executeUpdate();
            if (filasAfectadas < 1) {
                throw new Exception();
            }
       
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        }  catch (Exception ex) { 
           } 
        
         finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
       @Override
   public DTDipositivoAlarma buscarDAlarma(int numero)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Dispositivo s , DispositivoAlarma a  where s.NInventario=a.NInventarioAlarma and a.NInventarioAlarma=?;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setInt(1, numero);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTDipositivoAlarma dalarma = null;
            
            
    int numeroi;
    int idservicio;
    long cedt;
    String u;
    DTTecnico t;
   
    DTServicioAlarma sa;
            if (resultadoConsulta.next()) {
                  numeroi = resultadoConsulta.getInt("NInventario");
               u= resultadoConsulta.getString("Ubicación");
                cedt = resultadoConsulta.getLong("CedTecnico");
                idservicio = resultadoConsulta.getInt("NServiceAlarma");
               t=PTecnico.getInstancia().buscarTecnico(cedt);
              sa=PServicioAlarma.getInstancia().buscarServicioAlarma(idservicio);
                dalarma = new DTDipositivoAlarma(sa,numeroi,u,t);
            
            }
            
            return dalarma;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el Dispositivo Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    @Override
    public ArrayList<DTDipositivoAlarma> listarDAlarmas()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Dispositivo s,DispositivoAlarma a where s.NInventario=a.NInventarioAlarma and a.NServiceAlarma is null;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.createStatement();         
            resultadoConsulta = consulta.executeQuery(sql);
            ArrayList<DTDipositivoAlarma> dalarmas = new ArrayList();
            DTDipositivoAlarma dalarma;
         int numeroi;
             int idservicio;
                long cedt;
             String u;
             DTTecnico t;
              DTServicioAlarma sc;
            while (resultadoConsulta.next()) {
                numeroi = resultadoConsulta.getInt("NInventario");
               u= resultadoConsulta.getString("Ubicación");
                cedt = resultadoConsulta.getLong("CedTecnico");
                idservicio = resultadoConsulta.getInt("NServiceAlarma");
               t=PTecnico.getInstancia().buscarTecnico(cedt);
              sc=PServicioAlarma.getInstancia().buscarServicioAlarma(idservicio);
                dalarma = new DTDipositivoAlarma(sc,numeroi,u,t);
                dalarmas.add(dalarma);
            }
            
            return dalarmas;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Dispositivo de Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
       @Override
    public void registrarSDAlarma(DTDipositivoAlarma dalarmas)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        String sql="Update Dispositivo set Ubicación=?,CedTecnico=? where NInventario=?;";
        String sql2="update DispositivoAlarma set NServiceAlarma=? where NInventarioAlarma=?;";
        try {
           
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement(sql);
            consulta2 = conexion.prepareStatement(sql2);
           consulta.setString(1, dalarmas.getUbicacion());
            consulta.setLong(2,dalarmas.getTecnico().getCedulaEmp());
            consulta.setInt(3,dalarmas.getNinventario());
            
            consulta2.setInt(1,dalarmas.getServicioAlarma().getIdServicio());
           consulta2.setInt(2, dalarmas.getNinventario());
          
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
                throw new ExcepcionPersistencia("El Registro Dispositivo Alarma ya existe.", ex);
            } else {
                
                throw new ExcepcionPersistencia("No se pudo registrar el Dispositivo Alarma.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo registrar el Dispositivo Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
      @Override
    public void liberarSDAlarma(DTDipositivoAlarma dalarmas)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        String sql="Update Dispositivo set Ubicación='Alarma Liberada de Servicio' where NInventario=?;";
        String sql2="update DispositivoAlarma set NServiceAlarma=null where NInventarioAlarma=?;";
        try {
           
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement(sql);
            consulta2 = conexion.prepareStatement(sql2);
           consulta.setInt(1, dalarmas.getNinventario());
           consulta2.setInt(1, dalarmas.getNinventario());
          
            int filasAfectadas = consulta.executeUpdate();
            int filasAfectadas2 = consulta2.executeUpdate();
          
            
            if (filasAfectadas < 1||filasAfectadas2 <1) {
                throw new Exception();
            }
              conexion.commit();
            
        } catch (ExcepcionPersonalizada ex) {
         
            throw ex;
            
        } 
        catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo liberar el Dispositivo Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
    
     @Override
    public ArrayList<DTDipositivoAlarma> listarSDAlarmas()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Dispositivo s,DispositivoAlarma a where s.NInventario=a.NInventarioAlarma and a.NServiceAlarma is not null;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.createStatement();         
            resultadoConsulta = consulta.executeQuery(sql);
            
            ArrayList<DTDipositivoAlarma> dalarmas = new ArrayList();
            DTDipositivoAlarma dalarma;
         int numeroi;
             int idservicio;
                long cedt;
             String u;
             DTTecnico t;
                DTServicioAlarma sc;
            while (resultadoConsulta.next()) {
                numeroi = resultadoConsulta.getInt("NInventario");
               u= resultadoConsulta.getString("Ubicación");
                cedt = resultadoConsulta.getLong("CedTecnico");
                idservicio = resultadoConsulta.getInt("NServiceAlarma");
               t=PTecnico.getInstancia().buscarTecnico2(cedt);
              sc=PServicioAlarma.getInstancia().buscarServicioAlarma2(idservicio);
                dalarma = new DTDipositivoAlarma(sc,numeroi,u,t);
                dalarmas.add(dalarma);
            }
            
            return dalarmas;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Dispositivo de Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
      @Override
    public ArrayList<DTDipositivoAlarma> contarSDAlarmas(DTServicio s)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select a.* from ServicioAlarma s,DispositivoAlarma a where s.NServicioAlarma=a.NServiceAlarma and a.NServiceAlarma=?;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement(sql);
            consulta.setInt(1,s.getIdServicio());
            resultadoConsulta = consulta.executeQuery();
            
            ArrayList<DTDipositivoAlarma> dalarmas = new ArrayList();
            DTDipositivoAlarma dalarma;
         int numeroi;
             int idservicio;
                long cedt;
             String u;
             DTTecnico t;
                DTServicioAlarma sc;
            while (resultadoConsulta.next()) {
                numeroi = resultadoConsulta.getInt("NInventario");
               u= resultadoConsulta.getString("Ubicación");
                cedt = resultadoConsulta.getLong("CedTecnico");
                idservicio = resultadoConsulta.getInt("NServiceAlarma");
               t=PTecnico.getInstancia().buscarTecnico(cedt);
              sc=PServicioAlarma.getInstancia().buscarServicioAlarma(idservicio);
                dalarma = new DTDipositivoAlarma(sc,numeroi,u,t);
                dalarmas.add(dalarma);
            }
            
            return dalarmas;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Dispositivo de Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
