package uniandes.dpoo.taller7.interfaz3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import uniandes.dpoo.taller7.modelo.Top10;
import uniandes.dpoo.taller7.modelo.RegistroTop10;
import uniandes.dpoo.taller7.modelo.Tablero;

public class VentanaPrincipal extends JFrame {
    private Top10 top10;
    private Tablero tablero;
    private PanelSuperior pSuperior;
    private PanelDerecho pDerecho;
    private PanelInferior pInferior;
    private PanelTablero pTablero;

    public VentanaPrincipal() {
        this.setTitle("Lights Out");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        top10 = new Top10();
        top10.cargarRecords(new File("data/top10.csv"));

        pSuperior = new PanelSuperior();
        pDerecho = new PanelDerecho();
        pInferior = new PanelInferior();
        pTablero = new PanelTablero(this);

        this.add(pSuperior, BorderLayout.NORTH);
        this.add(pDerecho, BorderLayout.EAST);
        this.add(pInferior, BorderLayout.SOUTH);
        this.add(pTablero, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                salvarTop10();
            }
        });

        pDerecho.setButtonActionListener(new ButtonActionListener());

        this.setVisible(true);
    }

    protected void salvarTop10() {
        try {
            top10.salvarRecords(new File("data/top10.csv"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void nuevoTablero() {
        int tamaño = pSuperior.getTamaño();
        int dificultad = pSuperior.getDificultad();
        tablero = new Tablero(tamaño);
        tablero.desordenar(dificultad);
        pTablero.nuevoTablero(tablero.darTablero());
        pInferior.actualizarPEstado(0, "Invitado");
    }

    public void reiniciarTablero() {
        if (tablero != null) {
            tablero.reiniciar();
            pTablero.nuevoTablero(tablero.darTablero());
            pInferior.actualizarPEstado(0, "Invitado");
        }
    }

    public void cambiarJugador() {
        JTextField field = new JTextField();
        Object[] message = {"Ingrese el nombre del jugador:", field};
        int option = JOptionPane.showOptionDialog(this, message, "Cambiar Jugador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION && !field.getText().isEmpty()) {
            String jugador = field.getText();
            pInferior.actualizarPEstado(0, jugador);
        }
    }

    public void mostrarTop10() {
        // Implementación del diálogo para mostrar el Top10
    }

    public void jugar(int fila, int columna) {
        if (tablero != null) {
            tablero.jugar(fila, columna);
            pInferior.actualizarPEstado(tablero.darJugadas(), "Invitado"); // Actualiza el nombre del jugador si es necesario
            if (tablero.tableroIluminado()) {
                if (top10.esTop10(tablero.calcularPuntaje())) {
                    RegistroTop10 nuevoRecord = new RegistroTop10("Invitado", tablero.calcularPuntaje());
                    top10.agregarRegistro(nuevoRecord.darNombre(), nuevoRecord.darPuntos());
                }
                JOptionPane.showMessageDialog(this, "¡Felicitaciones!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                tablero = null;
                pTablero.limpiarTablero();
            }
        }
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source.getText().equals("Nuevo")) {
                nuevoTablero();
            } else if (source.getText().equals("Reiniciar")) {
                reiniciarTablero();
            } else if (source.getText().equals("Top 10")) {
                mostrarTop10();
            } else if (source.getText().equals("Cambiar Jugador")) {
                cambiarJugador();
            }
        }
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
