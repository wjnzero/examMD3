package service.class1;

import dao.class1.ClassDao;
import dao.class1.IClassDao;
import model.Classroom;

import java.util.List;

public class ClassService implements IClassService{
    private IClassDao classDao;

    public ClassService() {
        classDao = new ClassDao();
    }

    @Override
    public List<Classroom> fillAll() {
        return classDao.findAll();
    }
}
