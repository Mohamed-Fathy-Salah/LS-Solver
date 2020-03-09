package com.mofasa.methods;

public class GaussSeidel extends Base {
    public GaussSeidel(int numberOfVariables, int[][] coefficients) {
        super(numberOfVariables, coefficients);
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
                        if (i != j) tmp -= holder[j] * coefficients[i][j];
                    }
                    tmp += coefficients[i][numberOfVariables];
                    holder[i] = (float) (tmp / coefficients[i][i]);
                    s.append(holder[i]).append(" ");
                }
                fCPrint(k + " " + s + "\n");
            }
        }
        /*else {

        }*/
    }
}
