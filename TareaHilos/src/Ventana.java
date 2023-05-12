import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana implements ActionListener{
    JFrame frame;
    JLabel blinker;
    JButton button;
    JPanel panel;

    boolean activo = false;

    public Ventana(){
        frame = new JFrame("Tarea Programada POO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        blinker = new JLabel();
        blinker.setPreferredSize(new Dimension(100, 100));
        blinker.setOpaque(true);
        blinker.setBackground(Color.WHITE);

        button = new JButton("Enceder");
        button.addActionListener(this);
        button.setActionCommand("encender");

        panel = new JPanel(new BorderLayout());
        panel.add(blinker, BorderLayout.WEST);
        panel.add(button, BorderLayout.EAST);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        blink();
    }

    private void blink(){
        while(true){
            //OJO, se necesita esta pausa
            System.out.println(activo);
            if (activo){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("encender")){
            button.setText("apagar");
            button.setActionCommand("apagar");
            activo = true;
        }
        else{
            button.setText("encender");
            button.setActionCommand("encender");
            activo = false;
        }
    }

}