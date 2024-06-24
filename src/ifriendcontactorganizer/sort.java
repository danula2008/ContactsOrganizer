package ifriendcontactorganizer;

public class sort {

    // Method to sort the names array
    public static int[] sort_name_ar(contactList objContact) {

        String[] names = objContact.getNameAr();
        int[] sortedIndex = new int[objContact.getSize()];

        // Using bubble sort to sort the names
        for (int k = 0; k < names.length - 1; k++) {
            for (int i = 0; i < names.length - 1 - k; i++) {

                // If the first name is lower than second name(using first letter) swap the two
                if (names[i].charAt(0) > names[i + 1].charAt(0)) {
                    String temp = names[i];
                    names[i] = names[i + 1];
                    names[i + 1] = temp;

                } else if (names[i].charAt(0) == names[i + 1].charAt(0)) {

                    // If the two words first letters same loop till there is a different letters
                    int j = 0;
                    while (j < names[i].length() && j < names[i + 1].length() && names[i].charAt(j) == names[i + 1].charAt(j)) {
                        j++;
                    }

                    // If the first name is lower than second name swap the two
                    if (j < names[i].length() && j < names[i + 1].length() && names[i].charAt(j) > names[i + 1].charAt(j)) {
                        String temp = names[i];
                        names[i] = names[i + 1];
                        names[i + 1] = temp;
                    }
                }
            }
        }
        
        // Getting the indexes of the ordered names using a loop
        for (int i = 0; i < sortedIndex.length; i++) {
            ordering:
            for (int j = 0; j < sortedIndex.length; j++) {
                if (objContact.getName(j).equals(names[i])) {
                    for (int k = 0; k < i; k++) {

                        // If the index is there in the new index array skip that indes
                        if (sortedIndex[k] == j) {
                            continue ordering;
                        }
                    }
                    // Adding the ordered indexes to a list
                    sortedIndex[i] = j;
                    break;
                }
            }
        }
        
        // returning the list of sorted names indexes
        return sortedIndex;
    }

    // Method to sort the bday array
    public static int[] sort_date_ar(contactList objContact) {

        Date[] dates = objContact.getBdayAr();
        int[] sortedIndex = new int[objContact.getSize()];

        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < dates.length - 1; i++) {

                // Extractng the day, month, year of the first date
                int bdayYear = dates[i].getYear();
                int bdayMonth = dates[i].getMonth();
                int bdayDate = dates[i].getDay();

                // Extractng the day, month, year of the second date
                int nextbdayYear = dates[i + 1].getYear();
                int nextbdayMonth = dates[i + 1].getMonth();
                int nextbdayDate = dates[i + 1].getDay();

                // Swap if fist date is before second date 
                if (bdayYear > nextbdayYear || (bdayYear == nextbdayYear && bdayMonth > nextbdayMonth) || (bdayYear == nextbdayYear && bdayMonth == nextbdayMonth && bdayDate > nextbdayDate)) {
                    Date temp = dates[i];
                    dates[i] = dates[i + 1];
                    dates[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        
        // Getting the indexes of the ordered bithdays using a loop
        for (int i = 0; i < sortedIndex.length; i++) {
            ordering:
            for (int j = 0; j < sortedIndex.length; j++) {
                if (objContact.getBday(j).equals(dates[i])) {
                    for (int k = 0; k < i; k++) {

                        // If the index is there in the new index array skip that indes
                        if (sortedIndex[k] == j) {
                            continue ordering;
                        }
                    }
                    // Adding the ordered indexes to a list
                    sortedIndex[i] = j;
                    break;
                }
            }
        }
        
        // returning the list of sorted names indexes
        return sortedIndex;
    }

    // Method to sort the bday array
    public static int[] sort_salary_ar(contactList objContact) {

        double[] salary = objContact.getSalaryAr();
        int[] sortedIndex = new int[objContact.getSize()];

        // Using insertion sort for sorting Salary
        for (int i = 1; i < salary.length; i++) {
            double temp;
            for (int j = 0; j < i; j++) {
                if (salary[j] > salary[i]) {
                    temp = salary[j];
                    salary[j] = salary[i];
                    salary[i] = temp;
                }
            }
        }
        
        // Getting the indexes of the ordered salaries using a loop
        for (int i = 0; i < sortedIndex.length; i++) {
            ordering:
            for (int j = 0; j < sortedIndex.length; j++) {
                if (objContact.getSalary(j) == salary[i]) {
                    for (int k = 0; k < i; k++) {

                        // If the index is there in the new index array skip that indes
                        if (sortedIndex[k] == j) {
                            continue ordering;
                        }
                    }
                    // Adding the ordered indexes to a list
                    sortedIndex[i] = j;
                    break;
                }
            }
        }
        
        // returning the list of sorted names indexes
        return sortedIndex;
    }

}
