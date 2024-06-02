package uniandes.dpoo.taller7.interfaz4;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInferior extends JPanel {
    private JLabel lJugadas;
    private JLabel lJugador;

    public PanelInferior() {
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Jugadas: "));
        this.lJugadas = new JLabel("0");
        this.add(this.lJugadas);
        this.add(new JLabel(repeatSpaces(10)));
        this.add(new JLabel("Jugador: "));
        this.lJugador = new JLabel("Invitado");
        this.add(this.lJugador);
        this.setBackground(Color.LIGHT_GRAY);
    }

    public void actualizarPEstado(int jugadas, String jugador) {
        lJugadas.setText(Integer.toString(jugadas));
        lJugador.setText(jugador);
    }

    private String repeatSpaces(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
}
