package dao.class1;


import model.Classroom;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDao implements IClassDao{
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
    public List<Classroom> findAll() {
        List<Classroom> classrooms = new ArrayList<>();
        String sql = "SELECT * FROM classroom";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Classroom classroom = new Classroom();
                    classroom.setId(resultSet.getInt("id"));
                    classroom.setName(resultSet.getString("name"));
                    classrooms.add(classroom);
                }
                return classrooms;
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
