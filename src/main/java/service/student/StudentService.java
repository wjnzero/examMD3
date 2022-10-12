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
}
