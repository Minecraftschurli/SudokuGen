package at.ac.tgm.hit.gburkl.sudoku;

import javax.swing.*;
import java.awt.*;

/**
 * @author Georg Burkl <gburkl@student.tgm.ac.at>
 * @version 2021-03-06
 */
public class SudokuView extends JLayeredPane {
    private int dimension;
    private SudokuSpiel spiel;
    private JTextField[][] numbers;
    private final JPanel numberPanel = new JPanel();

    public SudokuView(SudokuSpiel spiel) {
        this.setSpiel(spiel);
        setLayout(new GridBagLayout());
        this.addLayer(numberPanel, DEFAULT_LAYER);
        this.addLayer(new Grid(), PALETTE_LAYER);
    }

    public void addLayer(JComponent component, int layer) {
        this.setLayer(component, layer);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(component, gbc);
    }

    public void setSpiel(SudokuSpiel spiel) {
        if (spiel == null) {
            throw new IllegalArgumentException("Spiel must not be null!");
        }
        if (!spiel.equals(this.spiel)) {
            this.spiel = spiel;
            this.numberPanel.removeAll();
            if (this.dimension != this.spiel.getDimension()) {
                this.dimension = this.spiel.getDimension();
                this.numbers = new JTextField[this.dimension][this.dimension];
                this.numberPanel.setLayout(new GridLayout(this.dimension, this.dimension));
            }
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    int num = this.spiel.get(row, col);
                    this.numbers[row][col] = new Number(num);
                    this.numberPanel.add(this.numbers[row][col], DEFAULT_LAYER, row * dimension + col-1);
                }
            }
        }
    }

    public boolean checkSpiel() {
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                final JTextField field = this.numbers[row][col];
                int num;
                try {
                    num = Integer.parseInt(field.getText());
                } catch (NumberFormatException e) {
                    return false;
                }
                if ((field.isEditable() && !this.spiel.check(row, col, num)) || num == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void lock() {
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                this.numbers[row][col].setEditable(false);
            }
        }
    }

    static class Number extends JTextField {
        public Number(int num) {
            super(num > 0 ? String.valueOf(num) : "", 1);
            this.setFont(this.getFont().deriveFont(Font.BOLD, 30));
            this.setEditable(num <= 0);
            this.setHorizontalAlignment(JTextField.CENTER);
        }
    }

    class Grid extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int cWidth = SudokuView.this.numbers[0][0].getWidth();
            int cHeight = SudokuView.this.numbers[0][0].getHeight();
            int height = this.getHeight();
            int width = this.getWidth();
            int sq = (int) Math.sqrt(SudokuView.this.dimension);
            final Stroke stroke = g2.getStroke();
            g2.setStroke(new BasicStroke(5));
            for (int i = 1; i < sq; i++) {
                g.drawLine(cWidth*sq*i, 0, cWidth*sq*i, height);
                g.drawLine(0, cHeight*sq*i, width, cHeight*sq*i);
            }
            g.drawRect(0,0,width-1,height-2);
            g2.setStroke(stroke);
        }
    }
}
