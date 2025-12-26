package com.app.service;

import com.app.exception.UserNotFoundException;
import com.app.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(User newUser) {
        for (User u : users) {
            if (u.getId() == newUser.getId()) {
                System.out.println("Error: User with ID " + newUser.getId() + " already exists!");
                return;
            }
            if (u.getEmail().equalsIgnoreCase(newUser.getEmail())) {
                System.out.println("Error: Email '" + newUser.getEmail() + "' is already registered!");
                return;
            }
        }
        users.add(newUser);
        System.out.println("User added successfully!");
    }

    public void getAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
            return;
        }
        System.out.println("Displaying all the Users");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println(
                    (i + 1) + ". ID: " + user.getId() +
                            ", Name: '" + user.getName() + '\'' +
                            ", Email: '" + user.getEmail() + '\''
            );
        }
    }


    public User getUserById(int id) throws UserNotFoundException {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException("User " + id + " not found");
    }

    public void updateUser(int id, String newName, String newEmail) throws UserNotFoundException {
        User user = getUserById(id); // Reuse search logic
        user.setName(newName);
        user.setEmail(newEmail);
        System.out.println("User updated successfully!");
    }


    public void deleteUser(int id) throws UserNotFoundException {
        User user = getUserById(id); // Ensure user exists
        users.remove(user);
        System.out.println("User deleted successfully!");
    }

    public void sortUsersByName() {
        users.sort(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Users sorted by name.");
    }

    public void sortUsersById() {
        users.sort(Comparator.comparingInt(User::getId));
        System.out.println("Users sorted by ID.");
    }

    public void exportUsersToFile(String filename) {
        if (users.isEmpty()) {
            System.out.println("No users to export.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write CSV header
            writer.println("ID,Name,Email");

            // Write each user
            for (User user : users) {
                writer.printf("%d,%s,%s%n", user.getId(), user.getName(), user.getEmail());
            }
            System.out.println("Users exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}
