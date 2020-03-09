package com.mofasa.ui;

import com.mofasa.FileHandler;
import com.mofasa.methods.MethodsFactory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel grid, control;
    Slider numberSelector, wSelector, iterationNumber;
    JCheckBox jacobi, gauss, sor;
    JButton solve;

    public MainFrame() {
        super("LS solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);//center the frame

        JPanel main = new JPanel();
        main.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));

        grid = new JPanel();
        grid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        control = new JPanel();
        control.setLayout(new BoxLayout(control, BoxLayout.Y_AXIS));

        fillGrid(2);

        numberSelector = new Slider(2, 10, 2, changeEvent -> fillGrid(numberSelector.getValue()), "variables = ");

        wSelector = new Slider(0, 200, 1, null, "w = 10^(-2) * ");
        wSelector.setVisible(false);

        iterationNumber = new Slider(0, 40, 10, null, "iterations = ");

        jacobi = new JCheckBox("Jacobi");
        gauss = new JCheckBox("Gauss-Seidel");
        sor = new JCheckBox("SOR");
        sor.addItemListener(itemEvent -> {
            wSelector.setVisible(sor.isSelected());
            pack();
        });

        JPanel tmp = new JPanel();
        tmp.setLayout(new BoxLayout(tmp, BoxLayout.X_AXIS));

        tmp.add(sor);
        tmp.add(Box.createHorizontalGlue());
        tmp.add(wSelector);

        solve = new JButton("solve");
        solve.addActionListener(actionEvent -> solveIt());

        control.add(Box.createVerticalGlue());
        control.add(numberSelector);
        control.add(Box.createVerticalGlue());
        control.add(leftJustify(jacobi));
        control.add(leftJustify(gauss));
        control.add(tmp);
        control.add(Box.createVerticalGlue());
        control.add(iterationNumber);
        control.add(Box.createVerticalGlue());
        control.add(solve);

        main.add(grid);
        main.add(control);

        getContentPane().add(main);
        setResizable(false);
        pack();
    }

    private void solveIt() {
        int n = numberSelector.getValue();
        int[][] arr = new int[n][n + 1];
        if (!(jacobi.isSelected() || gauss.isSelected() || sor.isSelected())) {
            JOptionPane.showMessageDialog(null, "choose a method to solve with");
            return;
        }
        try {
            new Thread(() -> {
                try {
                    for (int i = 1; i <= n; i++) {
                        for (int j = 0; j <= n; j++) {
                            arr[i - 1][j] = Integer.parseInt(((JTextField) grid.getComponent(i * (n + 1) + j)).getText());
                        }
                    }
                    if (jacobi.isSelected())
                        MethodsFactory.solve(MethodsFactory.JACOBI, n, iterationNumber.getValue(), arr);
                    if (gauss.isSelected())
                        MethodsFactory.solve(MethodsFactory.GAUSS_SEIDEL, n, iterationNumber.getValue(), arr);
                    if (sor.isSelected())
                        MethodsFactory.solve(MethodsFactory.SOR, n, iterationNumber.getValue(), arr, wSelector.getValue() / 100.0f);

                    FileHandler.getInstance().openExcell();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "fill all the fields with numbers only");
                }
            }).start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void fillGrid(int n) {
        grid.setLayout(new GridLayout(n + 1, n + 1, 10, 10));
        grid.removeAll();
        for (int i = 1; i <= n; i++) grid.add(new JLabel("x" + i, SwingConstants.CENTER));
        grid.add(new JLabel("= C", SwingConstants.CENTER));
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                grid.add(getTField());
            }
        }
        pack();
    }

    private JTextField getTField() {
        JTextField textField = new JTextField(5);
        //TODO: append only numbers
//        textField.getDocument().addDocumentListener();
        return textField;
    }

    private Component leftJustify(Component component) {
        Box b = Box.createHorizontalBox();
        b.add(component);
        b.add(Box.createHorizontalGlue());
        return b;
    }
}
