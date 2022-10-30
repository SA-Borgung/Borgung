package ku.cs.models;

import java.util.ArrayList;

public class EmployeeList {

    private ArrayList<Employee> employees;

    public EmployeeList() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){employees.add(employee);}

    public ArrayList<Employee> getEmployees(){return employees;}

    public int count() {return employees.size();}

    public Employee getEmployeeNumber(int num){
        Employee employee = employees.get(num);
        return employee;
    }

}
