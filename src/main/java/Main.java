
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String[]> records = new ArrayList<>();

    // Main method: displays the menu and handles user choices
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

                    if (isValidText(searchId)) {
                        searchRecord(searchId);
                    } else {
                        System.out.println("Student ID cannot be empty.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.nextLine().trim();

                    if (isValidText(deleteId)) {
                        deleteRecord(deleteId);
                    } else {
                        System.out.println("Student ID cannot be empty.");
                    }
                    break;

                case 5:
                    showSummary();
                    break;

                case 0:
                    System.out.println(
                            "Exiting Student Register. Goodbye!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Enter a number from 0 to 5.");
            }

        } while (choice != 0);

        scanner.close();
    }

    // Reads and validates the menu choice
    public static int readChoice() {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Adds a new student record
    public static boolean addRecord() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        if (!isValidText(id)) {
            System.out.println("Student ID cannot be empty.");
            return false;
        }

        if (findRecordIndex(id) != -1) {
            System.out.println(
                    "A record with this ID already exists.");
            return false;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        if (!isValidText(name)) {
            System.out.println("Student name cannot be empty.");
            return false;
        }

        System.out.print("Enter course: ");
        String course = scanner.nextLine().trim();

        if (!isValidText(course)) {
            System.out.println("Course cannot be empty.");
            return false;
        }

        records.add(new String[]{id, name, course});

        System.out.println("Record added successfully!");
        return true;
    }

    // Displays all student records
    public static int listRecords() {
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return 0;
        }

        System.out.println("\n========== STUDENT RECORDS ==========");
        System.out.printf("%-12s %-20s %-20s%n",
                "Student ID", "Name", "Course");
        System.out.println("------------------------------------------------");

        for (String[] record : records) {
            System.out.printf("%-12s %-20s %-20s%n",
                    record[0], record[1], record[2]);
        }

        System.out.println("------------------------------------------------");
        System.out.println("Total records: " + records.size());

        return records.size();
    }

    // Searches for a student by ID
    public static boolean searchRecord(String searchId) {
        int index = findRecordIndex(searchId);

        if (index != -1) {
            String[] record = records.get(index);

            System.out.println("\nRecord found!");
            System.out.println("Student ID: " + record[0]);
            System.out.println("Student Name: " + record[1]);
            System.out.println("Course: " + record[2]);

            return true;
        }

        System.out.println("No record found with ID: " + searchId);
        return false;
    }

    // Deletes a student record by ID
    public static boolean deleteRecord(String deleteId) {
        int index = findRecordIndex(deleteId);

        if (index != -1) {
            records.remove(index);
            System.out.println("Record deleted successfully!");
            return true;
        }

        System.out.println("No record found with ID: " + deleteId);
        return false;
    }

    // Displays the total number of students and counts by course
    public static int showSummary() {
        if (records.isEmpty()) {
            System.out.println("The register is empty.");
            return 0;
        }

        System.out.println("\n========== REGISTER SUMMARY ==========");
        System.out.println("Total records: " + records.size());
        System.out.println("\nRecords by course:");

        ArrayList<String> displayedCourses = new ArrayList<>();

        for (String[] record : records) {
            String course = record[2];
            boolean alreadyDisplayed = false;

            for (String displayed : displayedCourses) {
                if (displayed.equalsIgnoreCase(course)) {
                    alreadyDisplayed = true;
                    break;
                }
            }

            if (!alreadyDisplayed) {
                int count = countCourse(course);
                System.out.println(course + ": " + count);
                displayedCourses.add(course);
            }
        }

        System.out.println("=======================================");

        return records.size();
    }

    // Helper: finds a record's index by student ID
    public static int findRecordIndex(String id) {
        if (!isValidText(id)) {
            return -1;
        }

        for (int i = 0; i < records.size(); i++) {
            if (records.get(i)[0].equalsIgnoreCase(id.trim())) {
                return i;
            }
        }

        return -1;
    }

    // Helper: counts students enrolled in a particular course
    public static int countCourse(String course) {
        if (!isValidText(course)) {
            return 0;
        }

        int count = 0;

        for (String[] record : records) {
            if (record[2].equalsIgnoreCase(course.trim())) {
                count++;
            }
        }

        return count;
    }

    // Helper: retrieves a student's name using their ID
    public static String getStudentName(String id) {
        int index = findRecordIndex(id);

        if (index != -1) {
            return records.get(index)[1];
        }

        return null;
    }

    // Helper: checks whether text is non-null and non-empty
    public static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }
}
