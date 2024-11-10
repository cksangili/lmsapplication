package com.lms.app.entity;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

// Book Class
public class Book implements Serializable {
    private String title;
    private String author;
    private boolean isAvailable;
    private int inventory;

    public Book(String title, String author,int inventory) {
        this.title = title;
        this.author = author;
        this.inventory = inventory;
        this.isAvailable = true;
    }

    public int getInventory() {
        return inventory;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        isAvailable = false;
        // System.out.println("Before You have borrowed: " + title+" Inventory "+inventory);
        //  inventory--;
        // System.out.println("You have borrowed: " + title+" Inventory "+inventory);
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println("You have returned: " + title+" Inventory "+inventory);
    }

    // Override equals() method
    @Override
    public boolean equals(Object obj) {
        // Check if the objects are the same instance
        if (this == obj) return true;

        // Check if the object is null or not of the same class
        if (obj == null || getClass() != obj.getClass()) return false;

        // Cast the object to a Book
        Book book = (Book) obj;

        // Compare the titles
        return Objects.equals(title, book.title);
    }

    // Override hashCode() method
    @Override
    public int hashCode() {
        // Generate hash code based only on the title field
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Inventory "+inventory+", Available: " + (isAvailable ? "Yes" : "No");
    }

    public void updateInventory(int inventory) {
        this.inventory = inventory;
    }
}