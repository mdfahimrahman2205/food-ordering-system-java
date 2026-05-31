
package project;
import java.io.*;

import java.util.*;

public class FileHandler {
   public static void writeFile(String fileName, List<String> data) {
        try (Formatter writer = new Formatter(fileName)) {
            for (String line : data) {
                writer.format("%s%n", line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    
    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);
        
        if (!file.exists()) {
            System.out.println("File " + fileName + " not found. Creating new empty file.");
            try {
                file.createNewFile(); 
            } catch (IOException e) {
                System.out.println("Error while creating the file: " + e.getMessage());
            }
            return lines;
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return lines;
    }

public static boolean usernameExists(String filename, String username) {
    File file = new File(filename);
    if (!file.exists()) {
        return false;
    }

    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].trim().equals(username)) {
                return true;
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    return false;
}

public static boolean checkCustomerData(String filename, String username, String password) {
    File file = new File(filename);
    if (!file.exists()) {
        return false;
    }

    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].trim().equals(username) && parts[1].trim().equals(password)) {
                return true;
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    return false;
}
}
