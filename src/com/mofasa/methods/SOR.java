package com.mofasa.methods;

import java.io.FileWriter;

public class SOR extends Base {
    private float w;

    public SOR(int numberOfVariables, int[][] coefficients, float w) {
        super(numberOfVariables, coefficients);
        this.w = w;
    }

    public SOR(int numberOfVariables, int[][] coefficients) {
        this(numberOfVariables, coefficients, 1.25f);
    }

    @Override
    public void solve(FileWriter fw) {
        super.solve(fw);
        double tmp;
        if (hasNumberOfIteration()) {
            for (int k = 0; k <= numberOfIterations; k++) {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < numberOfVariables; ++i) {
                    tmp = 0;
                    for (int j = 0; j < numberOfVariables; j++) {
                        if (i != j) tmp -= holder[j] * coefficients[i][j];
                    }
                    tmp += coefficients[i][numberOfVariables];
                    holder[i] = (float) (w * tmp / coefficients[i][i] + (1 - w) * holder[i]);
                    s.append(holder[i]).append(" ");
                }
                fCPrint(k + " " + s + "\n", fw);
            }
        }
        /*else{

        }*/
    }
}
