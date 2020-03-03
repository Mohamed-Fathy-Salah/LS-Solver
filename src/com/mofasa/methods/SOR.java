package com.mofasa.methods;

import java.io.FileWriter;

public class SOR  extends Base{
    public SOR(int numberOfVariables, int[][] coefficients) {
        super(numberOfVariables, coefficients);
    }

    @Override
    public void solve(FileWriter fw) {
        super.solve(fw);
    }
}
