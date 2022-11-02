package ku.cs.models;

public class Employee {
    private String id;
    private String name;
    private String dateOfBirth;
    private int sex;
    private String address;
    private String phoneNumber;
    private String startWorkingDate;
    private String password;
    private String role;

    public Employee(String id, String name, String dateOfBirth, int sex, String address, String phoneNumber, String startWorkingDate, String password, String role) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.startWorkingDate = startWorkingDate;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setStartWorkingDate(String startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean checkId(String id) {
        return this.id.equals(id);
    }




}
