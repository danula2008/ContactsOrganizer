package ifriendcontactorganizer;

// Importing required libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Contacts_Interface extends JFrame {

    // Creating required private static variables
    private static contactList contactDB = new contactList();
    private static JPanel right_panel;
    private static JPanel left_panel;

    private static Contacts_Interface instance;

    // Creating the main colors in the interface as variables
    private static Color action_color = new Color(56, 56, 56, 255);
    private static Color bg_color = new Color(44, 44, 44, 255);
    private static Color border_color = new Color(110, 110, 110, 255);

    // Class to create the left panel
    class left {

        // Delcare private static variables
        private static int sortingIndex = 0;
        private static String searchQ = "";
        private static String[] dropdownOptions = {"None", "Name", "Salary", "Birthday"};

        // Method to set the sortingIndexVariable while updating the left panel
        private static void setsortingIndex(int val) {
            sortingIndex = val;
            instance.updateLeftPanel();
        }

        // Method to get th labels
        private static JPanel getLabels(int index, String Customername, String Customercontact) {

            // Creating the unique panel for each contact and styling it with a background color, border
            JPanel contact_panel = new JPanel(new GridLayout(3, 1));
            contact_panel.setBackground(new Color(44, 44, 44));
            contact_panel.setBorder(BorderFactory.createEmptyBorder(10, 0, -27, 0));

            // Creating the name label and styling it with border, font, forground color
            JLabel nameOfContact = new JLabel((Customername.length() > 21) ? Customername.substring(0, 18) + "..." : Customername);
            nameOfContact.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
            nameOfContact.setForeground(Color.WHITE);
            nameOfContact.setFont(new Font("Poppins", Font.BOLD, 16));

            // Adding the name label to the contact panel
            contact_panel.add(nameOfContact);

            // Creating the phone number label and styling it with border, font, forground color
            JLabel phoneNoOfContact = new JLabel(Customercontact);
            phoneNoOfContact.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 0));
            phoneNoOfContact.setForeground(Color.WHITE);
            phoneNoOfContact.setFont(new Font("Poppins", Font.PLAIN, 12));

            // Adding the phone number label to the contact panel
            contact_panel.add(phoneNoOfContact);

            // Adding a separator line at the end to divide each contact panel
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setPreferredSize(new Dimension(0, 1));

            // Adding the separator label to the contact panel
            contact_panel.add(separator);

            // Adding actions for contacts panel
            contact_panel.addMouseListener(new MouseAdapter() {

                // Panel Clicked
                public void mouseClicked(MouseEvent e) {
                    right.setIndex(index);
                }

                // Mouse over panel
                public void mouseEntered(MouseEvent e) {
                    contact_panel.setBackground(Color.DARK_GRAY);
                }

                // Mouse off panel
                public void mouseExited(MouseEvent e) {
                    contact_panel.setBackground(new Color(44, 44, 44));
                    nameOfContact.setForeground(Color.WHITE);
                    phoneNoOfContact.setForeground(Color.WHITE);
                }
            });

            // Returning the built contact panel
            return contact_panel;
        }

        // Method main method to get the left panel
        private static JPanel getleftPanel() {

            // Creating the main panel of the left panel
            JPanel left_panel = new JPanel(new BorderLayout());

            // Creating the top layout of the left panel
            JPanel topLayout = new JPanel(new GridLayout(2, 1));

            // Creating the search bar and adding a backgrounf color
            JPanel search_panel = new JPanel(new FlowLayout());
            search_panel.setBackground(bg_color);

            // Creating the search bar to get the text and adding a background forground color, border, font
            JTextField search_bar = new JTextField(searchQ, 10);
            search_bar.setBackground(action_color);
            search_bar.setForeground(Color.WHITE);
            search_bar.setBorder(BorderFactory.createLineBorder(border_color));
            search_bar.setFont(new Font("Popins", 1, 16));

            // Adding the search bar to the search panel
            search_panel.add(search_bar);

            // Creating the search button and adding a background forground color
            JButton btn_search = new JButton(sortingIndex != 5 ? "Search" : "Cancel");
            btn_search.setBackground(action_color);
            btn_search.setForeground(Color.WHITE);

            // Adding the search button to the search panel
            search_panel.add(btn_search);

            // Adding action for the search button
            btn_search.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    // If the search box is not active
                    if (sortingIndex != 5) {
                        searchQ = search_bar.getText();
                        setsortingIndex(5);
                    } else {
                        // If the search box is active
                        searchQ = "";
                        setsortingIndex(0);
                    }
                }
            });

            // Adding the search panel to the top layout
            topLayout.add(search_panel);

            // Creating a panel with a background color to add text
            JPanel data_bar = new JPanel(new FlowLayout());
            data_bar.setBackground(bg_color);

            // Creating the text dynamically and adding the text to a label and adding the label to the data bar panel
            String txt = sortingIndex == 0 ? "All Contacts (" + contactDB.getSize() + ")" : sortingIndex == 1 ? "Sorted by Name (A to Z)" : sortingIndex == 2 ? "Sorted by Salary (Low to High)" : sortingIndex == 5 ? "Search results for " + searchQ : "Sorted by Birthday (Old to Young)";
            JLabel lbl_txt = new JLabel(txt);
            lbl_txt.setForeground(Color.WHITE);
            data_bar.add(lbl_txt);

            // Adding thr data bar panel to the top layout
            topLayout.add(data_bar);

            // Adding the top layout to the north of the left panel
            left_panel.add("North", topLayout);

            // Creating the menu of the panel to hold the contacts and addong a background
            JPanel main_panel = new JPanel(new GridLayout(contactDB.getSize() < 6 ? 6 : 0, 1));
            main_panel.setBackground(bg_color);

            // If the sorting is none
            if (sortingIndex == 0) {

                // If there is no contact to show
                if (contactDB.getSize() == 0) {
                    JLabel label = new JLabel("No contacts found.");

                    label.setBackground(bg_color);
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("", Font.BOLD, 14));
                    label.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));

                    main_panel.add(label);
                } else {

                    // If not null showing all the contacts in the database
                    for (int i = 0; i < contactDB.getSize(); i++) {
                        main_panel.add(getLabels(i, contactDB.getNameAr()[i], contactDB.getPhoneNoAr()[i]));
                    }
                }
            } else {

                // Getting the indexes of sorted contacts into a array
                int[] sortedAr = sortingIndex == 1 ? sort.sort_name_ar(contactDB) : sortingIndex == 2 ? sort.sort_salary_ar(contactDB) : sortingIndex == 5 ? searching.search(searchQ, contactDB) : sort.sort_date_ar(contactDB);

                // Creating a panel with GridLayout and puting number of rows accordingly to get the contacts flowing from top
                main_panel = new JPanel(new GridLayout(sortedAr.length < 6 ? 6 : 0, 1));
                main_panel.setBackground(bg_color);

                // If there is no contacts after sorting
                if (sortedAr.length == 0) {
                    JLabel label = new JLabel("No contacts found.");

                    label.setBackground(bg_color);
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("", Font.BOLD, 14));
                    label.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));

                    main_panel.add(label);
                } else {
                    // If there are contacts using a loop adding the contacts to main panel
                    for (int i : sortedAr) {
                        main_panel.add(getLabels(i, contactDB.getNameAr()[i], contactDB.getPhoneNoAr()[i]));
                    }
                }
            }

            // Adding a scroll layout adding the created main panel and not allowing to scroll horizontally
            JScrollPane scroll_layout = new JScrollPane(main_panel);
            scroll_layout.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            // Styling the scroll bar 
            JScrollBar ScrollBar = new JScrollBar();
            ScrollBar.setBackground(bg_color);
            ScrollBar.setBorder(BorderFactory.createLineBorder(bg_color));
            scroll_layout.setVerticalScrollBar(ScrollBar);

            // Creating drop down menu of sorting            
            JComboBox dropdown = new JComboBox(dropdownOptions);
            dropdown.setFont(new Font("Popins", Font.PLAIN, 14));
            dropdown.setBackground(bg_color);
            dropdown.setForeground(Color.WHITE);

            dropdown.addActionListener(new ActionListener() {

                // When on of the options are selected
                public void actionPerformed(ActionEvent e) {

                    String selectedVal = (String) dropdown.getSelectedItem();
                    sortingIndex = selectedVal == "None" ? 0 : selectedVal == "Name" ? 1 : selectedVal == "Salary" ? 2 : 3;

                    // Swapping the front text with selected text
                    String selected = dropdownOptions[sortingIndex];
                    dropdownOptions[sortingIndex] = dropdownOptions[0];
                    dropdownOptions[0] = selected;

                    setsortingIndex(sortingIndex);
                }
            });

            // Creating the bottom panel and setting the background
            JPanel bottom_panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 5));
            bottom_panel.setBackground(bg_color);

            // Adding a Add button to add new contact
            JButton addbtn = new JButton("Add Contact");
            addbtn.setBackground(action_color);
            addbtn.setForeground(Color.WHITE);

            // Adding text as label
            JLabel lbl_orderBy = new JLabel("Order by : ");
            lbl_orderBy.setForeground(Color.WHITE);

            // Adding all elements to the bottom panel
            bottom_panel.add(lbl_orderBy);
            bottom_panel.add(dropdown);
            bottom_panel.add(addbtn);

            addbtn.addActionListener(new ActionListener() {

                // When button is pressed
                public void actionPerformed(ActionEvent evt
                ) {
                    right.addContact();
                }
            }
            );

            // Adding the panels to the left panel
            left_panel.add("Center", scroll_layout);
            left_panel.add("South", bottom_panel);

            // Returning the left panel
            return left_panel;
        }
    }

    class right {

        // Defineing the private static variables
        private static int index = -1;
        private static boolean enableEdit = false;
        private static boolean add = false;

        private static JTextField lblName_edit;
        private static JTextField lblContact_edit;
        private static JTextField lblCompany_edit;
        private static JTextField lblSalary_edit;
        private static JTextField lblBday_edit;

        // Method to set index
        public static void setIndex(int i) {
            index = i;
            enableEdit = false;
            add = false;
            instance.updateRightPanel();
        }

        // Method to enable add contact
        public static void addContact() {
            enableEdit = false;
            add = true;
            index = -2;
            instance.updateRightPanel();
        }

        // Method to enable edit
        public static void setenableEdit(boolean val) {
            enableEdit = val;
            add = false;
            instance.updateRightPanel();
        }

        // MEthod to create add contact interface
        public static JPanel addContact_Interface(JPanel labelPanel, JPanel RightDataPanel) {

            // Creating the label of Id txt and styling it
            JLabel lblId = new JLabel("Id       ");
            lblId.setFont(new Font("", Font.PLAIN, 16));
            lblId.setBackground(bg_color);
            lblId.setForeground(Color.WHITE);
            labelPanel.add(lblId);

            // Adding the value as a label of Id and styling it
            JLabel lblId_val = new JLabel(":    " + (contactDB.getSize() + 1));
            lblId_val.setBackground(bg_color);
            lblId_val.setForeground(Color.WHITE);
            lblId_val.setFont(new Font("", Font.PLAIN, 16));
            labelPanel.add(lblId_val);

            // Creating the label of name txt and styling it
            JLabel lblName = new JLabel("Name       ");
            lblName.setBackground(bg_color);
            lblName.setForeground(Color.WHITE);
            lblName.setFont(new Font("", Font.BOLD, 16));
            labelPanel.add(lblName);

            // Creating the text field of name and styling it
            lblName_edit = new JTextField();
            lblName_edit.setFont(new Font("", Font.BOLD, 16));
            lblName_edit.setBackground(action_color);
            lblName_edit.setForeground(Color.WHITE);
            labelPanel.add(lblName_edit);

            // Creating the label of phone number txt and styling it
            JLabel lblContact = new JLabel("Phone Number       ");
            lblContact.setBackground(bg_color);
            lblContact.setForeground(Color.WHITE);
            lblContact.setFont(new Font("", Font.BOLD, 16));
            labelPanel.add(lblContact);

            // Creating the text field of phone number and styling it
            lblContact_edit = new JTextField();
            lblContact_edit.setFont(new Font("", Font.BOLD, 16));
            lblContact_edit.setBackground(action_color);
            lblContact_edit.setForeground(Color.WHITE);
            labelPanel.add(lblContact_edit);

            // Creating the label of company name txt and styling it
            JLabel lblCompany = new JLabel("Company       ");
            lblCompany.setFont(new Font("", Font.PLAIN, 16));
            lblCompany.setBackground(bg_color);
            lblCompany.setForeground(Color.WHITE);
            labelPanel.add(lblCompany);

            // Creating the text field of company name and styling it
            lblCompany_edit = new JTextField();
            lblCompany_edit.setFont(new Font("", Font.PLAIN, 16));
            lblCompany_edit.setBackground(action_color);
            lblCompany_edit.setForeground(Color.WHITE);
            labelPanel.add(lblCompany_edit);

            // Creating the label of salary txt and styling it
            JLabel lblSalary = new JLabel("Salary       ");
            lblSalary.setBackground(bg_color);
            lblSalary.setForeground(Color.WHITE);
            lblSalary.setFont(new Font("", Font.PLAIN, 16));
            labelPanel.add(lblSalary);

            // Creating the text field of salary and styling it
            lblSalary_edit = new JTextField();
            lblSalary_edit.setFont(new Font("", Font.PLAIN, 16));
            lblSalary_edit.setBackground(action_color);
            lblSalary_edit.setForeground(Color.WHITE);
            labelPanel.add(lblSalary_edit);

            // Creating the label of birthday txt and styling it
            JLabel lblBday = new JLabel("Birthday       ");
            lblBday.setBackground(bg_color);
            lblBday.setForeground(Color.WHITE);
            lblBday.setFont(new Font("", Font.PLAIN, 16));
            labelPanel.add(lblBday);

            // Creating the text field of birthday and styling it
            lblBday_edit = new JTextField();
            lblBday_edit.setFont(new Font("", Font.PLAIN, 16));
            lblBday_edit.setBackground(action_color);
            lblBday_edit.setForeground(Color.WHITE);
            labelPanel.add(lblBday_edit);

            // adding a border to the label panel
            labelPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 70));

            // Creating a panel to add buttons
            JPanel btnPanel = new JPanel(new GridLayout(1, 3, 20, 0));
            btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 80, 70));
            btnPanel.setBackground(bg_color);

            // Creating a Done button and styling it
            JButton edit_btn = new JButton("Done");
            edit_btn.setBackground(action_color);
            edit_btn.setForeground(Color.WHITE);

            edit_btn.addActionListener(new ActionListener() {
                // When button is pressed
                public void actionPerformed(ActionEvent evt) {

                    // Taking the values of text fields
                    String name = lblName_edit.getText();
                    String phoneNo = lblContact_edit.getText();
                    String compName = lblCompany_edit.getText();

                    String msg = "";

                    // Validating inputs and shoing error dialog box if needed
                    // Validating salary
                    if (!validations.isValidSalary(lblSalary_edit.getText())) {
                        msg += "Invalidate Salary.\n";
                    }
                    // Validating birthday
                    if (!validations.isValidDate(lblBday_edit.getText())) {
                        msg += "Invalidate Birthday.\n";
                    }
                    // Validating phone number
                    int p_val = validations.isValidPhone(phoneNo, contactDB);
                    if (p_val != -3) {
                        switch (p_val) {
                            case -1:
                                msg += "Phone number does not have 10 characters or starts with 0.\n";
                                break;
                            case -2:
                                msg += "Phone number has a character others than digits.\n";
                                break;
                            default:
                                // Showing the saved contact if contact exists
                                JOptionPane.showMessageDialog(null, "Phone number exists.", "Phone number exists", JOptionPane.ERROR_MESSAGE);
                                setIndex(p_val);
                                return;
                        }
                    }

                    if (msg != "") {
                        // if there is errors showing a diablog box
                        JOptionPane.showMessageDialog(null, msg, "Invalid Input(s)", JOptionPane.ERROR_MESSAGE);
                    } else {

                        // If no errors after validation adding the new contact to the database
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this contact", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION) {
                            double salary = Double.parseDouble(lblSalary_edit.getText());
                            Date bday = new Date(lblBday_edit.getText());

                            contactDB.Addcontact(name, phoneNo, compName, salary, bday);
                            instance.updateLeftPanel();
                            setIndex(contactDB.getSize() - 1);
                        } else {
                            setIndex(-1);
                        }

                    }
                }
            });
            // Adding the button to the button pannel
            btnPanel.add(edit_btn);

            // Adding close button and styling it
            JButton close_btn = new JButton("Close");
            close_btn.setBackground(action_color);
            close_btn.setForeground(Color.WHITE);

            close_btn.addActionListener(new ActionListener() {
                // If button pressed
                public void actionPerformed(ActionEvent evt) {
                    setIndex(-1);
                }
            });
            // Adding close button to button panel
            btnPanel.add(close_btn);

            // Adding the panels to the right panel
            RightDataPanel.add("South", btnPanel);
            RightDataPanel.add("Center", labelPanel);

            //returning the created right panel
            return RightDataPanel;
        }

        // Method to get the right panel
        public static JPanel getPanel() {
            // Creating the right pannel and adding styles
            JPanel rightMainPanel = new JPanel(new BorderLayout());
            rightMainPanel.setBackground(bg_color);
            rightMainPanel.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
            rightMainPanel.setPreferredSize(new Dimension(503, 0));

            if (index == -1) {
                // If no contact is selected
                JLabel label = new JLabel("Click a contact to see details");

                label.setBackground(bg_color);
                label.setForeground(Color.WHITE);
                label.setFont(new Font("", Font.BOLD, 16));
                label.setBorder(BorderFactory.createEmptyBorder(0, 90, 0, 90));

                rightMainPanel.add(label);
                return rightMainPanel;
            }

            //If cntact is selected
            // Create label panel and style it
            JPanel labelPanel = new JPanel(new GridLayout(7, 3));
            labelPanel.setBackground(bg_color);
            labelPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 90));

            if (add) {
                // If enable adding is selected create the interface using the method and return it
                rightMainPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
                return addContact_Interface(labelPanel, rightMainPanel);
            }

            // Creating the label of id txt and styling it
            JLabel lblId = new JLabel("Id");
            lblId.setFont(new Font("", Font.PLAIN, 16));
            lblId.setBackground(bg_color);
            lblId.setForeground(Color.WHITE);
            labelPanel.add(lblId);

            // Creating the label of id value and styling it
            JLabel lblId_val = new JLabel(":    " + (index + 1));
            lblId_val.setFont(new Font("", Font.PLAIN, 16));
            lblId_val.setBackground(bg_color);
            lblId_val.setForeground(Color.WHITE);
            labelPanel.add(lblId_val);

            // Creating the label of name txt and styling it
            JLabel lblName = new JLabel("Name");
            lblName.setFont(new Font("", Font.BOLD, 16));
            lblName.setBackground(bg_color);
            lblName.setForeground(Color.WHITE);
            labelPanel.add(lblName);

            if (enableEdit) {
                // Creating the text field of name and styling it
                lblName_edit = new JTextField(contactDB.getName(index));
                lblName_edit.setFont(new Font("", Font.BOLD, 16));
                lblName_edit.setBackground(action_color);
                lblName_edit.setForeground(Color.WHITE);
                labelPanel.add(lblName_edit);
            } else {
                // Creating the label of name value and styling it
                JLabel lblName_val = new JLabel(":    " + contactDB.getName(index));
                lblName_val.setFont(new Font("", Font.BOLD, 16));
                lblName_val.setBackground(bg_color);
                lblName_val.setForeground(Color.WHITE);
                labelPanel.add(lblName_val);
            }

            // Creating the label of phone number txt and styling it
            JLabel lblContact = new JLabel("Phone Number");
            lblContact.setFont(new Font("", Font.BOLD, 16));
            lblContact.setBackground(bg_color);
            lblContact.setForeground(Color.WHITE);
            labelPanel.add(lblContact);

            if (enableEdit) {
                // Creating the text field of phone number and styling it
                lblContact_edit = new JTextField(contactDB.getPhoneNo(index));
                lblContact_edit.setFont(new Font("", Font.BOLD, 16));
                lblContact_edit.setBackground(action_color);
                lblContact_edit.setForeground(Color.WHITE);
                labelPanel.add(lblContact_edit);
            } else {
                // Creating the label of phone number value and styling it
                JLabel lblContact_val = new JLabel(":    " + contactDB.getPhoneNo(index));
                lblContact_val.setFont(new Font("", Font.BOLD, 16));
                lblContact_val.setBackground(bg_color);
                lblContact_val.setForeground(Color.WHITE);
                labelPanel.add(lblContact_val);
            }

            // Creating the label of company name txt and styling it
            JLabel lblCompany = new JLabel("Company");
            lblCompany.setFont(new Font("", Font.PLAIN, 16));
            lblCompany.setBackground(bg_color);
            lblCompany.setForeground(Color.WHITE);
            labelPanel.add(lblCompany);

            if (enableEdit) {
                // Creating the text field of company name and styling it
                lblCompany_edit = new JTextField(contactDB.getCompName(index));
                lblCompany_edit.setFont(new Font("", Font.PLAIN, 16));
                lblCompany_edit.setBackground(action_color);
                lblCompany_edit.setForeground(Color.WHITE);
                labelPanel.add(lblCompany_edit);
            } else {
                // Creating the label of company name value and styling it
                JLabel lblCompany_val = new JLabel(":    " + contactDB.getCompName(index));
                lblCompany_val.setFont(new Font("", Font.PLAIN, 16));
                lblCompany_val.setBackground(bg_color);
                lblCompany_val.setForeground(Color.WHITE);
                labelPanel.add(lblCompany_val);
            }

            // Creating the label of salary txt and styling it
            JLabel lblSalary = new JLabel("Salary");
            lblSalary.setFont(new Font("", Font.PLAIN, 16));
            lblSalary.setBackground(bg_color);
            lblSalary.setForeground(Color.WHITE);
            labelPanel.add(lblSalary);

            if (enableEdit) {
                // Creating the text field of salary and styling it
                lblSalary_edit = new JTextField("" + contactDB.getSalary(index));
                lblSalary_edit.setFont(new Font("", Font.PLAIN, 16));
                lblSalary_edit.setBackground(action_color);
                lblSalary_edit.setForeground(Color.WHITE);
                labelPanel.add(lblSalary_edit);
            } else {
                // Creating the label of salary value and styling it
                JLabel lblSalary_val = new JLabel(":    " + contactDB.getSalary(index));
                lblSalary_val.setFont(new Font("", Font.PLAIN, 16));
                lblSalary_val.setBackground(bg_color);
                lblSalary_val.setForeground(Color.WHITE);
                labelPanel.add(lblSalary_val);
            }

            // Creating the label of birthday txt and styling it
            JLabel lblBday = new JLabel("Birthday");
            lblBday.setFont(new Font("", Font.PLAIN, 16));
            lblBday.setBackground(bg_color);
            lblBday.setForeground(Color.WHITE);
            labelPanel.add(lblBday);

            // Creating the label of birthday txt and styling it
            JLabel lblBday_val = new JLabel(":    " + contactDB.getBday(index).getDate());
            lblBday_val.setFont(new Font("", Font.PLAIN, 16));
            lblBday_val.setBackground(bg_color);
            lblBday_val.setForeground(Color.WHITE);
            labelPanel.add(lblBday_val);

            // Adding a button panel and styling it
            JPanel btnPanel = new JPanel(new GridLayout(1, 3, 20, 0));
            btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 80, 90));
            btnPanel.setBackground(bg_color);

            // Create edit button and done button
            JButton edit_btn = new JButton(enableEdit ? "Done" : "Edit");
            edit_btn.setBackground(action_color);
            edit_btn.setForeground(Color.WHITE);
            edit_btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    // When button is pressed
                    if (enableEdit) {

                        // when text in buttton is Done
                        // Getting values from text box
                        String name = lblName_edit.getText();
                        String phoneNo = lblContact_edit.getText();
                        String compName = lblCompany_edit.getText();

                        String msg = "";

                        // Valudating salary
                        if (!validations.isValidSalary(lblSalary_edit.getText())) {
                            msg += "Invalidate Salary.\n";
                        }
                        // Validation phone number
                        int p_val = validations.isValidPhone(phoneNo, contactDB);
                        if (p_val != -3) {
                            switch (p_val) {
                                case -1:
                                    msg += "Phone number does not have 10 characters or starts with 0.\n";
                                    break;
                                case -2:
                                    msg += "Phone number has a character others than digits.\n";
                                    break;
                                default:
                                    // If number is not same from before edit
                                    if (!contactDB.getPhoneNo(index).equals(phoneNo)) {
                                        // If phone number exists show the detals 
                                        JOptionPane.showMessageDialog(null, "Phone number exists.", "Phone number exists", JOptionPane.ERROR_MESSAGE);
                                        setIndex(p_val);
                                        return;
                                    }
                            }
                        }

                        if (msg != "") {
                            // Show error msgs if there are errors
                            JOptionPane.showMessageDialog(null, msg, "Invalid Input(s)", JOptionPane.ERROR_MESSAGE);
                        } else {

                            // If no errors show confirm dialog
                            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to make change in this contact", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                            if (response == JOptionPane.YES_OPTION) {

                                // If confirmed make the changes
                                double salary = Double.parseDouble(lblSalary_edit.getText());

                                contactDB.setName(index, name);
                                contactDB.setPhoneNo(index, phoneNo);
                                contactDB.setCompName(index, compName);
                                contactDB.setSalary(index, salary);

                                instance.updateLeftPanel();
                                setIndex(index);
                            } else {
                                setIndex(-1);
                            }

                        }
                    } else {
                        setenableEdit(true);
                    }
                }
            });
            btnPanel.add(edit_btn);

            // Create delete button with styles
            JButton delete_btn = new JButton("Delete");
            delete_btn.setBackground(action_color);
            delete_btn.setForeground(Color.WHITE);

            delete_btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    // When button is pressed after confirmation delete the contact
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + contactDB.getName(index), "Confermation", JOptionPane.INFORMATION_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                        contactDB.Drop(index);

                        instance.updateLeftPanel();
                        right.setIndex(-1);
                        instance.updateRightPanel();

                    }

                }
            });

            // add the Delete button
            btnPanel.add(delete_btn);

            // Create close button with styles
            JButton close_btn = new JButton("Close");
            close_btn.setBackground(action_color);
            close_btn.setForeground(Color.WHITE);

            close_btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (enableEdit) {
                        // If cliked when editing close the editing option 
                        setIndex(index);
                    } else {
                        // Else close the right panel
                        setIndex(-1);
                    }
                }
            });

            // Add the close button to the panel
            btnPanel.add(close_btn);

            // Add the panels to the right panel
            rightMainPanel.add("South", btnPanel);
            rightMainPanel.add("Center", labelPanel);

            // Return the right panel
            return rightMainPanel;
        }
    }

    // Method to update the right panel
    private void updateRightPanel() {
        // Remove all the existing elements in the right panel
        right_panel.removeAll();
        // Create the layout of the container again
        right_panel.revalidate();
        // Schedule a event to do the changes of the updated component
        right_panel.repaint();
        // Add the new elements to the right panel
        right_panel.add("Center", right.getPanel());
    }

    // Method to update the left panel
    private void updateLeftPanel() {
        // Remove all the existing elements in the right panel
        left_panel.removeAll();
        // create the layout of the container again
        left_panel.revalidate();
        // Schedule a event to do the changes of the updated component
        left_panel.repaint();
        // Add the new elements to the left panel
        left_panel.add("Center", left.getleftPanel());
    }

    Contacts_Interface() {
        // Create a instance
        instance = this;

        // Set the values of the window
        setLayout(new BorderLayout());
        setSize(800, 600);
        setTitle("Contacts");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Add sample data in the file
        addSampleData.add(contactDB);

        // Add the left panel to the west
        left_panel = left.getleftPanel();
        add("West", left_panel);

        // Add the right panel to the East
        right_panel = right.getPanel();
        add("East", right_panel);
    }
}
