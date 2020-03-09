package com.mofasa;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private static final String FILE_NAME = "output.xlsx";
    private static volatile FileHandler instance;
    private FileWriter fw;
    private FileHandler() {
        try {
            fw=new FileWriter(FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileHandler getInstance() {
        FileHandler result = instance;
        if (result == null) {
            synchronized (FileHandler.class) {
                result = instance;
                if (result == null)
                    instance = result = new FileHandler();
            }
        }
        return result;
    }
    public void write(String s){
        try {
            fw.append(s);
            fw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void openExcell(){
        File file = new File(FILE_NAME);
        if (!Desktop.isDesktopSupported()) {
            System.out.println("not supported to open files");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
