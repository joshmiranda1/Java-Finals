// Title: ShelfMaster
// DIPROGLANG Project
// Created by John Harold D.
// 
import java.io.*;
import java.util.*;

public class Controller {
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Employee> employees = new ArrayList<>();
    private static int employeeCounter = 1;
    static Scanner input = new Scanner(System.in);
    int index = 1;
    final String dataFile = "Library-Catalogue.csv";

    @SuppressWarnings("ConvertToTryWithResources")
    public void demoDataLoad() {
        System.out.println("\n                            |===============================================================|");
        System.out.println("                            |Welcome to the Holy Angel University Library Management System!|");
        System.out.println("                            |===============================================================|");

        boolean running = true;

        do {
            System.out.println("\n<Main Menu>");
            System.out.println("===================================================================================================================");
            System.out.println("What would you like to do?        |\n [1] Borrow a book                |\n [2] Display all books            |\n [3] Return a book                |\n [4] Remove a book(admin)         |\n [5] Library Staff Payroll System |\n [6] Exit Program                 |");
            System.out.println("===================================================================================================================\n");
            System.out.print("Enter your Choice: ");

            int choice;
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer!");
                input.nextLine();
                continue;
            }

            try {
                if (choice == 1) {
                    ArrayList<Library> demoData = DataReader.readData(dataFile);

                    System.out.println("What book would you like to borrow?");
                    System.out.println("There are only " + demoData.size() + " entries in the catalogue");
                    System.out.print("Select what book number to borrow: ");
                    index = input.nextInt();
                    input.nextLine();

                    if (index <= 0 || index > demoData.size()) {
                        System.out.println("Please input a valid book number.");
                        continue;
                    } else {
                        Library current = demoData.get(index - 1);
                        System.out.println("Row: " + index + "\n" + current.toString());
                    }

                    try {
                        List<List<String>> rows = new ArrayList<>();
                        BufferedReader CsvR = new BufferedReader(new FileReader(dataFile));
                        String row;
                        while ((row = CsvR.readLine()) != null) {
                            String[] data = row.split(",");
                            rows.add(Arrays.asList(data));
                        }
                        CsvR.close();

                        if (index > 0 && index <= rows.size()) {
                            rows.remove(index - 1);
                        } else {
                            System.out.println("Book does not exist, returning to menu...");
                            continue;
                        }

                        BufferedWriter AddCSV = new BufferedWriter(new FileWriter(dataFile));
                        for (List<String> rowData : rows) {
                            AddCSV.write(String.join(",", rowData));
                            AddCSV.newLine();
                        }
                        AddCSV.close();

                        System.out.println("The Book has been borrowed successfully!");
                    } catch (IOException e) {
                        System.err.println("Error accessing CSV file: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer.");
                input.nextLine();
            }

            if (choice == 2) {
                ArrayList<Library> demoData = DataReader.readData(dataFile);
                System.out.println("There are " + demoData.size() + " entries in the catalogue");
                System.out.println("Displaying all books:\n");
                System.out.printf("%-40s %-20s %-10s %-40s %-20s%n", "Name:", "Author:", "Pages:", "Publisher:", "Genre:");
                for (Library book : demoData) {
                    System.out.println(book.toString());
                }
            }

            if (choice == 3) {
                System.out.println("Please input the following details to return a book.");
                System.out.println("==============================");
                String name, author, page, publisher, genre;

                try (BufferedWriter Addbookspls = new BufferedWriter(new FileWriter(dataFile, true))) {
                    input.nextLine();
                    System.out.print("Name: ");
                    name = input.nextLine();
                    System.out.print("Author: ");
                    author = input.nextLine();
                    System.out.print("Pages: ");
                    page = input.nextLine();
                    System.out.print("Publisher: ");
                    publisher = input.nextLine();
                    System.out.print("Genre: ");
                    genre = input.nextLine();
                    Addbookspls.write(name + "," + author + "," + page + "," + publisher + "," + genre);
                    Addbookspls.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (choice == 4) {
                    System.out.print("Select the book number to remove from the catalogue: ");
                    index = input.nextInt();
                    input.nextLine();

                    try {
                        List<List<String>> rows = new ArrayList<>();
                        try (BufferedReader CsvR = new BufferedReader(new FileReader(dataFile))) {
                            String row;
                            while ((row = CsvR.readLine()) != null) {
                                String[] data = row.split(",");
                                rows.add(Arrays.asList(data));
                            }
                        }

                        if (index > 0 && index <= rows.size()) {
                            rows.remove(index - 1);
                        } else {
                            System.out.println("Book does not exist in catalogue, returning to menu...");
                            continue;
                        }

                        BufferedWriter AddCSV = new BufferedWriter(new FileWriter(dataFile));
                        for (List<String> rowData : rows) {
                            AddCSV.write(String.join(",", rowData));
                            AddCSV.newLine();
                        }
                        AddCSV.close();

                        System.out.println("The book has been removed successfully!");
                    } catch (IOException e) {
                        System.err.println("Error accessing CSV file: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer.");
                input.nextLine();
            }

            if (choice == 5) {
                handlePayrollSystem();
            }

            if (choice == 6) {
                System.out.println("Thank you for using the library management system!");
                running = false;
            }

            if (choice >= 1 && choice <= 5) {
                System.out.print("Do you want to continue? (Y/N): ");
                String choiceAnswer = input.next().toUpperCase();

                if (choiceAnswer.equals("N")) {
                    running = false;
                    System.out.println("\nThank you for using the library management system!");
                }
            }
        } while (running);
    }
    // Payroll system
    public void handlePayrollSystem() {
        boolean payrollRunning = true;
        while (payrollRunning) {
            System.out.println("\n=== Library Staff Payroll System ===");
            System.out.println("[1] Add Employee");
            System.out.println("[2] Edit Employee");
            System.out.println("[3] Delete Employee");
            System.out.println("[4] Display Employees");
            System.out.println("[5] Search Employee by ID");  // New Option
            System.out.println("[6] Exit Payroll System");
            System.out.println("\n====================================");
            System.out.print("Enter choice: ");  int payrollChoice;
    
          
            try {
                payrollChoice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                input.nextLine();
                continue;
            }
    
            switch (payrollChoice) {
                case 1 -> addEmployee();
                case 2 -> editEmployee();
                case 3 -> deleteEmployee();
                case 4 -> displayEmployees();
                case 5 -> searchEmployeeById();  // Call Search Method
                case 6 -> {
                    payrollRunning = false;
                    System.out.println("Exiting Payroll System...");
                }
                default -> System.out.println("Invalid choice! Please select a valid option.");
                
            }
        }
    }
    // Add Employee
    public void addEmployee() {
        System.out.println("\n====================================");
        System.out.println("\n--- Add Employee ---");
        System.out.print("Enter Employee Name (First MI. Last): ");
        String name = input.nextLine();

        // Validate date input
        String dateHired;
        while (true) {
            System.out.print("Enter Date Hired (YYYY-MM-DD): ");
            dateHired = input.nextLine();
            if (dateHired.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break;  // Valid format, exit loop
            } else {
                System.out.println("Invalid date format! Please enter in YYYY-MM-DD format.");
            }
        }

        System.out.print("Enter Department: ");
        String department = input.nextLine();

        System.out.println("Select Position:");
        System.out.println("[1] Student Worker");
        System.out.println("[2] Librarian");
        System.out.println("[3] Assistant Librarian");
        System.out.println("[4] Clerk");
        System.out.println("[5] Other");
        
        int positionChoice;
        while (true) {
            System.out.print("Enter your choice: ");
            if (input.hasNextInt()) {
                positionChoice = input.nextInt();
                input.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                input.nextLine(); // Clear invalid input
            }
        }
        System.out.println("\n====================================");   

        String position = getPositionByChoice(positionChoice);

        System.out.print("Enter Employment Status (Probationary/Permanent/Contractual): ");
        String status = input.nextLine();

        double ratePerHour = getRateByPosition(position);

        double hoursWorked;
        while (true) {
            System.out.print("Enter Hours Worked: ");
            if (input.hasNextDouble()) {
                hoursWorked = input.nextDouble();
                input.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine(); // Clear invalid input
            }
        }

        double overtimePay = (hoursWorked > 40) ? (hoursWorked - 40) * (ratePerHour * 1.5) : 0;
        double totalSalary = (ratePerHour * hoursWorked) + overtimePay;

        Employee newEmployee = new Employee(name, dateHired, department, position, status, ratePerHour, hoursWorked, overtimePay, totalSalary);
        newEmployee.setEmployeeId(String.format("%04d", employeeCounter++)); // Format ID as 0001, 0002, etc.
        employees.add(newEmployee);

        System.out.println("\nEmployee added successfully!");
        System.out.println("Employee ID: " + newEmployee.getEmployeeId());  // Show assigned ID
    }


    
    public void searchEmployeeById() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employees found.");
            return;
        }
    
        System.out.print("\nEnter Employee ID to search: ");
        int searchId;
        try {
            searchId = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            input.nextLine();
            return;
        }
    
        for (Employee emp : employees) {
            if (emp.getEmployeeId().equals(String.format("%04d", searchId))) {
                System.out.println("\nEmployee Found: " + emp);
                return;
            }
        }
    
        System.out.println("\nNo employee found with ID: " + searchId);
    }
    
    
    private String getPositionByChoice(int choice) {
        return switch (choice) {
            case 1 -> "Student Worker";
            case 2 -> "Librarian";
            case 3 -> "Assistant Librarian";
            case 4 -> "Clerk";
            case 5 -> "Other";
            default -> "Unknown";
        };
    }

    private double getRateByPosition(String position) {
        return switch (position) {
            case "Student Worker" -> 10.0;
            case "Librarian" -> 25.0;
            case "Assistant Librarian" -> 20.0;
            case "Clerk" -> 15.0;
            case "Other" -> 12.0;
            default -> 0.0;
        };
    }
    // Helper function to ask if the user wants to edit a field
    private boolean editField(String fieldName) {
        System.out.print("Do you want to edit " + fieldName + "? (Y/N): ");
        String response = input.nextLine().trim().toUpperCase();
        return response.equals("Y");
    }

    public void editEmployee() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employees found.");
            return;
        }
    
        System.out.println("\n--- Employee List ---");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + employees.get(i).getName());
        }
    
        // Select employee to edit
        System.out.print("\nEnter the number of the employee to edit: ");
        int empIndex;
        try {
            empIndex = input.nextInt() - 1;
            input.nextLine(); // Consume newline
            if (empIndex < 0 || empIndex >= employees.size()) {
                System.out.println("Invalid employee number.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            input.nextLine();
            return;
        }
    
        Employee emp = employees.get(empIndex);
    
        System.out.println("\nEditing Employee: " + emp.getName());
    
        
    
        if (editField("Name")) {
            System.out.print("New Name (" + emp.getName() + "): ");
            String newName = input.nextLine();
            if (!newName.isEmpty()) emp.setName(newName);
        }
    
        if (editField("Date Hired")) {
            System.out.print("New Date Hired (" + emp.getDateHired() + "): ");
            String newDateHired = input.nextLine();
            if (!newDateHired.isEmpty()) emp.setDateHired(newDateHired);
        }
    
        if (editField("Department")) {
            System.out.print("New Department (" + emp.getDepartment() + "): ");
            String newDepartment = input.nextLine();
            if (!newDepartment.isEmpty()) emp.setDepartment(newDepartment);
        }
    
        if (editField("Position")) {
            System.out.print("New Position (" + emp.getPosition() + "): ");
            String newPosition = input.nextLine();
            if (!newPosition.isEmpty()) emp.setPosition(newPosition);
        }
    
        if (editField("Status")) {
            System.out.print("New Status (" + emp.getStatus() + "): ");
            String newStatus = input.nextLine();
            if (!newStatus.isEmpty()) emp.setStatus(newStatus);
        }
    
        if (editField("Rate per Hour")) {
            System.out.print("New Rate per Hour ($" + emp.getRatePerHour() + "): ");
            String newRate = input.nextLine();
            if (!newRate.isEmpty()) {
                try {
                    emp.setRatePerHour(Double.parseDouble(newRate));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rate! Keeping previous value.");
                }
            }
        }
    
        if (editField("Hours Worked")) {
            System.out.print("New Hours Worked (" + emp.getHoursWorked() + "): ");
            String newHoursWorked = input.nextLine();
            if (!newHoursWorked.isEmpty()) {
                try {
                    emp.setHoursWorked(Double.parseDouble(newHoursWorked));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid hours! Keeping previous value.");
                }
            }
        }
    
        // Recalculate overtime pay and total salary
        double overtimePay = (emp.getHoursWorked() > 40) ? (emp.getHoursWorked() - 40) * (emp.getRatePerHour() * 1.5) : 0;
        emp.setOvertimePay(overtimePay);
        emp.setTotalSalary((emp.getRatePerHour() * emp.getHoursWorked()) + overtimePay);
    
        System.out.println("\nEmployee updated successfully!");
        System.out.println(emp);
    }
    
    public void deleteEmployee() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employees found.");
            return;
        }
    
        System.out.println("\n--- Employee List ---");
    for (int i = 0; i < employees.size(); i++) {
        // Format ID with leading zeros (4 digits)
        String formattedId = String.format("%04d", (i + 1));
        System.out.println("[" + formattedId + "] " + employees.get(i).getName());
    }

        // Select employee to delete
        System.out.print("\nEnter the number of the employee to delete: ");
        int empIndex;
        try {
            empIndex = input.nextInt() - 1;
            input.nextLine(); 
            if (empIndex < 0 || empIndex >= employees.size()) {
                System.out.println("Invalid employee number.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            input.nextLine();
            return;
        }
    
        Employee removedEmployee = employees.remove(empIndex);
        System.out.println("\nEmployee '" + removedEmployee.getName() + "' has been successfully deleted.");
    }
    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employees found.");
        } else {
            System.out.println("\n--- Employee List ---");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }
}