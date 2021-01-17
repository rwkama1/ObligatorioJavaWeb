/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.datatypes;


public class DTPrecios {
    private double pbaseAlarma;
    private double pbaseCamara;
    private double tmonitoreoAlarma;
    private double tmonitoreoCamara;
    private double adicionalAlarma;
    private double adicionalCamara;

    public double getPbaseAlarma() {
        return pbaseAlarma;
    }

    public void setPbaseAlarma(double pbaseAlarma) {
        this.pbaseAlarma = pbaseAlarma;
    }

    public double getPbaseCamara() {
        return pbaseCamara;
    }

    public void setPbaseCamara(double pbaseCamara) {
        this.pbaseCamara = pbaseCamara;
    }

    public double getTmonitoreoAlarma() {
        return tmonitoreoAlarma;
    }

    public void setTmonitoreoAlarma(double tmonitoreoAlarma) {
        this.tmonitoreoAlarma = tmonitoreoAlarma;
    }

    public double getTmonitoreoCamara() {
        return tmonitoreoCamara;
    }

    public void setTmonitoreoCamara(double tmonitoreoCamara) {
        this.tmonitoreoCamara = tmonitoreoCamara;
    }

    public double getAdicionalAlarma() {
        return adicionalAlarma;
    }

    public void setAdicionalAlarma(double adicionalAlarma) {
        this.adicionalAlarma = adicionalAlarma;
    }

    public double getAdicionalCamara() {
        return adicionalCamara;
    }

    public void setAdicionalCamara(double adicionalCamara) {
        this.adicionalCamara = adicionalCamara;
    }

    public DTPrecios() {
        
    }

    public DTPrecios(double pbaseAlarma, double pbaseCamara, double tmonitoreoAlarma, double tmonitoreoCamara, double adicionalAlarma, double adicionalCamara) {
        this.pbaseAlarma = pbaseAlarma;
        this.pbaseCamara = pbaseCamara;
        this.tmonitoreoAlarma = tmonitoreoAlarma;
        this.tmonitoreoCamara = tmonitoreoCamara;
        this.adicionalAlarma = adicionalAlarma;
        this.adicionalCamara = adicionalCamara;
    }
    
}
