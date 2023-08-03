package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class MainFile {
    public static void main(String[] args) {
        File file = new File(".\\.gitignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("C:\\project\\basejava\\src\\com\\basejava\\webapp");
        System.out.println(dir.getAbsolutePath());
        System.out.println(dir.isDirectory());
        System.out.println(Arrays.toString(dir.list()));

        String[] list = dir.list();
        assert list != null;
        for (String name : list) {
            System.out.println(name);
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            System.out.println(fileInputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printCatalogDeep(dir);
    }

    public static void printCatalogDeep(File dir) {
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("File " + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("\n" + " Directory " + "[" + file.getName().toUpperCase() + "]");
                printCatalogDeep(file);
            }
        }
    }
}