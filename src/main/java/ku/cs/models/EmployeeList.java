package ku.cs.models;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {

    private ArrayList<Employee> employees;

    public EmployeeList() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){employees.add(employee);}

    public ArrayList<Employee> getEmployees(){return employees;}
    public ArrayList<String> specialList = new ArrayList<>();

    public int count() {return employees.size();}

    public Employee getEmployeeNumber(int num){
        Employee employee = employees.get(num);
        return employee;
    }

    public List<String> getAllEmployeeName(){
        for (Employee employee : employees){

            specialList.add(employee.getName());


        }
        return specialList;
    }

    public boolean isCorrectPair(String username, String password){
        for (Employee employee : employees){
            if (employee.getId().equals(username)&&employee.getPassword().equals(password)){
                return true;
            }
        }
        return false;

    }
    public Employee getEmployeeById(String id){
        for (Employee employee : employees) {
            if (employee.checkId(id)) {
                return employee;
            }
        }
        return null;
    }



}
