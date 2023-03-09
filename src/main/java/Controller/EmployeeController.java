package Controller;

import Dao.EmployeeDao;
import Dao.EmployeeDaoImpl;
import bean.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("")
public class EmployeeController extends HttpServlet {

    EmployeeDao employeeDao=null;

    @Override
    public void init() throws ServletException {
        employeeDao=new EmployeeDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getServletPath();

        switch (action) {

            case "/add":
                boolean addStatus=addEmployee(req,resp);
                break;
            case "/update":
                boolean updateStatus=updateEmployee(req,resp);
                break;
            case "/delete":
                boolean delStatus=deleteEmployee(req,resp);
                break;
            case "/list":
                List<Employee> employees=getAllEmployee(req,resp);
                break;
            case "/get":
                Employee employee=getEmployee(req, resp);
            default:
                getEmployee(req, resp);
                break;

        }
    }

    private List<Employee> getAllEmployee(HttpServletRequest req, HttpServletResponse resp) {
        List<Employee> employeeList=employeeDao.getAllEmployee();
        return employeeList;
    }

    private Employee getEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        PrintWriter pw=resp.getWriter();
        pw.write("<h1>Get here</h1>");
       Employee employee= employeeDao.getEmployee(id);

       return employee;
    }

    private boolean deleteEmployee(HttpServletRequest req, HttpServletResponse resp) {
        int id=Integer.parseInt(req.getParameter("id"));

        return employeeDao.deleteEmployee(id);
    }

    private boolean updateEmployee(HttpServletRequest req, HttpServletResponse resp) {
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String phone=req.getParameter("phone");
        String address=req.getParameter("address");
        Employee employee=new Employee(name,email,phone,address);
        return employeeDao.updateEmployee(employee);
    }

    private boolean addEmployee(HttpServletRequest req, HttpServletResponse resp) {

        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String phone=req.getParameter("phone");
        String address=req.getParameter("address");
        Employee employee=new Employee(name,email,phone,address);
        return employeeDao.addEmployee(employee);

    }

    //Note : You can use request dispatcher to move or include other page for personalised responses
}
