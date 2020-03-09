package com.mofasa.methods;

public class Jacobi extends Base {
    public Jacobi(int numberOfVariables, int[][] coefficients) {
        super(numberOfVariables, coefficients);
    }

    private float[] old = new float[numberOfVariables];

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
                        if (i != j) tmp -= old[j] * coefficients[i][j];
                    }
                    tmp += coefficients[i][numberOfVariables];
                    holder[i] = (float) (tmp / coefficients[i][i]);
                    s.append(old[i]).append(" ");
                }
                fCPrint(k + " " + s + "\n");
                old = holder.clone();
            }
        }
        /*else{

        }*/
    }
}
