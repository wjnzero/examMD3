package controller;

import model.Classroom;
import model.Student;
import service.INewService;
import service.class1.ClassService;
import service.class1.IClassService;
import service.student.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/")
public class HomeServlet extends HttpServlet {

    private INewService studentService;
    private IClassService classService;
    private List<Classroom> classrooms;

    @Override
    public void init() {
        studentService = new StudentService();
        classService = new ClassService();
        classrooms = classService.fillAll();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(request,response);
                break;
            case "edit":
                showEdit(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
            case "find":
                findByName(request,response);
                break;
            default:
                listStudent(request,response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createUser(request,response);
                break;
            case "edit":
                editUser(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
            case "find":
                findByName(request,response);
                break;
            default:
                listStudent(request,response);
                break;
        }
    }

    private void listStudent(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Student> students  = studentService.fillAll();
        request.setAttribute("students",students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/create.jsp");
        dispatcher.forward(request,response);
    }

    private void showEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = (Student) studentService.selectStudentById(id);
        RequestDispatcher dispatcher;

        if (student == null){
            dispatcher = request.getRequestDispatcher("views/student/404.jsp");
        }else {
            request.setAttribute("student",student);
            dispatcher = request.getRequestDispatcher("views/student/edit.jsp");
        }
        dispatcher.forward(request,response);
    }
    private void createUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
        LocalDate dateOfBirth = LocalDate.parse(new String(request.getParameter("dateOfBirth").getBytes("iso-8859-1"),"utf-8"));
        String address = new String(request.getParameter("address").getBytes("iso-8859-1"),"utf-8");
        String phoneNumber = new String(request.getParameter("phoneNumber").getBytes("iso-8859-1"),"utf-8");
        String email = new String(request.getParameter("email").getBytes("iso-8859-1"),"utf-8");
        int classRoom = Integer.parseInt(request.getParameter("classRoom"));
        Student newStudent = new Student(dateOfBirth, name, phoneNumber,email,address,classRoom);
        studentService.insertStudent(newStudent);
        request.setAttribute("message", "Thêm học viên thành công!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/create.jsp");
        dispatcher.forward(request,response);
    }

    private void editUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
        LocalDate dateOfBirth = LocalDate.parse(new String(request.getParameter("dateOfBirth").getBytes("iso-8859-1"),"utf-8"));
        String address = new String(request.getParameter("address").getBytes("iso-8859-1"),"utf-8");
        String phoneNumber = new String(request.getParameter("phoneNumber").getBytes("iso-8859-1"),"utf-8");
        String email = new String(request.getParameter("email").getBytes("iso-8859-1"),"utf-8");
        int classRoom = Integer.parseInt(request.getParameter("classRoom"));
        Student newStudent = new Student(id,dateOfBirth, name, phoneNumber,email,address,classRoom);

        studentService.updateStudent(newStudent);

        request.setAttribute("message","Sửa thông tin học viên thành công!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/edit.jsp");
        dispatcher.forward(request,response);
    }
    private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deleteStudent(id);

        List<Student> students = studentService.fillAll();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/list.jsp");
        dispatcher.forward(request, response);
    }

    private void  findByName(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String name = new String(request.getParameter("search").getBytes("iso-8859-1"),"utf-8");

        List<Student> students = (List<Student>) studentService.selectStudentByName(name);

        request.setAttribute("students",students);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/student/list.jsp");
        dispatcher.forward(request,response);
    }


}
