import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    private String employeeId;  // Changed to String
    private String name;
    private LocalDate dateHired;
    private String department;
    private String position;
    private String status;
    private double ratePerHour;
    private double hoursWorked;
    private double overtimePay;
    private double totalSalary;

    // Constructor
    public Employee(String name, String dateHired, String department, String position, String status, 
                    double ratePerHour, double hoursWorked, double overtimePay, double totalSalary) {
        this.name = name;
        this.dateHired = LocalDate.parse(dateHired, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Convert String to LocalDate
        this.department = department;
        this.position = position;
        this.status = status;
        this.ratePerHour = ratePerHour;
        this.hoursWorked = hoursWorked;
        this.overtimePay = overtimePay;
        this.totalSalary = totalSalary;
    }

    // Salary Calculation
    private double calculateTotalSalary() {
        return (ratePerHour * hoursWorked) + overtimePay;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }  // Fixed return type
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }  // Remove int version

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDateHired() { return dateHired; }
    public void setDateHired(String dateHired) { 
        try {
            this.dateHired = LocalDate.parse(dateHired, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getRatePerHour() { return ratePerHour; }
    public void setRatePerHour(double ratePerHour) { 
        this.ratePerHour = ratePerHour; 
        this.totalSalary = calculateTotalSalary(); // Recalculate salary
    }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { 
        this.hoursWorked = hoursWorked; 
        this.totalSalary = calculateTotalSalary(); // Recalculate salary
    }

    public double getOvertimePay() { return overtimePay; }
    public void setOvertimePay(double overtimePay) { 
        this.overtimePay = overtimePay; 
        this.totalSalary = calculateTotalSalary(); // Recalculate salary
    }

    public double getTotalSalary() { return totalSalary; }
    public void setTotalSalary(double totalSalary) { this.totalSalary = totalSalary; }

    // toString method
    @Override
    public String toString() {
        return String.format(
           
            "Employee ID   : %s%n" +  // Fixed from %d to %s
            "Name         : %s%n" +
            "Date Hired   : %s%n" +  // Convert dateHired to String format
            "Department   : %s%n" +
            "Position     : %s%n" +
            "Status       : %s%n" +
            "Rate Per Hour: %.2f%n" +
            "Hours Worked : %.2f%n" +   
            "Overtime Pay : %.2f%n" +
            "Total Salary : %.2f%n" +
            "=============================",
            employeeId, name, dateHired.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
            department, position, status, ratePerHour, hoursWorked, overtimePay, totalSalary
        );
    }
}
