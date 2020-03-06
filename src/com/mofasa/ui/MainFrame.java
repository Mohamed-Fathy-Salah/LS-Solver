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
    Slider numberSelector,wSelector;
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

        fillGrid(2);

        numberSelector = new Slider(2, 10, 2,changeEvent -> fillGrid(numberSelector.getValue()));

        wSelector = new Slider(0,200,1,null);
        wSelector.setVisible(false);

        jacobi = new JCheckBox("Jacobi");
        gauss = new JCheckBox("Gauss-Seidel");
        sor = new JCheckBox("SOR");
        sor.addItemListener(itemEvent -> {wSelector.setVisible(sor.isSelected());pack();});

        JPanel tmp = new JPanel();
        tmp.setLayout(new BoxLayout(tmp,BoxLayout.X_AXIS));

        tmp.add(sor);
        tmp.add(Box.createHorizontalGlue());
        tmp.add(wSelector);

        solve = new JButton("solve");
        solve.addActionListener(actionEvent -> solveIt(fw));

        control.add(Box.createVerticalGlue());
        control.add(numberSelector);
        control.add(Box.createVerticalGlue());
        control.add(leftJustify(jacobi));
        control.add(leftJustify(gauss));
        control.add(tmp);
        control.add(Box.createVerticalGlue());
        control.add(solve);

        main.add(grid);
        main.add(control);

        getContentPane().add(main);
        pack();
    }

    private void solveIt(FileWriter fw) {
        //TODO: run it in background thread
        int n = numberSelector.getValue();
        int[][] arr = new int[n][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                arr[i - 1][j] = Integer.parseInt(((JTextField) grid.getComponent(i * (n + 1) + j)).getText());
            }
        }
        if (jacobi.isSelected()) new Jacobi(n, arr).solve(fw);
        if (gauss.isSelected()) new GaussSeidel(n, arr).solve(fw);
        if (sor.isSelected()) new SOR(n, arr,wSelector.getValue()/100.0f).solve(fw);

        openExcel();
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
        pack();
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
    private Component leftJustify( Component component )  {
        Box  b = Box.createHorizontalBox();
        b.add( component );
        b.add( Box.createHorizontalGlue() );
        // (Note that you could throw a lot more components
        // and struts and glue in here.)
        return b;
    }
}
