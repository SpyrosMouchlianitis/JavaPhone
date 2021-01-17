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

    public void setUserCode(int code) {
        this.userCode = code;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setAfm(int afm) {
        this.afm = afm;
    }

    public int getAfm() {
        return afm;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    //return true if the afm has 9 digits else return false
    public boolean checkAfm(int afm) {
        int numOfDigits = 1;
        while (afm >= 10) {
            afm /= 10;
            numOfDigits++;
        }
        return numOfDigits == 9;
    }

    //if user is found return index else return -1
    public int findUser(int afm) {
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getAfm() == afm) {
                return i;
            }
        }
        return -1;
    }

    public void createUser(User user) {
        Scanner input = new Scanner(System.in);

        while (true) {
            //get user details
            user.setUserCode(userArrayList.size());
            System.out.println("Are you a 1. student, 2. individual or 3. professional? Please enter the number that corresponds to you: ");
            if (input.hasNextInt()) {
                int userType = input.nextInt();
                if (userType == 1 || userType == 2 || userType == 3) {
                    user.setUserType(userType);
                    break;
                }
                System.out.println("Please enter a corresponding number");
            } else {
                System.out.println("Wrong input");
            }
        }

        input.nextLine(); //used to clear input buffer

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

        System.out.println("Please enter your address");
        user.setAddress(input.next());

        userArrayList.add(user);
    }

    private boolean checkIDNumber(String idNumber) {
        System.out.println(idNumber);
        return idNumber.length() == 9;
    }

    //regex from https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
    private boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public ArrayList<User> getUserArrayList() {
        return this.userArrayList;
    }

}