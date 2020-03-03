package com.mofasa.methods;

import java.io.FileWriter;
import java.io.IOException;

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
        //TODO: get both from user
        holder = new double[numberOfVariables];
        numberOfIterations = 10;
    }
    private void sort(){
        //TODO: make the coef array diagonally dominant
    }
    protected void fCPrint(String s,FileWriter fw){
        System.out.print(s);
        //TODO: write in file
        try {
            fw.append(s);
            fw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void printTableHeaders(FileWriter fw){
        fCPrint("n ",fw);
        for (int i=1;i<=numberOfVariables;i++)
            fCPrint("x"+i+" ",fw);
        fCPrint("\n",fw);
    }
    public void solve(FileWriter fw){
        printTableHeaders(fw);
    };
}
