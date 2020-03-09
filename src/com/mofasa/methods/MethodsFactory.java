package com.mofasa.methods;

public class MethodsFactory {
    public static final int JACOBI=0;
    public static final int GAUSS_SEIDEL=1;
    public static final int SOR=2;

    public static void solve(int type, int numOfVars, int numOfItr , int[][] coefficients, float w){
        solveBy(type, numOfVars, coefficients,w).solve(numOfItr);
    }
    public static void solve(int type, int numOfVars, int numOfItr , int[][] coefficients){
        solveBy(type, numOfVars, coefficients,1.25f).solve(numOfItr);
    }

    private static Base solveBy(int type, int numOfVars, int[][] coefficients, float w){
        if (type ==JACOBI)return new Jacobi(numOfVars,coefficients);
        if (type == GAUSS_SEIDEL)return new GaussSeidel(numOfVars,coefficients);
        return new SOR(numOfVars,coefficients,w);
    }
}
