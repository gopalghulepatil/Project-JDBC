package jdbc;

import java.sql.*;

public class StudentDAO {

    public void insertStudent(String name, String email) {
        String sql = "INSERT INTO student_data (name, email) VALUES (?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("✅ Student inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllStudents() {
        String sql = "SELECT * FROM student_data";
        try {
        	 Connection conn = JDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql); 

            System.out.println("📋 Student List:");
            System.out.println("----------------------------------");

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    ", Name: " + rs.getString("name") +
                    ", Email: " + rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, String name, String email) {
        String sql = "UPDATE student_data SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Student updated.");
            } else {
                System.out.println("⚠️ No student found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM student_data WHERE id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("🗑️ Student deleted.");
            } else {
                System.out.println("⚠️ No student found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
