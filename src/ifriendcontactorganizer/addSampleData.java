package ifriendcontactorganizer;

public class addSampleData {

    public static void add(contactList db) {

        //Adding sample data
        db.Addcontact("Ethan Hall", "0798765432", "TechCorp", 42000.0, new Date("1982-10-15"));
        db.Addcontact("Lily Patel", "0743210987", "Green Energy", 65000.0, new Date("1997-03-01"));
        db.Addcontact("Oliver Brown", "0712345678", "Finance Inc.", 58000.0, new Date("1985-05-20"));
        db.Addcontact("Ava Lee", "0756789023", "Healthcare Solutions", 61000.0, new Date("1999-08-12"));
        db.Addcontact("William Davis", "0723456789", "Software Solutions", 52000.0, new Date("1980-02-05"));
        db.Addcontact("Sophia Martin", "0765432109", "Marketing Agency", 59000.0, new Date("1992-11-25"));
        db.Addcontact("Mia White", "0732109876", "Real Estate", 55000.0, new Date("1987-06-15"));
        db.Addcontact("Alexander Johnson", "0787654321", "IT Consulting", 63000.0, new Date("1995-01-01"));
        db.Addcontact("Isabella Taylor", "0790123456", "Education Services", 50000.0, new Date("1983-09-10"));
        db.Addcontact("Benjamin Clark", "0719876543", "Manufacturing Co.", 60000.0, new Date("1990-04-20"));
    }
}
