import com.app.exception.UserNotFoundException;
import com.app.model.User;
import com.app.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService service = new UserService();

        while (true) {
            System.out.println("\n--- User Management System ---");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Search User");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Sort by Name");
            System.out.println("7. Sort by ID");
            System.out.println("8. Export to File");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID, Name, Email: ");
                        service.addUser(new User(sc.nextInt(), sc.next(), sc.next()));
                        break;
                    case 2:
                        service.getAllUsers();
                        break;
                    case 3:
                        System.out.print("Enter ID to search: ");
                        System.out.println(service.getUserById(sc.nextInt()));
                        break;
                    case 4:
                        System.out.print("Enter ID to update, then new Name and Email: ");
                        service.updateUser(sc.nextInt(), sc.next(), sc.next());
                        break;
                    case 5:
                        System.out.print("Enter ID to delete: ");
                        service.deleteUser(sc.nextInt());
                        break;
                    case 6:
                        service.sortUsersByName();
                        break;
                    case 7:
                        service.sortUsersById();
                        break;
                    case 8:
                        System.out.print("Enter filename (e.g., users.csv): ");
                        sc.nextLine(); // consume leftover newline
                        String filename = sc.nextLine().trim();
                        if (filename.isEmpty()) filename = "users.csv";
                        service.exportUsersToFile(filename);
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (UserNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}