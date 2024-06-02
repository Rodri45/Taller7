package uniandes.dpoo.taller7.interfaz1;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import uniandes.dpoo.taller7.modelo.Top10;

public class VentanaPrincipal extends JFrame {
    private Top10 top10;

    public VentanaPrincipal() {
        this.setTitle("Lights Out");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        top10 = new Top10();
        top10.cargarRecords(new File("data/top10.csv"));
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                salvarTop10();
            }
        });

        this.setVisible(true);
    }

    protected void salvarTop10() {
        try {
            top10.salvarRecords(new File("data/top10.csv"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
