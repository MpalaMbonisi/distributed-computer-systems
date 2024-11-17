package com.github.mpalambonisi.lab06.q3;
import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HelloClient {
    public static void main(String[] args) {
        try {
            // Connect to the UserDatabase RMI service
            UserDatabaseInterface ui = (UserDatabaseInterface) Naming.lookup("//localhost:5001/UserDatabase");

            // Initialize Scanner for input
            Scanner scanner = new Scanner(System.in);
            int choice;

            while (true) {
                // Display menu options
                System.out.println("\nUser Database Operations:");
                System.out.println("1. Add User");
                System.out.println("2. Delete User");
                System.out.println("3. Retrieve User Details");
                System.out.println("4. List All Users");
                System.out.println("5. Modify User");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Add User
                        System.out.print("Enter first name: ");
                        String firstName = scanner.next();
                        System.out.print("Enter last name: ");
                        String lastName = scanner.next();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
                        System.out.print("Enter date [yyyy-mm-dd] : ");
                        Date dob = formatter.parse(scanner.next());
                        System.out.print("Enter salary: ");
                        double salary = scanner.nextDouble();
                        System.out.print("Enter division: ");
                        String division = scanner.next();
                        System.out.print("Enter work position: ");
                        String workPosition = scanner.next();
                        System.out.print("Enter gender (MALE, FEMALE, OTHER): ");
                        String genderStr = scanner.next().toUpperCase();
                        User.Gender gender = User.Gender.valueOf(genderStr);

                        // Create and add the user
                        User newUser = new User(firstName, lastName, dob , salary, gender, division, workPosition);
                        ui.addUser(newUser);
                        System.out.println("User added successfully!");
                        break;

                    case 2:
                        // Delete User
                        System.out.print("Enter first name of user to delete: ");
                        String deleteFirstName = scanner.next();
                        System.out.print("Enter last name of user to delete: ");
                        String deleteLastName = scanner.next();
                        ui.deleteUser(deleteFirstName, deleteLastName);
                        System.out.println("User deleted successfully!");
                        break;

                    case 3:
                        // Retrieve User Details
                        System.out.print("Enter first name of user to retrieve: ");
                        String retrieveFirstName = scanner.next();
                        System.out.print("Enter last name of user to retrieve: ");
                        String retrieveLastName = scanner.next();
                        User retrievedUser = ui.getUserDetails(retrieveFirstName, retrieveLastName);
                        if (retrievedUser != null) {
                            System.out.println("User Details: " + retrievedUser);
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 4:
                        // List All Users
                        List<User> users = ui.listUsers();
                        if (users.isEmpty()) {
                            System.out.println("No users in the database.");
                        } else {
                            System.out.println("User List:");
                            for (User user : users) {
                                System.out.println(user);
                            }
                        }
                        break;

                    case 5:
                        // Modify User
                        System.out.print("Enter first name of user to modify: ");
                        String modifyFirstName = scanner.next();
                        System.out.print("Enter last name of user to modify: ");
                        String modifyLastName = scanner.next();

                        // Input updated user details
                        System.out.print("Enter new first name: ");
                        String newFirstName = scanner.next();
                        System.out.print("Enter new last name: ");
                        String newLastName = scanner.next();
                        System.out.print("Enter new salary: ");
                        double newSalary = scanner.nextDouble();
                        System.out.print("Enter new division: ");
                        String newDivision = scanner.next();
                        System.out.print("Enter new work position: ");
                        String newWorkPosition = scanner.next();
                        System.out.print("Enter new gender (MALE, FEMALE, OTHER): ");
                        String newGenderStr = scanner.next().toUpperCase();
                        User.Gender newGender = User.Gender.valueOf(newGenderStr);

                        // Update the user
                        User updatedUser = new User(newFirstName, newLastName, new Date(), newSalary, newGender, newDivision, newWorkPosition);
                        ui.modifyUser(modifyFirstName, modifyLastName, updatedUser);
                        System.out.println("User modified successfully!");
                        break;

                    case 6:
                        // Exit
                        System.out.println("Exiting the client.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

