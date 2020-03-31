package implementations;

import interfaces.Database;
import utils.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseImpl implements Database {


    private final String connectionString = "jdbc:mysql://localhost:3306/company?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&" +
            "user=employee&password=123";
    private Connection connection = null;

    @Override
    public void open() {
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Employee> getEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from employee");

            while (resultSet.next())
            {
                Employee employee = new Employee(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4).charAt(0),
                        resultSet.getDouble(5)
                );
                employeeList.add(employee);
            }

            statement.close();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally {

        }

        return employeeList;
    }

    @Override
    public void insertEmployee(Employee employee) {

        try(PreparedStatement statement = connection.prepareStatement("insert into employee values(NULL,?,?,?,?)"
                ,Statement.RETURN_GENERATED_KEYS )){

            statement.setString(1,employee.getFirstName());
            statement.setString(2,employee.getFirstName());
            statement.setString(3,employee.getGender().toString());
            statement.setDouble(4,employee.getSalary());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                employee.setId(generatedKeys.getLong(1));

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }


    }
}
