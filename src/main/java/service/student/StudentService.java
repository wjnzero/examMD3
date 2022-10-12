package service.student;

import dao.student.IStudentDAO;
import dao.student.StudentDAO;
import model.Student;

import java.util.List;

public class StudentService implements IStudentService {

    private IStudentDAO studentDao;

    public StudentService() {
        studentDao = new StudentDAO();
    }

    @Override
    public List<Student> fillAll() {
        return studentDao.findAll();
    }
    @Override
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    @Override
    public Student selectStudentById(int id) {
        return studentDao.selectStudentById(id);
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentDao.deleteStudent(id);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Override
    public List<Student> selectStudentByName(String name) {
        return studentDao.selectStudentByName(name);
    }
}
