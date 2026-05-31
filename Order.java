
package project;
import java.util.ArrayList;
public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<FoodItem> items;
    private double total;
    private String status;

    public Order(int orderId, Customer customer, ArrayList<FoodItem> items, double total, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.total = total;
        this.status = status;
    }
    public void setStatus(String status) {
    this.status = status;
      }

   
    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void addFoodItem(FoodItem item) {
        items.add(item);
        total = calculateTotal();
    }

    private double calculateTotal() {
        double total = 0;
        for (FoodItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

   
    public String toFileString() {
        String itemNames = "";
        for (int i = 0; i < items.size(); i++) {
            itemNames = itemNames + items.get(i).getName();
            if (i != items.size() - 1) {
                itemNames = itemNames + "|";
            }
        }
        return orderId + "," + customer.getUsername() + "," + itemNames + "," + total + "," + status;
    }
    public static Order fromFileString(String line, Customer customer, FoodMenuManager menu) {
        String[] parts = line.split(",");
        if (parts.length < 5) {
            return null;
        }

        try {
            int orderId = Integer.parseInt(parts[0]);
            String username = parts[1];
            String[] itemNames = parts[2].split("\\|");
            double total = Double.parseDouble(parts[3]);
            String status = parts[4];

            ArrayList<FoodItem> items = new ArrayList<>();
            for (String itemName : itemNames) {
                FoodItem item = null;
                for (FoodItem f : menu.getAllFoodItems()) {
                    if (f.getName().equalsIgnoreCase(itemName)) {
                        item = f;
                        break;
                    }
                }
                if (item != null) {
                    items.add(item);
                }
            }

            if (!customer.getUsername().equals(username)) {
                return null;
            }

            return new Order(orderId, customer, items, total, status);

        } catch (NumberFormatException e) {
            return null;
        }
    }

    
    
    
    public void display() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getUsername());
        System.out.println("Items:");
        for (FoodItem item : items) {
            System.out.println(" - " + item.getName() + " ($" + item.getPrice() + ")");
        }
        System.out.println("Total: $" + total);
        System.out.println("Status: " + status);
        System.out.println("---------------------------");
    }
}
