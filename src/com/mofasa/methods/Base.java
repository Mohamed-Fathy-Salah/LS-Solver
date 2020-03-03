package com.mofasa.methods;

abstract public class Base {
    protected int numberOfVariables,numberOfIterations;
    protected int[][] coefficients;
    protected double[] holder;
    protected boolean[] done;
    //protected boolean hasNumberOfIterations;
    //TODO:set error


    public Base(int numberOfVariables, int[][] coefficients) {
        this.numberOfVariables = numberOfVariables;
        this.coefficients = coefficients;
    }
    private void sort(){
        //TODO
    }
    protected void fCPrint(String s){
        System.out.println(s);
        //TODO: write in file
    }
    public abstract void solve();
}
