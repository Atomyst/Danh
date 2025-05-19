import java.util.Scanner;

public class IOInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {
        System.out.println("\n===== E-Commerce System =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Quit");
        System.out.println("=============================");
    }

    public void displayAdminMenu() {
        System.out.println("\n===== Admin Menu =====");
        System.out.println("1. Show products");
        System.out.println("2. Add customers");
        System.out.println("3. Show customers");
        System.out.println("4. Show orders");
        System.out.println("5. Generate test data");
        System.out.println("6. Generate all statistical figures");
        System.out.println("7. Delete all data");
        System.out.println("8. Logout");
        System.out.println("=======================");
    }

    public int getUserChoice(int min, int max) {
        while (true) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid option. Choose between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public String[] getUserInput(String prompt, int numFields) {
        System.out.print(prompt + " ");
        String input = scanner.nextLine().trim();
        String[] fields = input.split("\\s+");

        if (fields.length > numFields) {
            return java.util.Arrays.copyOf(fields, numFields);
        } else {
            String[] result = new String[numFields];
            System.arraycopy(fields, 0, result, 0, fields.length);
            for (int i = fields.length; i < numFields; i++) {
                result[i] = "";
            }
            return result;
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String context, String message) {
        System.err.println("[ERROR] " + context + ": " + message);
    }
}