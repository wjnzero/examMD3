package dao.student;

import model.Student;

import java.util.List;

public interface IStudentDAO {
    List<Student> findAll();
    void insertStudent(Student student);
    Student selectStudentById(int id);
    boolean deleteStudent(int id);
    boolean updateStudent(Student student);
    List<Student> selectStudentByName(String name);
}
