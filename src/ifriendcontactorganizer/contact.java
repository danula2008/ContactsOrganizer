package ifriendcontactorganizer;

// Class of contact Data type
public class contact {

    // Declare private variables    
    private String name;
    private String phoneNo;
    private String compName;
    private double salary;
    private Date bday;

    // Creating parameterized default constructor
    contact(String name, String phoneNo, String compName, double salary, Date bday) {
        this.name = name.toUpperCase();
        this.phoneNo = phoneNo;
        this.compName = compName.toUpperCase();
        this.salary = salary;
        this.bday = bday;
    }

    // creating get methods(getters) for private variables
    // Method to get the saved name
    String getName() {
        return name;
    }

    // Method to phone number the saved name
    String getPhoneNo() {
        return phoneNo;
    }

    // Method to get the saved company name
    String getCompName() {
        return compName;
    }

    // Method to get the saved salary
    double getSalary() {
        return salary;
    }

    // Method to get the saved birthday
    Date getBday() {
        return bday;
    }
    
    
    // creating set methods(setters) for private variables
    // Method to set the saved name
    void setName(String name) {
        this.name = name;
    }

    // Method to set the saved phone number
    void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    // Method to set the saved company name
    void setCompName(String compName) {
        this.compName = compName;
    }

    // Method to set the saved salary
    void setSalary(double salary) {
        this.salary = salary;
    }

    // Method to set the saved birthday
    void setBday(Date bday) {
        this.bday = bday;
    }
}
