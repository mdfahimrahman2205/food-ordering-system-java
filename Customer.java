
package project;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class Customer implements User{
     private String username;
    private String password;
    private Scanner scanner = new Scanner(System.in);
    private static final String FILENAME = "users.txt";

    public Customer() {}

    public Customer(String username) {
        this.username = username;
    }
    

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean login() {
        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

     
        if (!(new File(FILENAME)).exists()) {
            System.out.println("No user data found - please register first.");
            return false;
        }

        if (FileHandler.checkCustomerData(FILENAME, inputUsername, inputPassword)) {
            System.out.println("Login successful");
            this.username = inputUsername;
            this.password = inputPassword;
            return true;
        } else {
            System.out.println("Invalid username or password.\n");
            return false;
        }
    }

    public void register(String newUsername, String newPassword) {
      
        ArrayList<String> users = new ArrayList<>(FileHandler.readFile(FILENAME));

        for (String userLine : users) {
            String[] parts = userLine.split(",");
            if (parts.length >= 1 && parts[0].equals(newUsername)) {
                System.out.println("Username already exists! Please choose another one.");
                return;
            }
        }

        users.add(newUsername + "," + newPassword);

        FileHandler.writeFile(FILENAME, users);

        System.out.println("Registration successful!\n");
        this.username = newUsername;
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + '}';
    }


}
