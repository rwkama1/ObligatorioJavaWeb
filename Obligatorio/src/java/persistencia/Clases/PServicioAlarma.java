/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTPropiedad;
import compartidos.beans.datatypes.DTServicio;
import compartidos.beans.datatypes.DTServicioAlarma;
import compartidos.beans.excepciones.ExcepcionPersistencia;
import compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import persistencia.Interfaces.IPServicioAlarma;

/**
 *
 * @author Waldemar
 */
public class PServicioAlarma implements IPServicioAlarma{
      private static PServicioAlarma instancia = null;

    public static PServicioAlarma getInstancia() {
        if (instancia == null) {
            instancia = new PServicioAlarma();
        }
        return instancia;
    }
     private PServicioAlarma() {
         
     }
       @Override
    public void agregarCSA(DTServicioAlarma sa,DTCliente c)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
          ArrayList<DTPropiedad> lista=c.getPropCliente();
         java.sql.Date fechaSql=new java.sql.Date(sa.getFechaServicio().getTime());
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareCall("{ CALL AgregarCSA(?,?,?,?,?,?,?,?,?,?) }");
          consulta.setLong(1, c.getCedulaCliente());
          consulta.setString(2, c.getNomCliente());
          consulta.setString(3, c.getDirCliente());
          consulta.setString(4, c.getZonabarrioCliente());
          consulta.setString(5, c.getTelCliente());
  
           consulta.setString(6,lista.get(lista.size()-1).getTipoProp());
           consulta.setString(7,lista.get(lista.size()-1).getDirProp());
           
          consulta.setDate(8, fechaSql);
          consulta.setBoolean(9,sa.isMoniteroServicio());
          consulta.setInt(10, sa.getCodigoanulacion());
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
    public void agregarServicioAlarma(DTServicioAlarma salarma)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            java.sql.Date fechaSql=new java.sql.Date(salarma.getFechaServicio().getTime());
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("INSERT INTO Servicio VALUES (null,?,?,?,?,false);");
            consulta2 = conexion.prepareStatement("INSERT INTO ServicioAlarma VALUES (LAST_INSERT_ID(),?);");
         
