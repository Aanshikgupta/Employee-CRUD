package Dao;

import bean.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    //Insert
    public boolean addEmployee(Employee employee);

    //Update
    public boolean updateEmployee(Employee employee);

    //Delete
    public boolean deleteEmployee(int id);

    //Get one
    public Employee getEmployee(int id);

    //Get All
    public List<Employee> getAllEmployee();

}