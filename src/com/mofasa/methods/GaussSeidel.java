package com.mofasa.methods;

public class GaussSeidel extends Base {
    public GaussSeidel(int numberOfVariables, int[][] coefficients,float[] init) {
        super(numberOfVariables, coefficients,init);
    }

    @Override
    public void solve(int numberOfIterations) {
        super.solve(numberOfIterations);
        double tmp;
        if (hasNumberOfIteration(numberOfIterations)) {
            for (int k = 0; k <= numberOfIterations; k++) {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < numberOfVariables; ++i) {
                    tmp = 0;
                    for (int j = 0; j < numberOfVariables; j++) {
                        if (i != j) tmp -= init[j] * coefficients[i][j];
                    }
                    tmp += coefficients[i][numberOfVariables];
                    init[i] = (float) (tmp / coefficients[i][i]);
                    s.append(init[i]).append(" ");
                }
                fCPrint(k + " " + s + "\n");
            }
        }
        /*else {

        }*/
    }
}