            consulta.setDate(1, fechaSql);
            consulta.setBoolean(2, salarma.isMoniteroServicio());
            consulta.setInt(3,salarma.getPropServicio().getIdpropiedad());   
            consulta.setLong(4,salarma.getClienteServicio().getCedulaCliente());   
            consulta2.setInt(1,salarma.getCodigoanulacion());
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
                throw new ExcepcionPersistencia("El ServicioAlarma ya existe.", ex);
            } else {
                
                throw new ExcepcionPersistencia("No se pudo agregar el ServicioAlarma.", ex);
            }
        } catch (Exception ex) {
             if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar el ServicioAlarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
     @Override
    public void desactivarServicioAlarma(DTServicioAlarma salarma)
            throws ExcepcionPersonalizada{
        Connection conexion = null;
        PreparedStatement consulta = null;
        PreparedStatement consulta2 = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            conexion.setAutoCommit(false);
            consulta = conexion.prepareStatement("update ServicioAlarma set CodigoAnulacion=null where NServicioAlarma=?;");
            consulta2 = conexion.prepareStatement("update Servicio set BajaServicio=true where NServicio=?;");
            
            consulta.setInt(1, salarma.getIdServicio());
            consulta2.setInt(1, salarma.getIdServicio());
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
   public DTServicioAlarma buscarServicioAlarma(int idservicio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Servicio s , ServicioAlarma a  where s.NServicio=a.NServicioAlarma  and a.NServicioAlarma=? and BajaServicio=false;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setInt(1, idservicio);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTServicioAlarma salarma = null;
            
            
            int nservicio;
            int id;
            long cedula;
            int codigo;
            boolean monitoreo;
            Date fecha;
         DTPropiedad propiedad;
            DTCliente cliente;
            if (resultadoConsulta.next()) {
                  nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                codigo=resultadoConsulta.getInt("CodigoAnulacion");
                cedula=resultadoConsulta.getLong("ClienteProp");
               propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                cliente=PCliente.getInstancia().buscarCliente(cedula);
                salarma = new DTServicioAlarma(codigo,nservicio, fecha,monitoreo,propiedad,cliente);
            
            }
            
            return salarma;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el ServicioAlarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    @Override
    public ArrayList<DTServicioAlarma> listarSAlarmas()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Servicio s , ServicioAlarma a  where s.NServicio=a.NServicioAlarma and BajaServicio=false;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery(sql);
            
            ArrayList<DTServicioAlarma> salarmas = new ArrayList();
            DTServicioAlarma salarma;
            
             int nservicio;
            int id;
             long cedula;
             int codigo;
            boolean monitoreo;
            Date fecha;
         DTPropiedad propiedad;
            DTCliente cliente;
            while (resultadoConsulta.next()) {
               nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                codigo=resultadoConsulta.getInt("CodigoAnulacion");
                cedula=resultadoConsulta.getLong("ClienteProp");
               propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                cliente=PCliente.getInstancia().buscarCliente(cedula);
                salarma = new DTServicioAlarma(codigo,nservicio,fecha,monitoreo,propiedad,cliente);
                salarmas.add(salarma);
            }
            
            return salarmas;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Servicios Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
      
    //SOLO SERVICIOS
      @Override
   public  ArrayList<DTServicioAlarma> listarSACliente(DTCliente cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Servicio s , ServicioAlarma a  where s.NServicio=a.NServicioAlarma and ClienteProp=? and BajaServicio=false;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement(sql);
            consulta.setLong(1, cedula.getCedulaCliente());
            
            resultadoConsulta = consulta.executeQuery();
            
           ArrayList<DTServicioAlarma> salarmas = new ArrayList();
            DTServicioAlarma salarma;
             int nservicio;
            int id;
             long c;
             int codigo;
            boolean monitoreo;
            Date fecha;
         DTPropiedad propiedad;
            DTCliente cliente;
            while (resultadoConsulta.next()) {
               nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                codigo=resultadoConsulta.getInt("CodigoAnulacion");
                c=resultadoConsulta.getLong("ClienteProp");
               propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                cliente=PCliente.getInstancia().buscarCliente(c);
                salarma = new DTServicioAlarma(codigo,nservicio,fecha,monitoreo,propiedad,cliente);
                salarmas.add(salarma);
            }
            
            return salarmas;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Servicios Alarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    
      @Override
   public DTServicio buscarSCliente(DTCliente cedula)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Servicio where ClienteProp=?;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement(sql);
            consulta.setLong(1, cedula.getCedulaCliente());
            
            resultadoConsulta = consulta.executeQuery();
           ArrayList<DTServicio> lista=new  ArrayList<DTServicio>();
           
            DTServicio s=null;     
             boolean monitoreo;
            Date fecha;
            int id;
         DTPropiedad propiedad;
             int nservicio;
            DTCliente cliente;
            long c;
            while (resultadoConsulta.next()) {
               nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                c=resultadoConsulta.getLong("ClienteProp");
                cliente=PCliente.getInstancia().buscarCliente(c);
                propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                s = new DTServicio(nservicio,fecha,monitoreo,propiedad,cliente);
         
            }
           return s;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los Servicios.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
    public DTServicioAlarma buscarServicioAlarma2(int idservicio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from Servicio s , ServicioAlarma a  where s.NServicio=a.NServicioAlarma  and a.NServicioAlarma=?;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            
            consulta.setInt(1, idservicio);
            
            resultadoConsulta = consulta.executeQuery();
            
            DTServicioAlarma salarma = null;
            
            
            int nservicio;
            int id;
            long cedula;
            int codigo;
            boolean monitoreo;
            Date fecha;
         DTPropiedad propiedad;
            DTCliente cliente;
            if (resultadoConsulta.next()) {
                  nservicio = resultadoConsulta.getInt("NServicio");
                fecha = resultadoConsulta.getDate("FechaContratacion");
                monitoreo = resultadoConsulta.getBoolean("Monitoreo");
                id = resultadoConsulta.getInt("Propiedad");
                codigo=resultadoConsulta.getInt("CodigoAnulacion");
                cedula=resultadoConsulta.getLong("ClienteProp");
               propiedad=PPropiedad.getInstancia().buscarPropiedad(id);
                cliente=PCliente.getInstancia().buscarCliente(cedula);
                salarma = new DTServicioAlarma(codigo,nservicio, fecha,monitoreo,propiedad,cliente);
            
            }
            
            return salarma;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el ServicioAlarma.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
