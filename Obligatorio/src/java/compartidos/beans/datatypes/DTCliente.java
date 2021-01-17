/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.datatypes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Waldemar
 */
public class DTCliente implements Serializable {
     private long cedulaCliente;
    private String nomCliente;
    private String dirCliente;
    private String zonabarrioCliente;
    private String telCliente;
    private ArrayList<DTPropiedad> propCliente;

    public long getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(long cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getDirCliente() {
        return dirCliente;
    }

    public void setDirCliente(String dirCliente) {
        this.dirCliente = dirCliente;
    }

    public String getZonabarrioCliente() {
        return zonabarrioCliente;
    }

    public void setZonabarrioCliente(String zonabarrioCliente) {
        this.zonabarrioCliente = zonabarrioCliente;
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    public ArrayList<DTPropiedad> getPropCliente() {
        return propCliente;
    }

    public void setPropCliente(ArrayList<DTPropiedad> propCliente) {
        this.propCliente = propCliente;
    }

    public DTCliente() {
    }

    public DTCliente(long cedulaCliente, String nomCliente, String dirCliente, String zonabarrioCliente, String telCliente, ArrayList<DTPropiedad> propCliente) {
        setCedulaCliente(cedulaCliente);
        setNomCliente(nomCliente);
        setDirCliente(dirCliente);
        setZonabarrioCliente(zonabarrioCliente);
        setTelCliente(telCliente);
        setPropCliente(propCliente);
    }

   
    
}
