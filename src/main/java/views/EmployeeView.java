package views;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.Employee;

import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeView {

    private List<Employee> employees;

    public void showEmployees(){
        employees.forEach(e->{
            System.out.println(e.getId()+" "+e.getFirstName()+" "+e.getLastName()+" "+e.getGender()+" "+e.getSalary());
        });
    }

}
