package ifriendcontactorganizer;

import java.time.LocalDate;

public class validations {

    // Method to validate phone number    
    public static int isValidPhone(String contact, contactList vContact) {

        // If the input does not have 10 characters or starts with 0, invalid phone number
        if (contact.length() != 10 || !contact.startsWith("0")) {
            return -1; // Returning 1 if input doesnt have 10 characters or not statint with 0
        }

        // If the input has a character others than digits, invalid phone number
        for (int i = 0; i < contact.length(); i++) {
            char c = contact.charAt(i);
            if (!Character.isDigit(c)) {
                return -2; // Returning 2 if all characters are not digits
            }
        }

        // If the input is already saved, invalid phone number
        for (int i = 0; i < vContact.getSize(); i++) {
            if (contact.equals(vContact.getPhoneNo(i))) {
                return i; // Returning index if number exists
            }
        }
        return -3; // Returning -3 if a valid phone number
    }

    public static boolean isValidSalary(String salary) {
        // Check if the salary string is empty or null
        if (salary == null || salary.isEmpty()) {
            return false;
        }

        // Check if all characters in the salary string are digits
        for (int i = 0; i < salary.length(); i++) {
            char c = salary.charAt(i);
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }

        // If all characters are digits, return true
        return true;
    }

    public static boolean isValidDate(String date) {

        LocalDate currentDate = LocalDate.now();
        // Extractng the day, month, year of todays date
        int currentMonthValue = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        int currentMonthDate = currentDate.getDayOfMonth();

        if (date.length() == 10 && date.charAt(4) == '-' && date.charAt(7) == '-') {
            // Extractng the day, month, year of input date
            int bdayYear = Integer.parseInt(date.substring(0, 4));
            int bdayMonth = Integer.parseInt(date.substring(5, 7));
            int bdayDate = Integer.parseInt(date.substring(8, 10));

            // If it is a leap year 29 days in February, if not days mentioned in daysPerMonth array
            if (bdayYear % 4 == 0 && bdayMonth == 2 && bdayDate > 29) {
                return false;
            }
            int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (bdayYear % 4 != 0 && (bdayDate > daysPerMonth[bdayMonth - 1])) {
                return false;
            }

            // If date is before today, Error
            if (bdayYear < currentYear || (bdayYear == currentYear && bdayMonth < currentMonthValue) || (bdayYear == currentYear && bdayMonth == currentMonthValue && bdayDate <= currentMonthDate)) {
                return true;
            }
        }

        // Else valid date
        return false;
    }
}
