package com.lms.app.entry;

import com.lms.app.entry.LibraryManagementSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagementSystemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void adminRegister() throws Exception {
        String[] args = {"register","admin","David","password1"};

        String output = tapSystemOut(() -> {
            LibraryManagementSystem.main(args);
        });

        outContent.reset();
        assertTrue(output.contains("Admin David successfully registered."));
    }
    @Test
    void userRegister() throws Exception {
        String[] args = {"register","user","Sang","password1"};

        String output = tapSystemOut(() -> {
            LibraryManagementSystem.main(args);
        });

        outContent.reset();
        assertTrue(output.contains("User Sang successfully registered."));
    }

    @Test
    void adminExistRegister() throws Exception {
        String[] args = {"register","admin","David","password1"};

        String output = tapSystemOut(() -> {
            LibraryManagementSystem.main(args);
        });

        outContent.reset();
        assertTrue(output.contains("Username already exists. Please try a different username."));
    }

    @Test
    void userExitRegister() throws Exception {
        String[] args = {"register","user","Sang","password1"};

        String output = tapSystemOut(() -> {
            LibraryManagementSystem.main(args);
        });

        outContent.reset();
        assertTrue(output.contains("Username already exists. Please try a different username."));
    }
    @Test
    void adminLogin() throws Exception {
        String[] args = {"login","Alice","password1"};

        String output = tapSystemOut(() -> {
            LibraryManagementSystem.main(args);
        });

        outContent.reset();
        assertTrue(output.contains("Admin Alice successfully logged in."));
    }
    @Test
    void userLogin() throws Exception {
        String[] args = {"login","Sang","password1"};

        String output = tapSystemOut(() -> {
            LibraryManagementSystem.main(args);
        });

        outContent.reset();
        assertTrue(output.contains("User Sang successfully logged in."));
    }
    @Test
    void adminInvalidLogin() throws Exception {
        String[] args = {"login","Alice1","password1"};

        String output = tapSystemOut(() -> {
            LibraryManagementSystem.main(args);
        });

        outContent.reset();
        assertTrue(output.contains("Invalid user credentials."));
    }
    @Test
    void userInvalidLogin() throws Exception {
        String[] args = {"login","Sang1","password1"};

        String output = tapSystemOut(() -> {
            LibraryManagementSystem.main(args);
        });

        outContent.reset();
        assertTrue(output.contains("Invalid user credentials."));
    }
}