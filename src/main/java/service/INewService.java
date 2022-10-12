package service;
import model.Student;

import java.util.List;

public interface INewService<E> {
    List<E> fillAll();
    void insertStudent(Student student);
    E selectStudentById(int id);
    boolean deleteStudent(int id);
    boolean updateStudent(Student student);
    List<E> selectStudentByName(String name);
}
