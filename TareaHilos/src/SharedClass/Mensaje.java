package SharedClass;

import java.io.Serializable;

public class Mensaje implements Serializable{
    boolean msj;

    public Mensaje(){
 
    }

    public void setMensaje(boolean m){
        msj = m;

    }

    public boolean getMensaje(){
        return msj;
    }



}


