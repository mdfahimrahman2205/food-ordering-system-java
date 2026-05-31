
package project;
import java.util.ArrayList;
import java.util.Scanner;
public class Menu {
       private Scanner scanner = new Scanner(System.in);
    private FoodMenuManager foodMenuManager;
    private OrderManager orderManager;

    public Menu(FoodMenuManager foodMenuManager, OrderManager orderManager) {
        this.foodMenuManager = foodMenuManager;
        this.orderManager = orderManager;
    }

    public void show(User user) {
        if (user instanceof Customer) {
            showCustomerMenu((Customer) user);
        } else if (user instanceof Admin) {
            showAdminMenu((Admin) user);
        } else {
            System.out.println("Unknown user type.");
        }
    }

    private void showCustomerMenu(Customer customer) {
        int choice;
        do {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Food Items");
            System.out.println("2. Place Order");
            System.out.println("3. View My Orders");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    foodMenuManager.viewAllFoodItems();
                    break;
                case 2:
                    ArrayList<FoodItem> selectedItems = selectFoodItems();
                    if (!selectedItems.isEmpty()) {
                        orderManager.placeOrder(customer, selectedItems);
                    } else {
                        System.out.println("No items selected.");
                    }
                    break;
                case 3:
                    orderManager.viewOrdersByCustomer(customer);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private void showAdminMenu(Admin admin) {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Food Item");
            System.out.println("2. Remove Food Item");
            System.out.println("3. View All Orders");
            System.out.println("4. Confirm an Order");
            System.out.println("5.Logout");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
            case 1:
                foodMenuManager.addFoodItem();
                break;
            case 2:
                foodMenuManager.removeFoodItem();
                break;
            case 3:
                orderManager.viewAllOrders();
                break;
            case 4:
                orderManager.viewAllOrders();
                System.out.print("Enter Order ID to update: ");
                int orderId = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter new status (e.g., Confirmed, Cancelled): ");
                String newStatus = scanner.nextLine();

                orderManager.updateOrderStatus(orderId, newStatus);
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    } while (choice != 5);
}

    private ArrayList<FoodItem> selectFoodItems() {
        ArrayList<FoodItem> selectedItems = new ArrayList<>();
        foodMenuManager.viewAllFoodItems();
        System.out.println("Enter food item names to order (type 'done' to finish):");

        while (true) {
            System.out.print("Food name: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

           FoodItem item = null;
         for (FoodItem f : foodMenuManager.getAllFoodItems()) {
            if (f.getName().equalsIgnoreCase(input)) {
          item = f;
          break;
         }
        }
 
            if (item != null) {
                selectedItems.add(item);
                System.out.println(item.getName() + " added.");
            } else {
                System.out.println("Item not found.");
            }
        }
        return selectedItems;
    }


}
