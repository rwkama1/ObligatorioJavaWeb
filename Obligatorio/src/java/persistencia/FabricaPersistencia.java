/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import persistencia.Clases.PAdministrador;
import persistencia.Clases.PCliente;
import persistencia.Clases.PCobrador;
import persistencia.Clases.PDispositivoAlarma;
import persistencia.Clases.PDispositivoCamara;
import persistencia.Clases.PPrecios;
import persistencia.Clases.PPropiedad;
import persistencia.Clases.PRecibo;
import persistencia.Clases.PServicioAlarma;
import persistencia.Clases.PServicioCamara;

import persistencia.Clases.PTecnico;
import persistencia.Interfaces.IPAdministrador;
import persistencia.Interfaces.IPCliente;
import persistencia.Interfaces.IPCobrador;
import persistencia.Interfaces.IPDispositivoAlarma;
import persistencia.Interfaces.IPDispositivoCamara;
import persistencia.Interfaces.IPPrecios;
import persistencia.Interfaces.IPPropiedad;
import persistencia.Interfaces.IPRecibo;
import persistencia.Interfaces.IPServicioAlarma;
import persistencia.Interfaces.IPServicioCamara;

import persistencia.Interfaces.IPTecnico;

/**
 *
 * @author Waldemar
 */
public class FabricaPersistencia {
      public static IPAdministrador getPAdministrador() {
        return PAdministrador.getInstancia();
    }
       public static IPCobrador getPCobrador() {
        return PCobrador.getInstancia();
    }
         public static IPTecnico getPTecnico() {
        return PTecnico.getInstancia();
    }
       public static IPCliente getPCliente() {
        return PCliente.getInstancia();
    }
         public static IPPropiedad getPPropiedad() {
        return PPropiedad.getInstancia();
    }
            public static IPServicioCamara getPSCamara() {
        return PServicioCamara.getInstancia();
    }
         public static IPServicioAlarma getPSAlarma() {
        return PServicioAlarma.getInstancia();
    }
           public static IPDispositivoCamara getPDCamara() {
        return PDispositivoCamara.getInstancia();
    }
       public static IPDispositivoAlarma getPDAlarma() {
        return PDispositivoAlarma.getInstancia();
    }
         public static IPPrecios getPPrecios() {
        return PPrecios.getInstancia();
    }
       public static IPRecibo getrecibo() {
        return PRecibo.getInstancia();
    }

}

