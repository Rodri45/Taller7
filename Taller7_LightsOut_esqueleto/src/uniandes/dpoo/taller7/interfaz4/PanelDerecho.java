package uniandes.dpoo.taller7.interfaz4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelDerecho extends JPanel {
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
        this.add(Box.createVerticalStrut(10));
        this.add(this.bReiniciar);
        this.add(Box.createVerticalStrut(10));
        this.add(this.bTop10);
        this.add(Box.createVerticalStrut(10));
        this.add(this.bCambiarJugador);
        for (Component c : this.getComponents()) {
            c.setForeground(Color.WHITE);
            if (c instanceof JButton) {
                c.setBackground(new Color(72, 156, 225));
                c.setMaximumSize(new Dimension(Integer.MAX_VALUE, c.getPreferredSize().height + 10));
            }
        }
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    public void setButtonActionListener(ActionListener listener) {
        bNuevo.addActionListener(listener);
        bReiniciar.addActionListener(listener);
        bTop10.addActionListener(listener);
        bCambiarJugador.addActionListener(listener);
    }
}
