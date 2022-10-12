package dao.student;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/student_management";
            String user = "root";
            String password = "123456";
            return DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException | SQLException ohNo) {
            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setEmail(resultSet.getString("email"));
                    student.setDateBirth(resultSet.getDate("datebirth").toLocalDate());
                    student.setClassroom(resultSet.getInt("class"));
                    studentList.add(student);
                }
                return studentList;
            } catch (SQLException e) {
                return null;
            }
            finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    return null;
                }
            }
        }
        return null;
    }
}
