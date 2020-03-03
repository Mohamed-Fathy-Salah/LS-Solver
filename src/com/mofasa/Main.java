package com.mofasa;

import com.mofasa.ui.MainFrame;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static final String FILE_NAME = "output.xlsx";
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw=new FileWriter(FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new MainFrame(fw);
    }
}
