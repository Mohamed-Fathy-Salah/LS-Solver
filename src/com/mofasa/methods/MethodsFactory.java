package com.mofasa.methods;

public class MethodsFactory {
    public static final int JACOBI=0;
    public static final int GAUSS_SEIDEL=1;
    public static final int SOR=2;

    public static void solve(int type, int numOfVars, int numOfItr , int[][] coefficients,float[] init,float w){
        solveBy(type, numOfVars, coefficients,init,w).solve(numOfItr);
    }
    public static void solve(int type, int numOfVars, int numOfItr , int[][] coefficients,float[] init){
        solveBy(type, numOfVars, coefficients,init,1.25f).solve(numOfItr);
    }

    private static Base solveBy(int type, int numOfVars, int[][] coefficients, float[] init,float w){
        if (type ==JACOBI)return new Jacobi(numOfVars,coefficients,init);
        if (type == GAUSS_SEIDEL)return new GaussSeidel(numOfVars,coefficients,init);
        return new SOR(numOfVars,coefficients,init,w);
    }
}
