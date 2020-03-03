package com.mofasa.ui;

import com.mofasa.Main;
import com.mofasa.methods.GaussSeidel;
import com.mofasa.methods.Jacobi;
import com.mofasa.methods.SOR;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainFrame extends JFrame {
    JPanel grid, control;
    JLabel number;
    JSlider numberSelector;
    JCheckBox jacobi, gauss, sor;
    JButton solve;

    public MainFrame(FileWriter fw) {
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

        number = new JLabel();

        change(2);

        numberSelector = new JSlider(2, 10, 2);
        numberSelector.addChangeListener(changeEvent -> change(numberSelector.getValue()));

        jacobi = new JCheckBox("Jacobi");
        gauss = new JCheckBox("Gauss-Seidel");
        sor = new JCheckBox("SOR");

        solve = new JButton("solve");
        solve.addActionListener(actionEvent -> solveIt(fw));

        control.add(Box.createVerticalGlue());
        control.add(number);
        control.add(numberSelector);
        control.add(Box.createVerticalGlue());
        control.add(jacobi);
        control.add(gauss);
        control.add(sor);
        control.add(Box.createVerticalGlue());
        control.add(solve);

        main.add(grid);
        main.add(control);

        getContentPane().add(main);
        pack();
    }

    private void solveIt(FileWriter fw) {
        int n = Integer.parseInt(number.getText());
        int[][] arr = new int[n][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                arr[i - 1][j] = Integer.parseInt(((JTextField) grid.getComponent(i * (n + 1) + j)).getText());
            }
        }
        if (jacobi.isSelected()) new Jacobi(n, arr).solve(fw);
        if (gauss.isSelected()) new GaussSeidel(n, arr).solve(fw);
        if (sor.isSelected()) new SOR(n, arr).solve(fw);

        openExcel();
    }

    private void change(int value) {
        number.setText(Integer.toString(value));
        fillGrid(value);
        pack();
    }

    private void fillGrid(int n) {
        grid.setLayout(new GridLayout(n + 1, n + 1, 10, 10));
        grid.removeAll();
        for (int i = 1; i <= n; i++) grid.add(new JLabel("x" + i, SwingConstants.CENTER));
        grid.add(new JLabel("= C", SwingConstants.CENTER));
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                grid.add(new JTextField(5));
            }
        }
    }
    private void openExcel(){
        File file = new File(Main.FILE_NAME);
        if (!Desktop.isDesktopSupported()) {
            System.out.println("not supported to open files");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
