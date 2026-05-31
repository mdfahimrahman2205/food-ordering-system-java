
package project;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FoodMenuManager {
   private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public FoodMenuManager() {
    }
    

    public void addFoodItem() {
        System.out.print("Enter food ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter food name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        FoodItem item = new FoodItem(id, name, price, category);
        foodItems.add(item);
        System.out.println("Food item added.");
    }

    public void removeFoodItem() {
        System.out.print("Enter food name to remove: ");
        String nameToRemove = scanner.nextLine();

        for (FoodItem item : foodItems) {
            if (item.getName().equalsIgnoreCase(nameToRemove)) {
                foodItems.remove(item);
                System.out.println("Food item removed.");
                return;
            }
        }

        System.out.println("Food item not found.");
    }

    public void viewAllFoodItems() {
        if (foodItems.isEmpty()) {
            System.out.println("No food items available.");
        } else {
            System.out.println("\n--- Food Menu ---");
            for (FoodItem item : foodItems) {
                item.display();
                System.out.println();
            }
        }
    }

    public ArrayList<FoodItem> getAllFoodItems() {
        return foodItems;
    }

   
    public void saveToFile(String filename) {
        List<String> lines = new ArrayList<>();
        for (FoodItem item : foodItems) {
            lines.add(item.toFileString());
        }
        FileHandler.writeFile(filename, lines);
        System.out.println("Food menu saved to file.");
    }

    
    public void loadFromFile(String filename) {
        
        List<String> lines = FileHandler.readFile(filename);
        for (String line : lines) {
            FoodItem item = FoodItem.fromFileString(line);
            if (item != null) {
                foodItems.add(item);
            }
        }
        System.out.println("Food menu loaded from file.");
    }

}
