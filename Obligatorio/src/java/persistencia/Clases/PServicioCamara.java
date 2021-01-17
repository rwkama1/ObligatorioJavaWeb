/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.datatypes.DTServicioCamara;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import compartidos.beans.excepciones.ExcepcionPersistencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistencia.Interfaces.IPServicioCamara;

/**
 *
 * @author Waldemar
 */
public class PServicioCamara implements IPServicioCamara {
       private static PServicioCamara instancia = null;

    public static PServicioCamara getInstancia() {
        if (instancia == null) {
            instancia = new PServicioCamara();
        }
        return instancia;
    }
     private PServicioCamara() {
         
     }
         @Override
    public void agregarCSC(DTServicioCamara sc,DTCliente c)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
          ArrayList<DTPropiedad> lista=c.getPropCliente();
         java.sql.Date fechaSql=new java.sql.Date(sc.getFechaServicio().getTime());
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareCall("{ CALL AgregarCSC(?,?,?,?,?,?,?,?,?,?) }");
          consulta.setLong(1, c.getCedulaCliente());
          consulta.setString(2, c.getNomCliente());
          consulta.setString(3, c.getDirCliente());
          consulta.setString(4, c.getZonabarrioCliente());
          consulta.setString(5, c.getTelCliente());
  
           consulta.setString(6,lista.get(lista.size()-1).getTipoProp());
           consulta.setString(7,lista.get(lista.size()-1).getDirProp());
           
          consulta.setDate(8, fechaSql);
          consulta.setBoolean(9,sc.isMoniteroServicio());
          consulta.setBoolean(10, sc.isTerminalgrabacion());
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
                
                throw new ExcepcionPersistencia("No se pudo agregar la Transaccion.", ex);
            }
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo agregar la Transaccion.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
    @Override
    public void agregarServicioCamara(DTServicioCamara scamaras)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            java.sql.Date fechaSql=new java.sql.Date(scamaras.getFechaServicio().getTime());
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("INSERT INTO Servicio VALUES (null,?,?,?,?,false);");
            consulta2 = conexion.prepareStatement("INSERT INTO ServicioCamara VALUES (LAST_INSERT_ID(),?);");
         
            consulta.setDate(1, fechaSql);
            consulta.setBoolean(2, scamaras.isMoniteroServicio());
            consulta.setInt(3,scamaras.getPropServicio().getIdpropiedad());   
            consulta.setLong(4,scamaras.getClienteServicio().getCedulaCliente());         
            consulta2.setBoolean(1, scamaras.isTerminalgrabacion());
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
                throw new ExcepcionPersistencia("El ServicioCamara ya existe.", ex);
            } else {
                
                throw new ExcepcionPersistencia("No se pudo agregar el ServicioCamara.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar el ServicioCamara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
     @Override
    public void desactivarServicioCamara(DTServicioCamara scamaras)
            throws ExcepcionPersonalizada{
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("update ServicioCamara set TerminalGrabacion=null where NServicioCamara=?;");
            consulta2 = conexion.prepareStatement("update Servicio set BajaServicio=true where NServicio=?;");
            
            consulta.setInt(1, scamaras.getIdServicio());
            consulta2.setInt(1, scamaras.getIdServicio());
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
            throw new ExcepcionPersistencia("No se pudo Desactivar el Servicio.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
    @Override
   public DTServicioCamara buscarServicioCamara(int idservicio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Servicio s,ServicioCamara a where s.NServicio=a.NServicioCamara and a.NServicioCamara=? and BajaServicio=false;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setInt(1, idservicio);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTServicioCamara scamara = null;
            
            
     int nservicio;
     int id;
     long cedula;
         boolean terminal;
            boolean monitoreo;
            Date fecha;
         DTPropiedad propiedad;
            DTCliente cliente;
            if (resultadoConsulta.next()) {
                  nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                terminal=resultadoConsulta.getBoolean("TerminalGrabacion");
                cedula=resultadoConsulta.getLong("ClienteProp");
               propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                cliente=PCliente.getInstancia().buscarCliente(cedula);
                scamara = new DTServicioCamara(terminal,nservicio, fecha,monitoreo,propiedad,cliente);
            
            }
            
            return scamara;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el ServicioCamara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    @Override
public ArrayList<DTServicioCamara> listarSCamaras()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery("select * from Servicio s ,ServicioCamara a  where s.NServicio=a.NServicioCamara and BajaServicio=false;");
            
            ArrayList<DTServicioCamara> scamaras = new ArrayList();
            DTServicioCamara scamara;
            
               int nservicio;
               int id;
                long cedula;
               boolean terminal;
                boolean monitoreo;
                Date fecha;
                DTPropiedad propiedad;
                DTCliente cliente;
            while (resultadoConsulta.next()) {
               nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                terminal=resultadoConsulta.getBoolean("TerminalGrabacion");
                cedula=resultadoConsulta.getLong("ClienteProp");
               propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                cliente=PCliente.getInstancia().buscarCliente(cedula);
                scamara = new DTServicioCamara(terminal,nservicio, fecha,monitoreo,propiedad,cliente);
                scamaras.add(scamara);
            }
            
            return scamaras;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Servicios Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
   @Override
public ArrayList<DTServicioCamara> listarSCClientes(DTCliente cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement("select * from Servicio s ,ServicioCamara a  where s.NServicio=a.NServicioCamara and ClienteProp=? and BajaServicio=false;");
             consulta.setLong(1, cedula.getCedulaCliente()); 
            resultadoConsulta = consulta.executeQuery();
          ArrayList<DTServicioCamara> scamaras = new ArrayList();
            DTServicioCamara scamara;
            
               int nservicio;
               int id;
                long c;
               boolean terminal;
                boolean monitoreo;
                Date fecha;
                DTPropiedad propiedad;
                DTCliente cliente;
            while (resultadoConsulta.next()) {
               nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                terminal=resultadoConsulta.getBoolean("TerminalGrabacion");
                c=resultadoConsulta.getLong("ClienteProp");
               propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                cliente=PCliente.getInstancia().buscarCliente(c);
                scamara = new DTServicioCamara(terminal,nservicio, fecha,monitoreo,propiedad,cliente);
                scamaras.add(scamara);
            }
            
            return scamaras;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Servicios Camara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
 public DTServicioCamara buscarServicioCamara2(int idservicio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Servicio s,ServicioCamara a where s.NServicio=a.NServicioCamara and a.NServicioCamara=?;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setInt(1, idservicio);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTServicioCamara scamara = null;
            
            
     int nservicio;
     int id;
     long cedula;
         boolean terminal;
            boolean monitoreo;
            Date fecha;
         DTPropiedad propiedad;
            DTCliente cliente;
            if (resultadoConsulta.next()) {
                  nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                terminal=resultadoConsulta.getBoolean("TerminalGrabacion");
                cedula=resultadoConsulta.getLong("ClienteProp");
               propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                cliente=PCliente.getInstancia().buscarCliente(cedula);
                scamara = new DTServicioCamara(terminal,nservicio, fecha,monitoreo,propiedad,cliente);
            
            }
            
            return scamara;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el ServicioCamara.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
