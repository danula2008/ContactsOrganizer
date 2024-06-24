package ifriendcontactorganizer;

// Class of contact Data type
public class contactList {

    // Creating the Node of the list
    class Node {

        private contact data;
        private Node next;

        Node(contact data) {
            this.data = data;
        }
    }

    // Setting the start variable of Node type to null
    private Node start;

    // Method to check if list is empty
    public boolean isEmpty() {
        return start == null;
    }

    // Method to add contacts to the list
    public void Addcontact(String name, String phoneNo, String compName, double salary, Date bday) {
        Node n1 = new Node(new contact(name.toUpperCase(), phoneNo, compName.toUpperCase(), salary, bday));
        
        //If the list empty add it to the start
        if (isEmpty()) {
            start = n1;
        } else {
            // If not move to the last element of the list and add the new data there
            Node temp = start;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n1;
        }
    }

    // creating get methods(getters) for private variables
    // Method to get the saved name at index i
    public String getName(int i) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        return temp.data.getName();
    }

    // Method to get the saved phone number at index i
    public String getPhoneNo(int i) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        return temp.data.getPhoneNo();
    }

    // Method to get the saved company name at index i
    public String getCompName(int i) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        return temp.data.getCompName();
    }

    // Method to get the saved salary at index i
    public double getSalary(int i) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        return temp.data.getSalary();
    }

    // Method to get the saved birthday at index i
    public Date getBday(int i) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        return temp.data.getBday();
    }

    // Method to get the size of the list
    public int getSize() {
        Node temp = start;
        int count = 0;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;

    }
    
    // Method to get the names as an array
    public String[] getNameAr() {
        String[] returnAr = new String[getSize()];
        Node temp = start;
        for (int i = 0; i < returnAr.length; i++) {
            returnAr[i] = temp.data.getName();
            temp = temp.next;
        }
        return returnAr;
    }
    
    // Method to get the phone numbers as an array
    public String[] getPhoneNoAr() {
        String[] returnAr = new String[getSize()];
        Node temp = start;
        for (int i = 0; i < returnAr.length; i++) {
            returnAr[i] = temp.data.getPhoneNo();
            temp = temp.next;
        }
        return returnAr;
    }

    // Method to get the salaries as an array
    public double[] getSalaryAr() {
        double[] returnAr = new double[getSize()];
        Node temp = start;
        for (int i = 0; i < returnAr.length; i++) {
            returnAr[i] = temp.data.getSalary();
            temp = temp.next;
        }
        return returnAr;
    }

    // Method to get the birthdays as an array
    public Date[] getBdayAr() {
        Date[] returnAr = new Date[getSize()];
        Node temp = start;
        for (int i = 0; i < returnAr.length; i++) {
            returnAr[i] = temp.data.getBday();
            temp = temp.next;
        }
        return returnAr;
    }

    
    // creating set methods(setters) for private variables
    // Method to set the saved name
    public void setName(int i, String name) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        temp.data.setName(name.toUpperCase());
    }

    // Method to set the saved phone number
    public void setPhoneNo(int i, String phoneNo) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        temp.data.setPhoneNo(phoneNo);
    }

    // Method to set the saved company name
    public void setCompName(int i, String compName) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        temp.data.setCompName(compName.toUpperCase());
    }

    // Method to set the saved salary
    public void setSalary(int i, double salary) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        temp.data.setSalary(salary);
    }

    // Method to set the saved birthday
    public void setBday(int i, Date bday) {
        Node temp = start;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        temp.data.setBday(bday);
    }

    // Method to remove the data at i index
    public void Drop(int i) {
        if (i < 0 || i >= this.getSize()) {
            return;
        }
        if (i == 0) {
            start = start.next;
        } else {
            Node temp = start;
            for (int j = 0; j < i - 1; j++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
    }

}
