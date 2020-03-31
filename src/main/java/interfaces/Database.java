package interfaces;

import utils.Employee;

import java.util.List;

public interface Database {
    void open();
    void close();
    List<Employee> getEmployees();
    void insertEmployee(Employee employee);
}
