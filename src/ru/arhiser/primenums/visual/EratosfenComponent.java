package ru.arhiser.primenums.visual;

import javax.swing.*;
import java.awt.*;

public class EratosfenComponent extends JComponent {

    private boolean[] state;
    private int position;

    public void setState(boolean[] state, int position) {
        this.state = state;
        this.position = position;
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(18f));

        int size = 25;

        if (state != null) {
            int rows = state.length / size;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < size && (i * size + j) < state.length; j++) {
                    int index = i * size + j;
                    if (index == position) {
                        g.setColor(Color.GREEN);
                    } else if (state[index]) {
                        g.setColor(Color.BLACK);
                    } else {
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    g.drawString(Integer.toString(index),  20 + j * 50, 50 + i * 30);
                }
            }
        }
    }
}
