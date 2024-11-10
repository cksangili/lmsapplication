package com.lms.app.entry;

import com.lms.app.entity.Book;

import java.io.*;
import java.util.HashMap;

class SystemLibrary {
    HashMap<String, Book> books = new HashMap<>();
    private String FILE_NAME = "inventory.ser";
    private String USER_FILE_NAME = "user.ser";
    private String lStrResult = "";


    public String addBook(String title, String author, int inventory) {
        // books.put(title, new Book(title, author, inventory));
        books = checkBook(title);
        //System.out.println("addBook(1) " + books);
        if (books.containsKey(title)) {
            System.out.println(title+ " book already exists. Choose a different title.");
        } else {
            books.put(title, new Book(title, author, inventory));
            // System.out.println("addBook(2) " + books);
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                out.writeObject(books);
                lStrResult = "Book "+title+" by "+author+" added successfully, inventory: "+inventory+".";
                System.out.println(lStrResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lStrResult;
        }
        return lStrResult;
    }

    public HashMap<String, Book> checkBook(String title) {
        //System.out.println("checkBook(title)-> " + title);
        HashMap<String, Book> checkBooks = new HashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            checkBooks = (HashMap<String, Book>) in.readObject();
            //System.out.println("checkBook(1) -> " + checkBooks);
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, initialize an empty HashMap
            //System.out.println("checkBook -> No previous data found, starting with an empty database.");
            checkBooks = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return checkBooks;
    }

    public String borrowBook(String title) {
        books = checkBook(title);
        if (books.containsKey(title)) {
            Book book = books.get(title);
            if (book.getInventory()>0 && book.isAvailable()) {
                //System.out.println("book.getInventory(1) "+book.getInventory());
                int updateCount = book.getInventory()-1;
                book.updateInventory(updateCount);
                //System.out.println("book.getInventory(2) "+book.getInventory());
                updateBook(title,  book.getInventory());

                book.borrowBook();
                lStrResult = "Book "+title+" borrowed";
                //  updateBookMap(books, title, book);
                //  System.out.println("Book is borrowed.");
                return lStrResult;
            } else {
                lStrResult = "Book is not available.";
                System.out.println(lStrResult);
                return lStrResult;

            }

        } else {
            lStrResult = "Book not found.";
            System.out.println(lStrResult);
            return lStrResult;
        }

    }

    public String returnBook(String title) {
        //   HashMap<String, Book> newBooks = new HashMap<>();
        books = checkBook(title);
        if (books.containsKey(title)) {
            Book book = books.get(title);
            if (!book.isAvailable()) {
                book.returnBook();
                //  System.out.println("book.returnBook(1) "+book.getInventory());
                int updateCount = book.getInventory()+1;
                book.updateInventory(updateCount);
                //  System.out.println("book.returnBook(2) "+book.getInventory());
                updateBook(title,  book.getInventory());
                //updateBook(title, book.getInventory()+1);
                //newBooks.put(title, book);
                // updateBookMap(books, title, book);
                //   System.out.println("Book is returned.");
                lStrResult = "Book "+title+" returned";
                return lStrResult;
            } else {
                lStrResult = title+ " book is already available.";
                System.out.println(lStrResult);
                return lStrResult;
            }
        } else {
            lStrResult = "Book not found.";
            System.out.println(lStrResult);
            return lStrResult;
        }
    }

    public String displayBooks() {

        //  System.out.println("displayBooks() -> "+books);
        if(books.isEmpty()){
            // System.out.println("No books found.");
            books = checkBook("title");
            for (Book book : books.values()) {
                System.out.println(book);
            }
            lStrResult = "List of books displayed.";
            return lStrResult;
        }else {
            for (Book book : books.values()) {
                System.out.println(book);
                //books = new HashMap<>();
                //addBook(book.getTitle(), book.getAuthor(), book.getInventory());
            }
            lStrResult = "List of book displayed.";
            return lStrResult;
        }
    }

    public String searchBook(String title) {
        books = checkBook(title);
        if (books.containsKey(title)) {
            Book book = books.get(title);
            System.out.println(book);
            lStrResult = title+" Book found.";
            return lStrResult;
        } else {
            lStrResult = "Book not found.";
            System.out.println(lStrResult);
            return lStrResult;
        }
    }
    // code for update book inventory
    public void updateBook(String title, int inventory) {
        books = checkBook(title);
        if (books.containsKey(title)) {
            Book book = books.get(title);
            book.updateInventory(inventory);
            System.out.println("Book inventory updated.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void updateBookMap(HashMap<String, Book> map, String title, Book newBook) {
        //System.out.println("updateBookMap() ->"+title+" newBook "+newBook+" map "+map);

        if (map != null && map.containsKey(title)) {
            //map.put(title, newBook);  // Update the Book object with the new details
            // System.out.println("Book updated in HashMap getInventory()-> "+newBook.getInventory());
        } else {
            System.out.println("Book with the title '" + title + "' not found.");
        }
    }

    public String deleteBook(String title) {
        books = checkBook(title);
        if (books.containsKey(title)) {
            books.remove(title);
            lStrResult = title+" book deleted successfully.";
            System.out.println(lStrResult);
            return lStrResult;
        } else {
            lStrResult = "Book not found.";
            System.out.println("Book not found.");
            return lStrResult;
        }
    }
}