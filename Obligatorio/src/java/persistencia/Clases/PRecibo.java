/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Clases;

import compartidos.beans.datatypes.DTCliente;
import compartidos.beans.datatypes.DTCobrador;
import compartidos.beans.datatypes.DTLineaRecibo;
import compartidos.beans.datatypes.DTRecibo;
import compartidos.beans.datatypes.DTServicio;
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
import logica.FabricaLogica;
import persistencia.Interfaces.IPRecibo;

/**
 *
 * @author Waldemar
 */
public class PRecibo implements IPRecibo{
     private static PRecibo instancia = null;
    
    
    public static PRecibo getInstancia() {
        if (instancia == null) {
            instancia = new PRecibo();
        }
        return instancia;
    }
     private PRecibo() {
        
    }
     @Override
    public void agregarRecibo(DTRecibo recibo)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        CallableStatement consulta = null;
      
       
        try {
            
            java.sql.Date fechaSql=new java.sql.Date(recibo.getFechaFacturacion().getTime());
            conexion = UtilidadesPersistencia.getConexion();
             conexion.setAutoCommit(false);
           consulta = conexion.prepareCall("{ CALL AgregarRecibo(?,?,?) }");
           consulta.setDouble(1,recibo.getImportetotal());
           consulta.setDate(2, fechaSql);
           consulta.setLong(3, recibo.getCliente().getCedulaCliente());
           consulta.executeUpdate();                                        
             for(DTLineaRecibo l:recibo.getLineasRecibo())
               {
                consulta = conexion.prepareCall("{ CALL AgregarLinea(?,?) }");
                consulta.setInt(1,l.getServicelinea().getIdServicio());
                consulta.setDouble(2,l.getImporteLinea()); 
                consulta.executeUpdate();                                        
            }
                conexion.commit();
        } catch (Exception ex) {
              if (conexion != null) {
                 try {
                     conexion.rollback();
                 } catch (SQLException ex1) {
                    throw new ExcepcionPersistencia("¡ERROR! Ocurrió un error al deshacer los cambios.", ex1);
                 }
                
                }
            throw new ExcepcionPersistencia("No se pudo agregar el Recibo.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
        
    }
          @Override
    public ArrayList<DTRecibo> listarRecibos()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery("select * from ReciboCobro;");
            
            ArrayList<DTRecibo> recibos = new ArrayList<DTRecibo>();
            DTRecibo r;
            
            int idrecibo;
            Date fecha;
            double total;
            long cedCobrador;
            long clienteR;
            
            while (resultadoConsulta.next()) {
                 idrecibo = resultadoConsulta.getInt("IDRecibo");
                fecha = resultadoConsulta.getDate("FechaFacturacion");
                 total = resultadoConsulta.getDouble("ImporteTotal");
                cedCobrador = resultadoConsulta.getLong("CedCobrador");
                clienteR=resultadoConsulta.getLong("ClienteR");
                DTCliente cliente=PCliente.getInstancia().buscarCliente(clienteR);
                 DTCobrador cobrador=PCobrador.getInstancia().buscarCobrador(cedCobrador);
                r = new DTRecibo(idrecibo,total,fecha,listarLineas(idrecibo),cliente,cobrador);
                recibos.add(r);
            }
            
            return recibos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los recibos.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
     ArrayList<DTLineaRecibo> listarLineas(int id)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select l.*,s.Propiedad from LineaReciboCobro l inner join Servicio s on l.NServicioLinea=s.NServicio where IDReciboLinea=?;";
        
        try {
            conexion = UtilidadesPersistencia.getConexion();          
            consulta = conexion.prepareStatement(sql);
            consulta.setInt(1,id);
            resultadoConsulta = consulta.executeQuery();
            
            ArrayList<DTLineaRecibo> lineas = new ArrayList();
            DTLineaRecibo l;
            
            int idlinea;
            int idserv;
            double importe;
            
            while (resultadoConsulta.next()) {
                 idlinea = resultadoConsulta.getInt("IDLinea");
                idserv = resultadoConsulta.getInt("NServicioLinea");
                 importe = resultadoConsulta.getDouble("Importe");
                 DTServicio serv=FabricaLogica.getLogicaServicio().buscarServicio(idserv);
                l = new DTLineaRecibo(idlinea,importe,serv);
                lineas.add( l);
            }
            
            return lineas;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar las Lineas.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
          @Override
       public DTRecibo buscarRecibos(int id)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement("select * from ReciboCobro where IDRecibo=?;");
            
            consulta.setInt(1, id);
            
            resultadoConsulta = consulta.executeQuery();
            
             DTRecibo r=null;
            
            int idrecibo;
            Date fecha;
            double total;
            long cedCobrador;
            long clienteR;
            
            while (resultadoConsulta.next()) {
                 idrecibo = resultadoConsulta.getInt("IDRecibo");
                fecha = resultadoConsulta.getDate("FechaFacturacion");
                 total = resultadoConsulta.getDouble("ImporteTotal");
                cedCobrador = resultadoConsulta.getLong("CedCobrador");
                clienteR=resultadoConsulta.getLong("ClienteR");
                DTCliente cliente=PCliente.getInstancia().buscarCliente(clienteR);
                 DTCobrador cobrador=PCobrador.getInstancia().buscarCobrador(cedCobrador);
                r = new DTRecibo(idrecibo,total,fecha,listarLineas(idrecibo),cliente,cobrador);
            }
            
            return r;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo buscar el Cliente.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
        @Override
    public void cobrarRecibo(DTRecibo recibo)
            throws ExcepcionPersonalizada {
          Connection conexion = null;
        PreparedStatement consulta = null;
        String sql="update ReciboCobro set CedCobrador=? where IDRecibo=?;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            consulta = conexion.prepareStatement(sql);
             consulta.setLong(1,recibo.getCobrador().getCedulaEmp());
            consulta.setInt(2,recibo.getIdRecibo());
         ;
            int filasAfectadas = consulta.executeUpdate();
            
            if (filasAfectadas < 1) {
                throw new Exception();
             
            } 
        } catch (ExcepcionPersonalizada ex) {
            throw ex;
        } catch (Exception ex) {
        
            throw new ExcepcionPersistencia("No se pudo Cobrar el Recibo.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(consulta, conexion);
        }
    }
      public ArrayList<DTRecibo> listarRSC()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery("select * from ReciboCobro where CedCobrador is null;");
            
            ArrayList<DTRecibo> recibos = new ArrayList<DTRecibo>();
            DTRecibo r;
            
            int idrecibo;
            Date fecha;
            double total;
            long cedCobrador;
            long clienteR;
            
            while (resultadoConsulta.next()) {
                 idrecibo = resultadoConsulta.getInt("IDRecibo");
                fecha = resultadoConsulta.getDate("FechaFacturacion");
                 total = resultadoConsulta.getDouble("ImporteTotal");
                cedCobrador = resultadoConsulta.getLong("CedCobrador");
                clienteR=resultadoConsulta.getLong("ClienteR");
                DTCliente cliente=PCliente.getInstancia().buscarCliente(clienteR);
                 DTCobrador cobrador=PCobrador.getInstancia().buscarCobrador(cedCobrador);
                r = new DTRecibo(idrecibo,total,fecha,listarLineas(idrecibo),cliente,cobrador);
                recibos.add(r);
            }
            
            return recibos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los recibos.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
      }
         public ArrayList<DTRecibo> listarRCC()
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        Statement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="select * from ReciboCobro r inner join Cobrador c on r.CedCobrador=c.CedulaCobrador where r.CedCobrador is not null;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.createStatement();
            
            resultadoConsulta = consulta.executeQuery(sql);
            
            ArrayList<DTRecibo> recibos = new ArrayList<DTRecibo>();
            DTRecibo r;
            
            int idrecibo;
            Date fecha;
            double total;
            long cedCobrador;
            long clienteR;
            
            while (resultadoConsulta.next()) {
                 idrecibo = resultadoConsulta.getInt("IDRecibo");
                fecha = resultadoConsulta.getDate("FechaFacturacion");
                 total = resultadoConsulta.getDouble("ImporteTotal");
                cedCobrador = resultadoConsulta.getLong("CedCobrador");
                clienteR=resultadoConsulta.getLong("ClienteR");
                DTCliente cliente=PCliente.getInstancia().buscarCliente(clienteR);
                 DTCobrador cobrador=PCobrador.getInstancia().buscarCobrador2(cedCobrador);
                r = new DTRecibo(idrecibo,total,fecha,listarLineas(idrecibo),cliente,cobrador);
                recibos.add(r);
            }
            
            return recibos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los recibos.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
      }
               @Override
    public ArrayList<DTRecibo> buscoRecibos(String criterio)
            throws ExcepcionPersonalizada {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultadoConsulta = null;
        String sql="Select r.* From ReciboCobro r inner join Cliente c on r.ClienteR=c.CedulaCliente where c.ZonaBarrio LIKE ?;";
        try {
            conexion = UtilidadesPersistencia.getConexion();
            
            consulta = conexion.prepareStatement(sql);
            consulta.setString(1, "%" + criterio + "%");
            
            resultadoConsulta = consulta.executeQuery();
            
           ArrayList<DTRecibo> recibos = new ArrayList<DTRecibo>();
            DTRecibo r;
            
            int idrecibo;
            Date fecha;
            double total;
            long cedCobrador;
            long clienteR;
            
            while (resultadoConsulta.next()) {
                 idrecibo = resultadoConsulta.getInt("IDRecibo");
                fecha = resultadoConsulta.getDate("FechaFacturacion");
                 total = resultadoConsulta.getDouble("ImporteTotal");
                cedCobrador = resultadoConsulta.getLong("CedCobrador");
                clienteR=resultadoConsulta.getLong("ClienteR");
                DTCliente cliente=PCliente.getInstancia().buscarCliente(clienteR);
                 DTCobrador cobrador=PCobrador.getInstancia().buscarCobrador(cedCobrador);
                r = new DTRecibo(idrecibo,total,fecha,listarLineas(idrecibo),cliente,cobrador);
                recibos.add(r);
            }
            
            return recibos;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los recibos.", ex);
        } finally {
            UtilidadesPersistencia.cerrarRecursos(resultadoConsulta, consulta, conexion);
        }
    }
}
