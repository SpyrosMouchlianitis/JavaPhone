package org.hua.javaphone;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    protected ArrayList<User> userArrayList;
    private int userCode;
    private int afm;
    private String address;
    private String idNumber;
    protected int userType; // 1 = φοιτητης, 2 = ιδιωτης, 3 = επαγγελματιας
    private String email;

    public User(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    /**
     * sets the user's code, which is the userArrayList's size
     * @param code the user's code
     */
    public void setUserCode(int code) {
        this.userCode = code;
    }

    /**
     * gets the user's code
     * @return the user's code
     */
    public int getUserCode() {
        return userCode;
    }

    /**
     * sets the user's afm
     * @param afm the user's afm
     */
    public void setAfm(int afm) {
        this.afm = afm;
    }

    /**
     * gets the user's afm
     * @return the user's afm
     */
    public int getAfm() {
        return afm;
    }

    /**
     * sets the user's address
     * @param address the user's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets the user's address
     * @return the user's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * set's the user's id number
     * @param idNumber the user's id number entered by the user
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * gets the user's id number
     * @return the user's id number
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * sets the user's type
     * 1 -> student
     * 2 -> individual
     * 3 -> professional
     * @param userType integer from 1 to 3 corresponding to the user's type
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * gets the user's type
     * 1 -> student
     * 2 -> individual
     * 3 -> professional
     * @return integer from 1 to 3 corresponding to the user's type
     */
    public int getUserType() {
        return userType;
    }

    /**
     * sets the user's email
     * @param email the user's email entered by the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the user's email
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * check if the afm entered by the user has 9 digits
     * @param afm the afm entered by the user
     * @return true if afm is 9 digits else false
     */
    public boolean checkAfm(int afm) {
        int numOfDigits = 1;
        while (afm >= 10) {
            afm /= 10;
            numOfDigits++;
        }
        return numOfDigits == 9;
    }

    /**
     * find the user by searching with the entered afm in the userArrayList
     * @param afm is the search key
     * @return the index where the afm was found in the array list
     */
    public int findUser(int afm) {
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getAfm() == afm) {
                return i;
            }
        }
        return -1;
    }

    /**
     * create a new user by getting all the necessary information
     * @param user is the user created in main which only has an afm
     */
    public void createUser(User user) {
        Scanner input = new Scanner(System.in);

        while (true) {
            //get user details
            user.setUserCode(userArrayList.size()); //the user code is based on the size of the array list
            System.out.println("Are you a 1. student, 2. individual or 3. professional? Please enter the number that corresponds to you: ");
            if (input.hasNextInt()) {
                int userType = input.nextInt();
                if (userType == 1 || userType == 2 || userType == 3) {
                    user.setUserType(userType); //user type: 1.student, 2.individual, 3.professional
                    break;
                }
                System.out.println("Please enter a corresponding number");
            } else {
                System.out.println("Wrong input");
            }
        }

        input.nextLine(); //used to clear input buffer

        //get user's ID number
        while (true) {
            System.out.println("Please enter your ID number: ");
            String idNumber = input.nextLine();
            if (checkIDNumber(idNumber)) {
                user.setIdNumber(idNumber);
                break;
            } else {
                System.out.println("Wrong Input!");
            }
        }

        //get user's email
        while (true) {
            System.out.println("Please enter your email: ");
            String email = input.next();
            if (checkEmail(email)) {
                user.setEmail(email);
                break;
            } else {
                System.out.println("Wrong Input!");
            }
        }

        //get user's address
        System.out.println("Please enter your address");
        user.setAddress(input.next());

        userArrayList.add(user); //add the user to the array list
    }

    /**
     * checks if the ID entered by the user is 9 characters long
     * @param idNumber the ID entered by the user
     * @return true if the ID is 9 characters long else returns false
     */
    private boolean checkIDNumber(String idNumber) {
        System.out.println(idNumber);
        return idNumber.length() == 9;
    }

    /**
     * checks the email entered by the user
     * @param email is the email entered by the user
     * @return true if email has correct format else returns false
     */
    private boolean checkEmail(String email) {
        //regex from https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
        Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

}
