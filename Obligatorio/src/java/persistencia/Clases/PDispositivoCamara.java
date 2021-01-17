/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;


import compartidos.beans.datatypes.DTDispositivoCamara;
import compartidos.beans.datatypes.DTServicio;

import compartidos.beans.datatypes.DTServicioCamara;
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

import persistencia.Interfaces.IPDispositivoCamara;

/**
 *
 * @author Waldemar
 */
public class PDispositivoCamara implements IPDispositivoCamara {
       private static PDispositivoCamara instancia = null;

    public static PDispositivoCamara getInstancia() {
        if (instancia == null) {
            instancia = new PDispositivoCamara();
        }
        return instancia;
    }
     private PDispositivoCamara() {
         
     }
    
     @Override
    public void agregarDCamara(DTDispositivoCamara dcamaras)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        try {
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("INSERT INTO Dispositivo VALUES (?,'Sin Servicio',null);");
            consulta2 = conexion.prepareStatement("INSERT INTO DispositivoCamara VALUES (?,'Sin Servicio',null);");
            consulta.setInt(1, dcamaras.getNinventario());
            consulta2.setInt(1,dcamaras.getNinventario());
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
                throw new ExcepcionPersistencia("El Dispositivo Camara ya existe.", ex);
            } else {
                
                throw new ExcepcionPersistencia("No se pudo agregar el Dispositivo Camara.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar el Dispositivo Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
    
      @Override
    public void eliminarDCamara(DTDispositivoCamara dcamaras)
            throws ExcepcionPersonalizada{
        Connection conexion = null;
       CallableStatement consulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();  
            consulta = conexion.prepareCall("{ CALL EliminarDCamara(?) }");
            consulta.setInt(1, dcamaras.getNinventario());
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
   public DTDispositivoCamara buscarDCamara(int numero)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Dispositivo s , DispositivoCamara a  where s.NInventario=a.NInventarioCamara and a.NInventarioCamara=?;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setInt(1, numero);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTDispositivoCamara dcamara = null;
            
            
    int numeroi;
    int idservicio;
    long cedt;
    String u;
    DTTecnico t;
    String ei;
    DTServicioCamara sc;
            if (resultadoConsulta.next()) {
                  numeroi = resultadoConsulta.getInt("NInventario");
               u= resultadoConsulta.getString("Ubicación");
                cedt = resultadoConsulta.getLong("CedTecnico");
                idservicio = resultadoConsulta.getInt("NServiceCamara");
                ei=resultadoConsulta.getString("ExterioroInterior");
         
               t=PTecnico.getInstancia().buscarTecnico(cedt);
              sc=PServicioCamara.getInstancia().buscarServicioCamara(idservicio);
                dcamara = new DTDispositivoCamara(ei,sc,numeroi,u,t);
            
            }
            
            return dcamara;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el Dispositivo Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    @Override
    public ArrayList<DTDispositivoCamara> listarDCamaras()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Dispositivo s,DispositivoCamara a where s.NInventario=a.NInventarioCamara and a.NServiceCamara is null;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.createStatement();         
            resultadoConsulta = consulta.executeQuery(sql);
            
            ArrayList<DTDispositivoCamara> dcamaras = new ArrayList();
            DTDispositivoCamara dcamara;
         int numeroi;
             int idservicio;
                long cedt;
             String u;
             DTTecnico t;
             String ei;
                DTServicioCamara sc;
            while (resultadoConsulta.next()) {
                numeroi = resultadoConsulta.getInt("NInventario");
               u= resultadoConsulta.getString("Ubicación");
                cedt = resultadoConsulta.getLong("CedTecnico");
                idservicio = resultadoConsulta.getInt("NServiceCamara");
                ei=resultadoConsulta.getString("ExterioroInterior");
         
               t=PTecnico.getInstancia().buscarTecnico(cedt);
              sc=PServicioCamara.getInstancia().buscarServicioCamara(idservicio);
                dcamara = new DTDispositivoCamara(ei,sc,numeroi,u,t);
                dcamaras.add(dcamara);
            }
            
            return dcamaras;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Dispositivo de Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    
       @Override
    public void registrarSDCamara(DTDispositivoCamara dcamaras)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        String sql="Update Dispositivo set Ubicación=?,CedTecnico=? where NInventario=?;";
        String sql2="update DispositivoCamara set ExterioroInterior=?,NServiceCamara=? where NInventarioCamara=?;";
        try {
           
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement(sql);
            consulta2 = conexion.prepareStatement(sql2);
           consulta.setString(1, dcamaras.getUbicacion());
            consulta.setLong(2,dcamaras.getTecnico().getCedulaEmp());
            consulta.setInt(3,dcamaras.getNinventario());
            
            consulta2.setString(1, dcamaras.getExteriorinterior());
            consulta2.setInt(2,dcamaras.getServicioCamara().getIdServicio());
           consulta2.setInt(3, dcamaras.getNinventario());
          
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
                throw new ExcepcionPersistencia("El Registro Dispositivo Camara ya existe.", ex);
            } else {
                
                throw new ExcepcionPersistencia("No se pudo registrar el Dispositivo Camara.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo registrar el Dispositivo Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
      @Override
    public void liberarSDCamara(DTDispositivoCamara dcamaras)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        String sql="Update Dispositivo set Ubicación='Camara Liberada de Servicio' where NInventario=?;";
        String sql2="update DispositivoCamara set ExterioroInterior='Camara Liberada de Servicio',NServiceCamara=null where NInventarioCamara=?;";
        try {
           
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement(sql);
            consulta2 = conexion.prepareStatement(sql2);
           consulta.setInt(1, dcamaras.getNinventario());
           consulta2.setInt(1, dcamaras.getNinventario());
          
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
            throw new ExcepcionPersistencia("No se pudo liberar el Dispositivo Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
    
     @Override
    public ArrayList<DTDispositivoCamara> listarSDCamaras()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Dispositivo s,DispositivoCamara a where s.NInventario=a.NInventarioCamara and a.NServiceCamara is not null;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.createStatement();         
            resultadoConsulta = consulta.executeQuery(sql);
            
            ArrayList<DTDispositivoCamara> dcamaras = new ArrayList();
            DTDispositivoCamara dcamara;
         int numeroi;
             int idservicio;
                long cedt;
             String u;
             DTTecnico t;
             String ei;
                DTServicioCamara sc;
            while (resultadoConsulta.next()) {
                numeroi = resultadoConsulta.getInt("NInventario");
               u= resultadoConsulta.getString("Ubicación");
                cedt = resultadoConsulta.getLong("CedTecnico");
                idservicio = resultadoConsulta.getInt("NServiceCamara");
                ei=resultadoConsulta.getString("ExterioroInterior");
         
               t=PTecnico.getInstancia().buscarTecnico2(cedt);
              sc=PServicioCamara.getInstancia().buscarServicioCamara2(idservicio);
                dcamara = new DTDispositivoCamara(ei,sc,numeroi,u,t);
                dcamaras.add(dcamara);
            }
            
            return dcamaras;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Dispositivo de Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
      @Override
    public ArrayList<DTDispositivoCamara> contarSDCamaras(DTServicio s)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select a.* from ServicioCamara s,DispositivoCamara a where s.NServicioCamara=a.NServiceCamara and a.NServiceCamara=?;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement(sql);
            consulta.setInt(1,s.getIdServicio());
            resultadoConsulta = consulta.executeQuery();
            
             
            ArrayList<DTDispositivoCamara> dcamaras = new ArrayList();
            DTDispositivoCamara dcamara;
         int numeroi;
             int idservicio;
                long cedt;
             String u;
             DTTecnico t;
             String ei;
                DTServicioCamara sc;
            while (resultadoConsulta.next()) {
                numeroi = resultadoConsulta.getInt("NInventario");
               u= resultadoConsulta.getString("Ubicación");
                cedt = resultadoConsulta.getLong("CedTecnico");
                idservicio = resultadoConsulta.getInt("NServiceCamara");
                ei=resultadoConsulta.getString("ExterioroInterior");
         
               t=PTecnico.getInstancia().buscarTecnico(cedt);
              sc=PServicioCamara.getInstancia().buscarServicioCamara(idservicio);
                dcamara = new DTDispositivoCamara(ei,sc,numeroi,u,t);
                dcamaras.add(dcamara);
            }
            
            return dcamaras;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Dispositivo de Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
