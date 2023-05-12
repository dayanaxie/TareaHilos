package Boton.src;

import javax.swing.*;

import SharedClass.Mensaje;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*;



public class Boton implements ActionListener{
    JButton button;
    boolean activo;
    ObjectOutputStream output;
    ObjectInputStream input;
    Mensaje mensaje;

    public Boton(){
        activo = false;
        button = new JButton("encender");
        button.setActionCommand("encender");
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Socket client = new Socket("127.0.0.1", 4567);
            mensaje = new Mensaje();
            // lo que le voy a mandar
            output = new ObjectOutputStream(client.getOutputStream());
            if(e.getActionCommand().equals("encender")){
                System.out.println("Boton presionado encendido");
                button.setText("apagar");
                button.setActionCommand("apagar");
                
                System.out.println("pausa");
                activo = true;
                mensaje.setMensaje(true);
            }
            else{
                System.out.println("Boton presionado apagar");
                button.setText("encender");
                button.setActionCommand("encender");
                activo = false;
                mensaje.setMensaje(false);
            }
            output.writeObject(mensaje);
            output.flush();

            input = new ObjectInputStream(client.getInputStream());
            mensaje = (Mensaje)input.readObject();
            System.out.println("Boton recibio de Etiqueta: " + Boolean.toString(mensaje.getMensaje()));

            input.close();
            output.close();
            client.close();
        }catch(Exception e2){
            System.out.println(e2);
        }
    
    }
    public JButton getButton() {
        return button;
    }
    public void setButton(JButton button) {
        this.button = button;
    }

    
}
