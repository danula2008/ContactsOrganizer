package ifriendcontactorganizer;

// Class of data type of Date
public class Date {

    // Declare private variables
    private int year;
    private int month;
    private int day;

    // Creating parameterized default constructor
    Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    // Creating parameterized default constructor
    Date(String dateString){
        this.year = Integer.parseInt(dateString.substring(0, 4));
        this.month = Integer.parseInt(dateString.substring(5, 7));
        this.day = Integer.parseInt(dateString.substring(8, 10));
    }

    // creating get methods(getters) for private variables
    // Method to get the year of the date saved
    public int getYear() {
        return year;
    }

    // Method to get the month of the date saved
    public int getMonth() {
        return month;
    }

    // Method to get the date of the date saved
    public int getDay() {
        return day;
    }

    // Method to get the saved date as a String in the format YYYY-MM-DD
    public String getDate() {
        return year + "-" + month + "-" + day;
    }
}
