
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String[]> records = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== STUDENT REGISTER CLI =====");
            System.out.println("1. Add record");
            System.out.println("2. List records");
            System.out.println("3. Search record");
            System.out.println("4. Delete record");
            System.out.println("5. Show summary");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = readChoice();

            switch (choice) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    System.out.println("List records selected.");
                    break;
                case 3:
                    System.out.println("Search record selected.");
                    break;
                case 4:
                    System.out.println("Delete record selected.");
                    break;
                case 5:
                    System.out.println("Summary selected.");
                    break;
                case 0:
                    System.out.println("Exiting Student Register. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Enter a number from 0 to 5.");
            }

        } while (choice != 0);

        scanner.close();
    }

    public static int readChoice() {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean addRecord() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return false;
        }

        // Prevent duplicate IDs
        for (String[] record : records) {
            if (record[0].equalsIgnoreCase(id)) {
                System.out.println("A record with this ID already exists.");
                return false;
            }
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty.");
            return false;
        }

        System.out.print("Enter course: ");
        String course = scanner.nextLine().trim();

        if (course.isEmpty()) {
            System.out.println("Course cannot be empty.");
            return false;
        }

        records.add(new String[]{id, name, course});

        System.out.println("Record added successfully!");
        return true;
    }
}
