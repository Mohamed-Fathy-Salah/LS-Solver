package com.mofasa.methods;

import java.io.FileWriter;

public class GaussSeidel extends Base {
    public GaussSeidel(int numberOfVariables, int[][] coefficients) {
        super(numberOfVariables, coefficients);
    }

    @Override
    public void solve(FileWriter fw) {
        super.solve(fw);
        float tmp;
        //if (hasNumberOfIterations){
            for(int k=0;k<=numberOfIterations;k++){
                fCPrint(k+" ",fw);
                for (int i = 0; i < numberOfVariables; ++i){
                    tmp=0;
                    for(int j=0;j<numberOfVariables;j++){
                        if(i!=j)tmp-=holder[j]*coefficients[i][j];
                    }
                    tmp+=coefficients[i][numberOfVariables];
                    holder[i]=tmp/coefficients[i][i];
                    fCPrint(holder[i]+" ",fw);
                }
                fCPrint("\n",fw);
            }
        /*}else{

        }*/
    }
}
