package com.lms.app.entry;

import com.lms.app.entity.Book;
import com.lms.app.util.LMSUtility;

import java.io.*;
import java.util.HashMap;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        //System.out.println("Hello World");
        LibrarySystem librarySystem = new LibrarySystem();
        SystemLibrary library = new SystemLibrary();
        LMSUtility lmsUtility = new LMSUtility();
        String lStrResult = "";
        if (args.length > 0) {
            String command = args[0];
            switch (command.toLowerCase()) {
                case "register":
                    if (args.length == 4) {
                        System.out.println(" LMS Register ");
                        lStrResult = librarySystem.registerUser(args[1],args[2], args[3]);
                        System.out.println(lStrResult);

                    } else {
                        System.out.println("Username already exists. Please try a different username.");
                    }
                    break;
                case "login":
                    if (args.length == 3) {
                        System.out.println(" LMS Login ");
                        lStrResult = librarySystem.loginUser(args[1], args[2]);
                        System.out.println(lStrResult);
                    } else {
                        System.out.println("Invalid user credentials.");
                    }
                    break;
                case "add":
                    if (args.length == 4) {
                        System.out.println(" LMS Book Add Role-> "+lmsUtility.getRole());

                        //System.out.println("Main Method(add) "+args[1]+ " "+args[2]+ " "+args[3]);
                        if(null!=lmsUtility && lmsUtility.getRole().equals("admin"))
                            library.addBook(args[1], args[2],Integer.parseInt(args[3]));
                        else
                            System.out.println("Only admin can add book.");
                    } else {
                        System.out.println("Usage: add title author inventory");
                    }
                    break;
                case "list":
                    System.out.println(" LMS Book List Role-> "+lmsUtility.getRole());
                    //System.out.println(" Main Method(list) "+args[0]);
                    if(null!=lmsUtility && lmsUtility.getRole().equals("admin"))
                        library.displayBooks();
                    else
                        System.out.println("Only admin can list book.");
                    break;
                case "search":
                    System.out.println(" LMS Search Book ");
                    //System.out.println(" Main Method(search) "+args[1]);
                    library.searchBook(args[1]);
                    break;
                case "borrow":
                    System.out.println(" LMS Borrow Book ");
                    // System.out.println(" Main Method(borrow) "+args[1]);
                    library.borrowBook(args[1]);
                    break;
                case "return":
                    System.out.println(" LMS Return Book ");
                    //System.out.println(" Main Method(return) "+args[1]);
                    library.returnBook(args[1]);
                    break;
                case "delete":
                    System.out.println(" LMS Delete Book ");
                    //System.out.println(" Main Method(delete) "+args[1]);
                    if(null!=lmsUtility && lmsUtility.getRole().equals("admin"))
                        library.deleteBook(args[1]);
                    else
                        System.out.println("Only admin can delete book.");

                    break;
                default:
                    System.out.println("Unknown command.");
                    break;
            }
        } else {
            System.out.println("No command provided.");
        }
    }
}