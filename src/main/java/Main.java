
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
                    listRecords();
                    break;
                case 3:
                    System.out.print("Enter student ID to search: ");
                    String searchId = scanner.nextLine().trim();

                    if (!searchId.isEmpty()) {
                        searchRecord(searchId);
                    } else {
                        System.out.println("Student ID cannot be empty.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.nextLine().trim();

                    if (!deleteId.isEmpty()) {
                        deleteRecord(deleteId);
                    } else {
                        System.out.println("Student ID cannot be empty.");
                    }
                    break;

                case 5:
                    showSummary();
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

    public static int listRecords() {
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return 0;
        }

        System.out.println("\n========== STUDENT RECORDS ==========");

        System.out.printf("%-12s %-20s %-20s%n",
                "Student ID", "Name", "Course");

        System.out.println("-----------------------------------------------");

        for (String[] record : records) {
            System.out.printf("%-12s %-20s %-20s%n",
                    record[0], record[1], record[2]);
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Total records: " + records.size());

        return records.size();
    }

    public static boolean searchRecord(String searchId) {
        for (String[] record : records) {
            if (record[0].equalsIgnoreCase(searchId.trim())) {
                System.out.println("\nRecord found!");
                System.out.println("Student ID: " + record[0]);
                System.out.println("Student Name: " + record[1]);
                System.out.println("Course: " + record[2]);
                return true;
            }
        }

        System.out.println("No record found with ID: " + searchId);
        return false;
    }

    public static boolean deleteRecord(String deleteId) {
        for (int i = 0; i < records.size(); i++) {
            String[] record = records.get(i);

            if (record[0].equalsIgnoreCase(deleteId.trim())) {
                records.remove(i);
                System.out.println("Record deleted successfully!");
                return true;
            }
        }

        System.out.println("No record found with ID: " + deleteId);
        return false;
    }

    public static int showSummary() {
        if (records.isEmpty()) {
            System.out.println("The register is empty.");
            return 0;
        }

        System.out.println("\n========== REGISTER SUMMARY ==========");
        System.out.println("Total records: " + records.size());

        ArrayList<String[]> courseCounts = new ArrayList<>();

        for (String[] record : records) {
            String course = record[2];
            boolean found = false;

            for (String[] entry : courseCounts) {
                if (entry[0].equalsIgnoreCase(course)) {
                    int count = Integer.parseInt(entry[1]);
                    entry[1] = String.valueOf(count + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                courseCounts.add(new String[]{course, "1"});
            }
        }

        System.out.println("\nRecords by course:");

        for (String[] entry : courseCounts) {
            System.out.println(entry[0] + ": " + entry[1]);
        }

        System.out.println("=======================================");

        return records.size();
    }
}
