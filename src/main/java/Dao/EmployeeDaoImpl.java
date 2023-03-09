package Dao;

import bean.Employee;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    public static Connection connection= DBConnection.connection;

    public boolean addEmployee(Employee employee){

        try {

            System.out.println("Starting creating user");

            String name = employee.getName();
            String address = employee.getAddress();
            String email = employee.getEmail();
            String phone = employee.getPhone();

            String insertStatement = "INSERT INTO employee (name, email, phone, address) VALUES (?,?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);

            int result = preparedStatement.executeUpdate();

            return result == 1;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean updateEmployee(Employee employee) {

        try {

            System.out.println("Starting updating user");

            String name = employee.getName();
            String address = employee.getAddress();
            String email = employee.getEmail();
            String phone = employee.getPhone();

            String updateStatement = "UPDATE INTO employee SET name=?, email=?, phone=?, address=? WHERE id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, employee.getId());

            int result = preparedStatement.executeUpdate();

            return result == 1;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteEmployee(int id) {

        try {
            System.out.println("Starting updating user");

            String deleteStatement = "DELETE FROM employee WHERE id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);

            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();

            return result == 1;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public Employee getEmployee(int id) {
        try {
            System.out.println("Starting selecting all users");

            String deleteStatement = "SELECT * FROM employee WHERE id =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee=new Employee();

            while(resultSet.next()){
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setEmail(resultSet.getString("email"));
            }

            return employee;


        }catch (SQLException e){
            e.printStackTrace();

        }

        return null;

    }

    public List<Employee> getAllEmployee() {

        try {
            System.out.println("Starting selecting all users");

            String deleteStatement = "SELECT * FROM employee;";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> employees=new ArrayList<Employee>();

            while(resultSet.next()){
                Employee employee=new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setEmail(resultSet.getString("email"));
                employees.add(employee);
            }

            return employees;


        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}