package uniandes.dpoo.taller7.interfaz4;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class PanelSuperior extends JPanel {
    private JComboBox<String> selectTamaño;
    private JRadioButton bFacil;
    private JRadioButton bMedio;
    private JRadioButton bDificil;

    public PanelSuperior() {
        String[] options = {"3x3", "5x5", "9x9"};
        selectTamaño = new JComboBox<>(options);
        selectTamaño.setSelectedIndex(1);
        bFacil = new JRadioButton("Facil", false);
        bMedio = new JRadioButton("Medio", true);
        bDificil = new JRadioButton("Dificil", false);
        bFacil.setOpaque(false);
        bMedio.setOpaque(false);
        bDificil.setOpaque(false);
        ButtonGroup dificultad = new ButtonGroup();
        dificultad.add(bFacil);
        dificultad.add(bMedio);
        dificultad.add(bDificil);
        this.add(new JLabel("Tamaño:"));
        this.add(selectTamaño);
        this.add(new JLabel("Dificultad:"));
        this.add(this.bFacil);
        this.add(this.bMedio);
        this.add(this.bDificil);
        this.setBackground(new Color(42, 137, 224));
        for (Component c : this.getComponents()) {
            if (!(c instanceof JComboBox)) {
                c.setForeground(Color.WHITE);
            }
        }
    }

    public int getTamaño() {
        String selectedOption = (String) selectTamaño.getSelectedItem();
        int tamaño = 0;
        if (selectedOption.equals("3x3")) {
            tamaño = 3;
        } else if (selectedOption.equals("5x5")) {
            tamaño = 5;
        } else if (selectedOption.equals("9x9")) {
            tamaño = 9;
        }
        return tamaño;
    }

    public int getDificultad() {
        int dificultad = 0;
        if (bFacil.isSelected()) {
            dificultad = 5;
        } else if (bMedio.isSelected()) {
            dificultad = 10;
        } else if (bDificil.isSelected()) {
            dificultad = 15;
        }
        return dificultad;
    }
}

