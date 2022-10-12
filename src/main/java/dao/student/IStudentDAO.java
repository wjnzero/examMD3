package dao.student;

import model.Student;

import java.util.List;

public interface IStudentDAO {
    List<Student> findAll();
}
