package com.company.employee.dao;

import com.company.employee.model.Employee;
import com.company.employee.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDAO {

    // Add Employee
    public void addEmployee(Employee emp) {

        String sql = "INSERT INTO employee (name, email, salary) VALUES (?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setDouble(3, emp.getSalary());

            ps.executeUpdate();
            System.out.println("Employee added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View All Employees
    public void viewEmployees() {

        String sql = "SELECT * FROM employee";

        try (
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {
            System.out.println("\nID | Name | Email | Salary");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getDouble("salary")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Update Employee
public void updateEmployee(int id, String name, String email, double salary) {

    String sql = "UPDATE employee SET name=?, email=?, salary=? WHERE id=?";

    try (
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setDouble(3, salary);
        ps.setInt(4, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Employee updated successfully");
        } else {
            System.out.println("Employee not found");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Delete Employee
public void deleteEmployee(int id) {

    String sql = "DELETE FROM employee WHERE id=?";

    try (
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Employee deleted successfully");
        } else {
            System.out.println("Employee not found");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
