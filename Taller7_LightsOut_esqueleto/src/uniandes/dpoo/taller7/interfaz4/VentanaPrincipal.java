package uniandes.dpoo.taller7.interfaz4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

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
    private String jugador = "Invitado";

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
        pInferior.actualizarPEstado(0, jugador);
    }

    public void reiniciarTablero() {
        if (tablero != null) {
            tablero.reiniciar();
            pTablero.nuevoTablero(tablero.darTablero());
            pInferior.actualizarPEstado(0, jugador);
        }
    }

    public void cambiarJugador() {
        JTextField field = new JTextField();
        Object[] message = {"Ingrese el nombre del jugador:", field};
        int option = JOptionPane.showOptionDialog(this, message, "Cambiar Jugador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION && !field.getText().isEmpty()) {
            jugador = field.getText();
            pInferior.actualizarPEstado(0, jugador);
        }
    }

    public void mostrarTop10() {
        JDialog dialog = new JDialog(this, "Top 10", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("#  Jugador   Puntos");
        titulo.setOpaque(true);
        titulo.setBackground(new Color(92, 166, 229));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(titulo, BorderLayout.NORTH);
        DefaultListModel<String> model = new DefaultListModel<>();
        int index = 1;
        for (RegistroTop10 registroTop10 : top10.darRegistros()) {
            model.addElement(Integer.toString(index) + "   " + registroTop10.toString());
            index++;
        }
        JList<String> list = new JList<>(model);
        list.setFont(new Font("Arial", Font.PLAIN, 16));
        list.setFixedCellHeight(30);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionBackground(Color.GRAY);
        list.setSelectedIndex(0);
        for (int i = 0; i < list.getModel().getSize(); i++) {
            JLabel label = (JLabel) list.getCellRenderer().getListCellRendererComponent(list, list.getModel().getElementAt(i), i, false, false);
            label.setHorizontalAlignment(JLabel.CENTER);
        }
        JScrollPane scrollPane = new JScrollPane(list);
        panel.add(scrollPane, BorderLayout.CENTER);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setSize(300, dialog.getHeight());
        dialog.setVisible(true);
    }

    public void jugar(int fila, int columna) {
        if (tablero != null) {
            tablero.jugar(fila, columna);
            pInferior.actualizarPEstado(tablero.darJugadas(), jugador);
            if (tablero.tableroIluminado()) {
                int puntaje = tablero.calcularPuntaje();
                if (top10.esTop10(puntaje)) {
                    RegistroTop10 nuevoRecord = new RegistroTop10(jugador, puntaje);
                    top10.agregarRegistro(nuevoRecord.darNombre(), nuevoRecord.darPuntos());
                    salvarTop10(); 
                    mostrarTop10(); 
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
