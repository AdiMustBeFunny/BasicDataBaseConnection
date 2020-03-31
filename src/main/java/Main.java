import implementations.DatabaseImpl;
import interfaces.Database;
import utils.Employee;
import views.EmployeeView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Database db = new DatabaseImpl();
        db.open();

        db.insertEmployee(createEmployee());

        EmployeeView employeeView = new EmployeeView(db.getEmployees());
        employeeView.showEmployees();

        db.close();
    }

    public static Employee createEmployee(){
        Employee employee = new Employee();
        Scanner input = new Scanner(System.in);

        System.out.println("Name:");
        employee.setFirstName(input.nextLine());

        System.out.println("Last Name:");
        employee.setLastName(input.nextLine());
        System.out.println("Gender:");
        employee.setGender(input.nextLine().charAt(0));
        System.out.println("Salary:");
        employee.setSalary(Double.parseDouble(input.nextLine()));

        return employee;
    }
}
