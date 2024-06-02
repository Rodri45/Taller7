package uniandes.dpoo.taller7.interfaz3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class PanelTablero extends JPanel implements MouseListener {
    private VentanaPrincipal ventanaPrincipal;
    private boolean[][] tablero;
    private RoundRectangle2D[][] casillas;
    private int altoCasilla;
    private int anchoCasilla;

    public PanelTablero(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.addMouseListener(this);
    }

    public void nuevoTablero(boolean[][] tablero) {
        this.tablero = tablero;
        casillas = new RoundRectangle2D[tablero.length][tablero.length];
        altoCasilla = (getHeight() - (10 * (tablero.length + 1))) / tablero.length;
        anchoCasilla = (getWidth() - (10 * (tablero.length + 1))) / tablero.length;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero.length; columna++) {
                int xPos = 10 + columna * (anchoCasilla + 10);
                int yPos = 10 + fila * (altoCasilla + 10);
                casillas[fila][columna] = new RoundRectangle2D.Double(xPos, yPos, anchoCasilla, altoCasilla, 20, 20);
            }
        }
        repaint();
    }

    public void limpiarTablero() {
        this.tablero = null;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        if (tablero != null) {
            altoCasilla = (getHeight() - (10 * (tablero.length + 1))) / tablero.length;
            anchoCasilla = (getWidth() - (10 * (tablero.length + 1))) / tablero.length;
            int x = 10;
            int y = 10;
            for (int fila = 0; fila < tablero.length; fila++) {
                for (int columna = 0; columna < tablero.length; columna++) {
                    if (tablero[fila][columna]) {
                        g2.setColor(Color.YELLOW);
                    } else {
                        g2.setColor(Color.GRAY);
                    }
                    g2.fillRoundRect(x, y, anchoCasilla, altoCasilla, 20, 20);
                    g2.setColor(Color.BLACK);
                    g2.drawRoundRect(x, y, anchoCasilla, altoCasilla, 20, 20);
                    x += anchoCasilla + 10;
                }
                y += altoCasilla + 10;
                x = 10;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int[] casilla = convertirCoordenadasACasilla(x, y);
        if (casillas[casilla[0]][casilla[1]].contains(x, y)) {
            ventanaPrincipal.jugar(casilla[0], casilla[1]);
            repaint();
        }
    }

    private int[] convertirCoordenadasACasilla(int x, int y) {
        int fila = (int) (y / (altoCasilla + 10));
        int columna = (int) (x / (anchoCasilla + 10));
        return new int[] { fila, columna };
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}

