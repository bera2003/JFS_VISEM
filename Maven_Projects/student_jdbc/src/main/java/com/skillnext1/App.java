package com.skillnext1;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final StudentDAO dao = new StudentDAO();

    public static void main(String[] args) {
        System.out.println("=== STUDENT JDBC APP ===");

        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> insert();
                    case "2" -> update();
                    case "3" -> delete();
                    case "4" -> viewAll();
                    case "5" -> viewById();
                    case "0" -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1) Insert Student");
        System.out.println("2) Update Student");
        System.out.println("3) Delete Student");
        System.out.println("4) View All Students");
        System.out.println("5) View by ID");
        System.out.println("0) Exit");
        System.out.print("Enter choice: ");
    }

    private static void insert() throws Exception {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Address: "); String address = sc.nextLine();

        Student s = new Student(name, age, email, address);
        int id = dao.addStudent(s);

        System.out.println(id > 0 ? "Inserted with ID: " + id : "Insert failed");
    }

    private static void update() throws Exception {
        System.out.print("Enter ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = dao.getStudentById(id);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("New Name (" + s.getName() + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) s.setName(name);

        System.out.print("New Age (" + s.getAge() + "): ");
        String ageStr = sc.nextLine();
        if (!ageStr.isEmpty()) s.setAge(Integer.parseInt(ageStr));

        System.out.print("New Email (" + s.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.isEmpty()) s.setEmail(email);

        System.out.print("New Address (" + s.getAddress() + "): ");
        String address = sc.nextLine();
        if (!address.isEmpty()) s.setAddress(address);

        boolean success = dao.updateStudent(s);
        System.out.println(success ? "Updated successfully" : "Update failed");
    }

    private static void delete() throws Exception {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        boolean success = dao.deleteStudent(id);
        System.out.println(success ? "Deleted successfully" : "Delete failed");
    }

    private static void viewAll() throws Exception {
        List<Student> list = dao.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No students found.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void viewById() throws Exception {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = dao.getStudentById(id);
        System.out.println(s == null ? "Not found" : s);
    }
}

