package ku.cs.models;

import java.util.ArrayList;

public class CustomerList {
    private ArrayList<Customer> customers;

    public CustomerList() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer){customers.add(customer);}

    public ArrayList<Customer> getAllCustomers(){return customers;}

    public int count() {return customers.size();}

    public Customer getCustomerNumber(int num){
        Customer customer = customers.get(num);
        return customer;
    }

}
