
package project;
import java.util.Scanner;
public class Admin implements User{
private static final String ADMINUSERNAME = "admin";
    private static final String ADMINPASSWORD = "admin123";
  Scanner scanner = new Scanner(System.in);

 @Override
    public boolean login() {
      

        System.out.print("Enter admin username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Enter admin password: ");
        String inputPassword = scanner.nextLine();

        if (inputUsername.equals(ADMINUSERNAME) && inputPassword.equals(ADMINPASSWORD)) {
            System.out.println("Admin login successful!");
            
            return true;
        } else {
            System.out.println("Invalid admin information.");
            return false;
        }
    }


}
