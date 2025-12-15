package com.skillnext1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public int addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO students (name, age, email, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, s.getName());
            pst.setInt(2, s.getAge());
            pst.setString(3, s.getEmail());
            pst.setString(4, s.getAddress());

            int affected = pst.executeUpdate();
            if (affected == 0) return -1;

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public boolean updateStudent(Student s) throws SQLException {
        String sql = "UPDATE students SET name=?, age=?, email=?, address=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, s.getName());
            pst.setInt(2, s.getAge());
            pst.setString(3, s.getEmail());
            pst.setString(4, s.getAddress());
            pst.setInt(5, s.getId());

            return pst.executeUpdate() > 0;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        }
    }

    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT id, name, age, email, address FROM students WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("email"),
                            rs.getString("address")
                    );
                }
            }
        }
        return null;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, name, age, email, address FROM students ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("address")
                ));
            }
        }
        return list;
    }
}
