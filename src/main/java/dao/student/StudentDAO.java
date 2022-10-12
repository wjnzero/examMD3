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
                    student.setAddress(resultSet.getString("address"));
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

    @Override
    public void insertStudent(Student student) {
        String sql = "INSERT INTO student VALUE (?,?,?,?,?,?,?)";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, student.getName());
                statement.setString(2, student.getDateBirth().toString());
                statement.setString(3,student.getPhone());
                statement.setString(4,student.getEmail());
                statement.setString(5,student.getEmail());
                statement.setString(6,student.getAddress());
                statement.setInt(7,student.getClassroom());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Student selectStudentById(int id) {
        Student student = new Student();
        String sql = "SELECT * FROM student WHERE id =?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1,id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setEmail(resultSet.getString("email"));
                    student.setAddress(resultSet.getString("address"));
                    student.setDateBirth(resultSet.getDate("datebirth").toLocalDate());
                    student.setClassroom(resultSet.getInt("class"));
                }
                return student;
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

    @Override
    public boolean deleteStudent(int id) {
        boolean status;
        String sql = "DELETE FROM student WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                status = statement.executeUpdate() > 0;
                return status;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        boolean status;
        String sql = "UPDATE student SET name = ?, datebirth= ?, phone =?, email =?, address =?, class =? where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, student.getName());
                statement.setString(2, student.getDateBirth().toString());
                statement.setString(3, student.getPhone());
                statement.setString(4, student.getEmail());
                statement.setString(5,student.getAddress());
                statement.setInt(6, student.getClassroom());
                statement.setInt(7, student.getId());
                status = statement.executeUpdate() > 0;
                return status;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public List<Student> selectStudentByName(String name) {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE name LIKE ?";
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%"+name+"%");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setEmail(resultSet.getString("email"));
                    student.setAddress(resultSet.getString("address"));
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
