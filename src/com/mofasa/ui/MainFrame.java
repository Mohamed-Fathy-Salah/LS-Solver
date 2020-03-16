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
        setResizable(false);

        JPanel main = new JPanel();
        main.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));

        grid = new JPanel();
        grid.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
        control = new JPanel();
        control.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        control.setLayout(new BoxLayout(control, BoxLayout.Y_AXIS));

        fillGrid(2);

        numberSelector = new Slider(2, 10, 2, changeEvent -> fillGrid(numberSelector.getValue()), "variables = ");

        wSelector = new Slider(100, 200, 125, null, "w = 10^(-2) * ");
        wSelector.setVisible(false);

        iterationNumber = new Slider(2, 40, 10, null, "iterations = ");

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
        control.add(Box.createRigidArea(new Dimension(0,10)));
        control.add(Box.createVerticalGlue());
        control.add(justify(jacobi,false));
        control.add(justify(gauss,false));
        control.add(tmp);
        control.add(Box.createVerticalGlue());
        control.add(Box.createRigidArea(new Dimension(0,10)));
        control.add(iterationNumber);
        control.add(Box.createRigidArea(new Dimension(0,10)));
        control.add(Box.createVerticalGlue());
        control.add(justify(solve,true));

        main.add(control);
        main.add(new JSeparator(SwingConstants.VERTICAL));
        main.add(grid);

        getContentPane().add(main);
        pack();
        setLocationRelativeTo(null);
    }

    private void solveIt() {
        if (!(jacobi.isSelected() || gauss.isSelected() || sor.isSelected())) {
            JOptionPane.showMessageDialog(null, "choose a method to solve with");
            return;
        }
        try {
            new Thread(() -> {
                try {
                    int n=numberSelector.getValue();
                    int[][] coefficients = getCoefficients();
                    float[] init=getInit();
                    if (jacobi.isSelected())
                        MethodsFactory.solve(MethodsFactory.JACOBI, n, iterationNumber.getValue(), coefficients,init.clone());
                    if (gauss.isSelected())
                        MethodsFactory.solve(MethodsFactory.GAUSS_SEIDEL, n, iterationNumber.getValue(), coefficients,init.clone());
                    if (sor.isSelected())
                        MethodsFactory.solve(MethodsFactory.SOR, n, iterationNumber.getValue(), coefficients, init.clone(),wSelector.getValue() / 100.0f);

                    FileHandler.getInstance().openExcell();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "fill all the fields with numbers only");
                }
            }).start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int[][] getCoefficients()throws NumberFormatException{
        int n =numberSelector.getValue();
        int[][] arr=new int[n][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                arr[i - 1][j] = Integer.parseInt(((JTextField) grid.getComponent(i * (n + 1) + j)).getText());
            }
        }
        return arr;
    }

    private float[] getInit()throws NumberFormatException{
        int n=numberSelector.getValue();
        int hold=(n+1)*(n+1);
        float[] arr = new float[n];
        String tmp;
        for (int i=0;i<n;++i){
            tmp =((JTextField)grid.getComponent(hold+i)).getText();
            if (tmp.isEmpty())tmp="0";
            arr[i]=Float.parseFloat(tmp);
            System.out.print(arr[i]+" ");
        }
        return arr;
    }
    private void fillGrid(int n) {
        grid.setLayout(new GridLayout(n + 2, n + 1, 10, 10));
        grid.removeAll();
        for (int i = 1; i <= n; i++) grid.add(new JLabel("x" + i, SwingConstants.CENTER));
        grid.add(new JLabel("= C", SwingConstants.CENTER));
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                grid.add(getTField(false));
            }
        }
        for (int i = 1; i <= n; i++) grid.add(getTField(true));
        grid.add(new JLabel("<- init", SwingConstants.CENTER));
        pack();
        setLocationRelativeTo(null);
    }

    private JTextField getTField(boolean init) {
        JTextField textField = new JTextField(5);
        if (init){
            textField.setBackground(Color.DARK_GRAY);
            textField.setForeground(Color.WHITE);
        }
        //TODO: append only numbers
//        textField.getDocument().addDocumentListener();
        return textField;
    }

    private Component justify(Component component, boolean center) {
        Box b = Box.createHorizontalBox();
        if (center)b.add(Box.createHorizontalGlue());
        b.add(component);
        b.add(Box.createHorizontalGlue());
        return b;
    }
}
