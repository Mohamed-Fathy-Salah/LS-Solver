package com.mofasa.methods;

import java.io.FileWriter;

public class Jacobi extends Base {
    public Jacobi(int numberOfVariables, int[][] coefficients) {
        super(numberOfVariables, coefficients);
    }

    @Override
    public void solve(FileWriter fw) {
        super.solve(fw);
    }
}
