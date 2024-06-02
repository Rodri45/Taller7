package uniandes.dpoo.taller7.interfaz2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelDerecho extends JPanel implements ActionListener {
    private JButton bNuevo;
    private JButton bReiniciar;
    private JButton bTop10;
    private JButton bCambiarJugador;

    public PanelDerecho() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        bNuevo = new JButton("Nuevo");
        bReiniciar = new JButton("Reiniciar");
        bTop10 = new JButton("Top 10");
        bCambiarJugador = new JButton("Cambiar Jugador");
        this.add(Box.createVerticalStrut(210));
        this.add(this.bNuevo);
        this.bNuevo.addActionListener(this);
        this.add(Box.createVerticalStrut(10));
        this.add(this.bReiniciar);
        this.bReiniciar.addActionListener(this);
        this.add(Box.createVerticalStrut(10));
        this.add(this.bTop10);
        this.bTop10.addActionListener(this);
        this.add(Box.createVerticalStrut(10));
        this.add(this.bCambiarJugador);
        this.bCambiarJugador.addActionListener(this);
        for (Component c : this.getComponents()) {
            c.setForeground(Color.WHITE);
            if (c instanceof JButton) {
                c.setBackground(new Color(72, 156, 225));
                c.setMaximumSize(new Dimension(Integer.MAX_VALUE, c.getPreferredSize().height + 10));
            }
        }
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implementación de los eventos más adelante
    }
}
