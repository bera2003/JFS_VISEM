package com.company.employee.main;

import com.company.employee.dao.EmployeeDAO;
import com.company.employee.model.Employee;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();

                    dao.addEmployee(new Employee(0, name, email, salary));
                    break;

                case 2:
                    dao.viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter Employee ID to Update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("New Salary: ");
                    double newSalary = sc.nextDouble();

                    dao.updateEmployee(uid, newName, newEmail, newSalary);
                    break;

                case 4:
                    System.out.print("Enter Employee ID to Delete: ");
                    int did = sc.nextInt();

                    dao.deleteEmployee(did);
                    break;

                case 5:
                    System.out.println("Thank you!");
                    System.exit(0);
            }
        }
    }
}