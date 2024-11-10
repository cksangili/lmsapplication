package com.lms.app.util;

import com.lms.app.entity.User;

import java.io.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.HashMap;
import com.lms.app.entity.User;

public class LMSUtility {
    private final String ROLE_FILE_NAME = "role.dat";
    public String getRole() {
        User user = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ROLE_FILE_NAME))) {
            user = (User) in.readObject();
            //System.out.println("Users data loaded. users "+users);
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, initialize an empty HashMap
            System.out.println("No previous data found, starting with an empty database.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

         //if (user == null) {
            return user.getRole();
        //}
    }

    // Method to save the users HashMap to a file
    public void saveUsers(User users) {
        //System.out.println(" saveUsers "+users);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ROLE_FILE_NAME))) {
            out.writeObject(users);
            System.out.println("Users data saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String capFirstLetter(String str) {
        // Check if the string is not null and not empty
        if (str == null || str.isEmpty()) {
            return str;  // Return the original string if it's null or empty
        }

        // Capitalize the first letter and append the rest of the string
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
