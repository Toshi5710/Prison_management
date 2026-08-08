package com.example.prison_management.utils;

import java.io.*;
import java.util.ArrayList;

public class BinaryFileUtil {

    // Helper class to handle appending objects to an existing file without corrupting headers
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // Do not write a new stream header when appending
            reset();
        }
    }

    // --- METHOD 1: READ ALL OBJECTS FROM FILE ---
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> readAllObjects(String fileName) {
        ArrayList<T> list = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return list; // Return empty list if file doesn't exist yet
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    T obj = (T) ois.readObject();
                    list.add(obj);
                } catch (EOFException e) {
                    break; // Reached end of binary file
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading binary file " + fileName + ": " + e.getMessage());
        }

        return list;
    }

    // --- METHOD 2: APPEND A SINGLE OBJECT TO FILE ---
    public static <T> boolean appendObject(String fileName, T object) {
        File file = new File(fileName);
        boolean append = file.exists() && file.length() > 0;

        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            ObjectOutputStream oos = append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos);

            oos.writeObject(object);
            oos.close();
            fos.close();
            return true;

        } catch (IOException e) {
            System.err.println("Error writing to binary file " + fileName + ": " + e.getMessage());
            return false;
        }
    }
}