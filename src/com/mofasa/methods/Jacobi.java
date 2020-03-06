package com.mofasa.methods;

import java.io.FileWriter;

public class Jacobi extends Base {
    public Jacobi(int numberOfVariables, int[][] coefficients) {
        super(numberOfVariables, coefficients);
    }

    private float[] old = new float[numberOfVariables];

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
                        if (i != j) tmp -= old[j] * coefficients[i][j];
                    }
                    tmp += coefficients[i][numberOfVariables];
                    holder[i] = (float) (tmp / coefficients[i][i]);
                    s.append(old[i]).append(" ");
                }
                fCPrint(k + " " + s + "\n", fw);
                old = holder.clone();
            }
        }
        /*else{

        }*/
    }
}
