package ru.arhiser.tree2.graph;

import ru.arhiser.tree2.graph.model.GraphModel;
import ru.arhiser.tree2.graph.view.FieldComponent;

import javax.swing.*;
import java.awt.*;

public class GraphMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphModel graphModel = new GraphModel();

        BorderLayout borderLayout = new BorderLayout();
        FieldComponent field = new FieldComponent(graphModel);
        Button buttonStartWide = new Button("Поиск в ширину");
        Button buttonStartDeep = new Button("Поиск в глубину");
        Button buttonReset = new Button("Сброс");
        Button buttonStepWide = new Button("Шаг в ширину");
        Button buttonStepDeep = new Button("Шаг в глубину");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttons.add(buttonStartWide);
        buttons.add(buttonStartDeep);
        buttons.add(buttonStepWide);
        buttons.add(buttonStepDeep);
        buttons.add(buttonReset);

        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(field, BorderLayout.CENTER);
        frame.getContentPane().add(buttons, BorderLayout.PAGE_END);

        buttonReset.addActionListener(e -> field.clear());
        buttonStartWide.addActionListener(e -> field.startWide());
        buttonStartDeep.addActionListener(e -> field.startDeep());
        buttonStepWide.addActionListener(e -> field.stepWide());
        buttonStepDeep.addActionListener(e -> field.stepDeep());

        frame.setVisible(true);
        field.clear();
    }

}
