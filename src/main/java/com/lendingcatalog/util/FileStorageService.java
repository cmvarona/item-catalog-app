package com.lendingcatalog.util;

import com.lendingcatalog.util.exception.FileStorageException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileStorageService {

    // Requirement: File I/O
    public static void writeContentsToFile(String contents, String filename, boolean appendFile) throws FileStorageException {
        File file = new File(filename);
        if (appendFile) {
            try(PrintWriter dataOutput = new PrintWriter(new FileOutputStream(file, true))) {
                dataOutput.println(contents);
            }
            catch(FileNotFoundException e) {
                System.out.println("File path not found.");
            }
        }
        else {
            try (PrintWriter dataOutput = new PrintWriter(file)) {
                dataOutput.println(contents);
            }
            catch (FileNotFoundException e) {
                System.out.println("File path not found.");
            }
        }
    }

    public static List<String> readContentsOfFile(String filename) throws FileStorageException {
        File file = new File(filename);
        List<String> contents = new ArrayList<>();
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String currentLine = fileReader.nextLine();
                contents.add(currentLine);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File path not found.");
        }
        return contents;
    }
}
