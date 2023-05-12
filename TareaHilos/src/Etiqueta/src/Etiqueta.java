package Etiqueta.src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.*;
import java.io.*;


import Boton.src.Boton;

import SharedClass.Mensaje;

public class Etiqueta{
    JFrame frame;
    JLabel blinker;
    JButton button;
    JPanel panel;
    Socket client;
    ServerSocket server;
    ObjectInputStream input;
    ObjectOutputStream output;
    boolean activo;
    Mensaje mensaje;


    public Etiqueta(){
        frame = new JFrame("Tarea Programada POO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        blinker = new JLabel();
        blinker.setPreferredSize(new Dimension(100, 100));
        blinker.setOpaque(true);
        blinker.setBackground(Color.WHITE);
        panel = new JPanel(new BorderLayout());
        panel.add(blinker, BorderLayout.WEST);
        panel.add(new Boton().getButton(), BorderLayout.EAST);
        frame.add(panel);
        frame.pack();
        abrirConexion();
    }

    private void abrirConexion(){
        try{
            server = new ServerSocket(4567);
            frame.setVisible(true);
            while(true){
                client = server.accept();
                input = new ObjectInputStream(client.getInputStream());
                mensaje = (Mensaje)input.readObject();
                activo = mensaje.getMensaje();
                System.out.println("Etiqueta recibio de Boton: " + Boolean.toString(mensaje.getMensaje()));
                if(activo) {
                    Thread blinkThread = new Thread(() -> blink());
                    blinkThread.start();
                }
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(mensaje);
                output.flush();
                client.close();
            }
        }catch(Exception e2){
            System.out.println(e2);
        }
    }

    private void blink(){
        while(activo){
            try {
                blinker.setBackground(Color.YELLOW);
                Thread.sleep(500);
                blinker.setBackground(Color.WHITE);
                Thread.sleep(500);
            } catch (InterruptedException e) {   
                e.printStackTrace();
            }   
            
        }
    }
    
}
