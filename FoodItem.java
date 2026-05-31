
package project;

public class FoodItem {
  private int id;
    private String name;
    private double price;
    private String category;

    public FoodItem(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Id: " + id);
        System.out.println("Price: " + price);
        System.out.println("Category: " + category);
    }

    
    public String toFileString() {
        return id + "," + name + "," + price + "," + category;
    }

    
    public static FoodItem fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) return null;
        try {
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            String category = parts[3];
            return new FoodItem(id, name, price, category);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
