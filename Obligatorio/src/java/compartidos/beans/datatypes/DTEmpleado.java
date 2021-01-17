/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.datatypes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Waldemar
 */
public class DTEmpleado implements Serializable{
     private long cedulaEmp;
    private String nomEmp;
    private String claveEmp;
    private double sueldoEmp;
    private Date fechaEmp;

    public long getCedulaEmp() {
        return cedulaEmp;
    }

    public void setCedulaEmp(long cedulaEmp) {
        this.cedulaEmp = cedulaEmp;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public String getClaveEmp() {
        return claveEmp;
    }

    public void setClaveEmp(String claveEmp) {
        this.claveEmp = claveEmp;
    }

    public double getSueldoEmp() {
        return sueldoEmp;
    }

    public void setSueldoEmp(double sueldoEmp) {
        this.sueldoEmp = sueldoEmp;
    }

    public Date getFechaEmp() {
        return fechaEmp;
    }

    public void setFechaEmp(Date fechaEmp) {
        this.fechaEmp = fechaEmp;
    }

    public DTEmpleado() {       
      
    }
    
    public DTEmpleado(long cedulaEmp, String nomEmp, String claveEmp, double sueldoEmp, Date fechaEmp) {
      setCedulaEmp(cedulaEmp);
        setNomEmp(nomEmp);
        setClaveEmp(claveEmp);
        setSueldoEmp(sueldoEmp);
        setFechaEmp(fechaEmp);
    }

    
}
