package ifriendcontactorganizer;

import java.util.Arrays;

// Class to search data
public class searching {

    public static int[] search(String searchQ, contactList objContact) {
        int[] returnAr = new int[0];

        // Using a loop checking if the words starts with the entered text
        for (int i = 0; i < objContact.getSize(); i++) {

            if (objContact.getName(i).toUpperCase().startsWith(searchQ.toUpperCase()) || objContact.getPhoneNo(i).startsWith(searchQ)) {
                // If match adding it to a array
                returnAr = Arrays.copyOf(returnAr, returnAr.length + 1);
                returnAr[returnAr.length - 1] = i;
            }
        }

        // returning the array with all matched indexes
        return returnAr;
    }
}
