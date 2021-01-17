/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compartidos.beans.datatypes;

import java.io.Serializable;

/**
 *
 * @author Waldemar
 */
public class DTPropiedad implements Serializable {
    private int idpropiedad;
private String dirProp;
private String tipoProp;

    public int getIdpropiedad() {
        return idpropiedad;
    }

    public void setIdpropiedad(int idpropiedad) {
        this.idpropiedad = idpropiedad;
    }

    public String getDirProp() {
        return dirProp;
    }

    public void setDirProp(String dirProp) {
        this.dirProp = dirProp;
    }

    public String getTipoProp() {
        return tipoProp;
    }

    public void setTipoProp(String tipoProp) {
        this.tipoProp = tipoProp;
    }

    public DTPropiedad() {
    }

    public DTPropiedad(int idpropiedad, String dirProp, String tipoProp) {
        setIdpropiedad(idpropiedad);
       setDirProp(dirProp);
       setTipoProp(tipoProp);
    }
    

}
