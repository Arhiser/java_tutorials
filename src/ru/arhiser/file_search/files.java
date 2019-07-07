package ru.arhiser.file_search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class files {

    public static void main(String[] args) {
        ArrayList<File> fileList = new ArrayList<>();
        getFiles(new File("D:\\"), fileList);
        for(File file: fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static void getFiles(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            System.out.println("searching: " + rootFile.getAbsolutePath());
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file: directoryFiles) {
                    if (file.isDirectory()) {
                        getFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".jpg")) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }
}
