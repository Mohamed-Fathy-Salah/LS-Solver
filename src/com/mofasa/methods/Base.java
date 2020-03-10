package com.mofasa.methods;

import com.mofasa.FileHandler;

abstract public class Base {
    protected int numberOfVariables;
    protected int[][] coefficients;
    protected float[] init;
    //protected boolean[] done;
    //protected int decimalError;

    public Base(int numberOfVariables, int[][] coefficients,float[] init) {
        this.numberOfVariables = numberOfVariables;
        this.coefficients = coefficients;
        this.init = init;
    }
    private void sort(){
        //TODO: make the coef array diagonally dominant
    }
    protected void fCPrint(String s){
        System.out.print(s);
        FileHandler.getInstance().write(s);
    }
    private void printTableHeaders(){
        fCPrint("n ");
        for (int i=1;i<=numberOfVariables;i++)
            fCPrint("x"+i+" ");
        fCPrint("\n");
    }
    protected boolean hasNumberOfIteration(int numberOfIterations){return numberOfIterations>0;}

    public void solve(int numberOfIterations){
        fCPrint("\n"+this.getClass().getSimpleName()+"_method\n");
        printTableHeaders();
        sort();
    }
}
