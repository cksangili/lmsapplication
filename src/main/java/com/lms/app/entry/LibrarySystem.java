package com.lms.app.entry;

import com.lms.app.entity.User;
import com.lms.app.util.LMSUtility;

import java.io.*;
import java.util.HashMap;

// LibrarySystem Class
public class LibrarySystem {
    private HashMap<String, User> users;
    private final String FILE_NAME = "users.dat";
    private LMSUtility lmsUtility=new LMSUtility();
    String lStrOut="";
    public LibrarySystem() {
        users = new HashMap<>();
    }

    // Method to save the users HashMap to a file
    public void saveUsers(HashMap<String, User> users) {
        //System.out.println(" saveUsers "+users);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(users);
            //System.out.println("Users data saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to load the users HashMap from a file
    public HashMap<String, User> loadUsers(HashMap<String, User> users) {
        //System.out.println(" loadUsers "+users);
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (HashMap<String, User>) in.readObject();
            //System.out.println("Users data loaded. users "+users);
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, initialize an empty HashMap
            System.out.println("No previous data found, starting with an empty database.");
            users = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }

    public String registerUser(String role,String username, String password) {
        //System.out.println(" registerUser1 "+users+" username "+username);
        //System.out.println(" HashMap1 "+users);

        HashMap<String, User> tempUsers = new HashMap<>();
        //	if(null!=users && users.size()>0)
        tempUsers = loadUsers(users);
        //System.out.println(" HashMap2 "+tempUsers);
        //System.out.println(" registerUser2 username "+username+" - "+tempUsers.containsKey(username));
        if (tempUsers.containsKey(username)) {
            lStrOut = "Username already exists. Please try a different username.";
            //  System.out.println(lStrOut);
        } else {
            //System.out.println(" Else Block "+username+" - "+users.containsKey(username));
            users.put(username, new User(role,username, password));
            saveUsers(users);
            lStrOut = lmsUtility.capFirstLetter(role)+" "+username+" successfully registered.";
            //System.out.println(lStrOut);

            return lStrOut;
        }
        return lStrOut;
    }
    public String loginUser(String username, String password) {
        //if(null!=users && users.size()>0)
        users = loadUsers(users);
        //System.out.println(" loginUser  users "+users);
        User user = users.get(username);
        //System.out.println(" loginUser "+username+" "+password+" "+users);
        if(user != null && user.validatePassword(password)) {
            //userMenu();
            // Admin Alice successfully logged in.
            lmsUtility.saveUsers(user);
            lStrOut = lmsUtility.capFirstLetter(user.getRole())+" "+username+ " successfully logged in.";
            //System.out.println(lStrOut);
            return lStrOut;
        } else {
            lStrOut = "Invalid user credentials.";
            System.out.println("Invalid user credentials.");
            return lStrOut;
        }

        //return lStrOut;
    }


}
