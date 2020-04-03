package ru.arhiser.primenums.visual;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GraphMain {

    static boolean[] state;
    static int position = 2;
    static EratosfenComponent eratosfenComponent;

    public static void main(String[] args) {
        state = new boolean[1000];
        Arrays.fill(state, true);

        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout borderLayout = new BorderLayout();
        eratosfenComponent = new EratosfenComponent();
        eratosfenComponent.setState(state, position);
        Button step = new Button("Дальше");


        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttons.add(step);

        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(eratosfenComponent, BorderLayout.CENTER);
        frame.getContentPane().add(buttons, BorderLayout.PAGE_END);

        step.addActionListener(e -> step());

        frame.setVisible(true);
    }

    public static void step() {
        if (position < state.length) {
            if (state[position]) {
                for (int j = 2 * position; j < state.length; j += position) {
                    state[j] = false;
                }
            }
            position++;
        }
        eratosfenComponent.setState(state, position);
        eratosfenComponent.repaint();
    }

}
