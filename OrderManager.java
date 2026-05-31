
package project;
import java.util.ArrayList;
public class OrderManager {
   private ArrayList<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
       
        
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added successfully.");
    }
 
    public boolean updateOrderStatus(int orderId, String newStatus) {
    for (Order order : orders) {
        if (order.getOrderId() == orderId) {
            order.setStatus(newStatus);
            System.out.println("Order status updated.");
            return true;
        }
    }
    System.out.println("Order with ID " + orderId + " not found.");
    return false;
}



    public void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("\nAll Orders:");
        for (Order order : orders) {
            order.display();
        }
    }
    public void placeOrder(Customer customer, ArrayList<FoodItem> selectedItems) {
    int newOrderId = orders.size() + 1;  
    double total = 0;

    for (FoodItem item : selectedItems) {
        total += item.getPrice();
    }

    Order newOrder = new Order(newOrderId, customer, selectedItems, total, "Pending");
    orders.add(newOrder);
    System.out.println("Order placed successfully.");
}
    
    public void viewOrdersByCustomer(Customer customer) {
    boolean found = false;
    for (Order order : orders) {
        if (order.getCustomer().getUsername().equals(customer.getUsername())) {
            order.display();
            found = true;
        }
    }
    if (!found) {
        System.out.println("No orders found for this customer.");
    }
}


    public void saveToFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        for (Order order : orders) {
            lines.add(order.toFileString());
        }
        FileHandler.writeFile(filename, lines);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void loadFromFile(String filename, Customer customer, FoodMenuManager menu) {
        ArrayList<String> lines = (ArrayList<String>) FileHandler.readFile(filename);
        for (String line : lines) {
            Order order = Order.fromFileString(line, customer, menu);
            if (order != null) {
                orders.add(order);
            }
        }
    }
}
