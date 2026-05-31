
package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodMenuManager foodMenuManager = new FoodMenuManager();
        OrderManager orderManager = new OrderManager(); 
     
        Menu menu = new Menu(foodMenuManager, orderManager);

       
        foodMenuManager.loadFromFile("foodmenu.txt");
        File orderfile=new File("orders.txt");
        try{ 
               if(!(orderfile.exists()))
               {
                   orderfile.createNewFile();
                   System.out.println("order file created");
               }
        }
               catch(IOException e)
                       {
                       System.out.println("File not created");
                       }
                try {
            Scanner fileScanner = new Scanner(orderfile);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

          
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String username = parts[1].trim();
                

                
                Customer customer = new Customer(username);
                

                
                Order order = Order.fromFileString(line, customer, foodMenuManager);
                if (order != null) {
                    orderManager.addOrder(order);
                    System.out.println("Loaded order: " + order);
                } else {
                    System.out.println("Failed to load order for: " + username);
                }
            }
        }
    } 
        catch (FileNotFoundException e) {
        System.out.println("Orders file not found!");
    }

        

        while (true) {
            System.out.println("\nWelcome to Food Ordering System");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Registration");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                Admin admin = new Admin();
                if (admin.login()) {
                    menu.show(admin);
                    foodMenuManager.saveToFile("foodmenu.txt");
                }
            } else if (choice.equals("2")) {
                Customer customer = new Customer();
                if (customer.login()) {
                    menu.show(customer);
                }
            } else if (choice.equals("3")) {
                System.out.print("Enter new username: ");
                String newUser = scanner.nextLine();
                System.out.print("Enter new password: ");
                String newPass = scanner.nextLine();

                Customer newCustomer = new Customer();
                newCustomer.register(newUser, newPass);
            } else if (choice.equals("4")) {
                System.out.println("Exiting system. Goodbye!");
                try (Formatter formatter = new Formatter("orders.txt")) {
                for (Order order : orderManager.getOrders()){
                    formatter.format("%s\n", order.toFileString());
                }
                System.out.println("Orders saved to file.");
            } catch (FileNotFoundException e) {
                System.out.println("Error saving orders.");
            }
                
                        
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();

    }
}
