import java.io.*;

public class Main {
    private static final String USER_DATA_FILE = "users.txt";

    public static void main(String[] args) {
        IOInterface io = new IOInterface();

        while (true) {
            io.displayMainMenu();
            int choice = io.getUserChoice(1, 3);

            switch (choice) {
                case 1 -> handleLogin(io);
                case 2 -> handleRegistration(io);
                case 3 -> {
                    io.printMessage("Exiting application...");
                    return;
                }
                default -> io.printErrorMessage("Main Menu", "Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void handleLogin(IOInterface io) {
        io.printMessage("Login: Please enter your username and password.");
        String[] credentials = io.getUserInput("Username Password:", 2);

        if (validateUser(credentials[0], credentials[1])) {
            io.printMessage("Login successful. Welcome, " + credentials[0] + "!");
            adminFlow(io);
        } else {
            io.printErrorMessage("Login", "Invalid username or password.");
        }
    }

    private static void handleRegistration(IOInterface io) {
        io.printMessage("Registration process...");
        String[] userDetails = io.getUserInput("Enter Username and Password:", 2);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(userDetails[0] + "," + userDetails[1]);
            writer.newLine();
            io.printMessage("User " + userDetails[0] + " successfully registered!");
        } catch (IOException e) {
            io.printErrorMessage("Registration", "Failed to save user data.");
        }
    }

    private static boolean validateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2 && data[0].equals(username) && data[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Login: Failed to read user data.");
        }
        return false;
    }

    private static void adminFlow(IOInterface io) {
        while (true) {
            io.displayAdminMenu();
            int choice = io.getUserChoice(1, 8);

            switch (choice) {
                case 1 -> io.printMessage("Displaying all products...");
                case 2 -> io.printMessage("Adding new customers...");
                case 3 -> io.printMessage("Showing customer list...");
                case 4 -> io.printMessage("Displaying order list...");
                case 5 -> io.printMessage("Generating test order data...");
                case 6 -> io.printMessage("Generating statistical figures...");
                case 7 -> io.printMessage("Deleting all data...");
                case 8 -> {
                    io.printMessage("Logging out...");
                    return;
                }
                default -> io.printErrorMessage("Admin Menu", "Invalid option. Please try again.");
            }
        }
    }
}