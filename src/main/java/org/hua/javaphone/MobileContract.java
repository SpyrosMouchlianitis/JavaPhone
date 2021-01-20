package org.hua.javaphone;

import java.util.ArrayList;
import java.util.Scanner;

public class MobileContract extends Contract {

    public MobileContract(ArrayList<Contract> contractArrayList, ArrayList<User> userArrayList) {
        super(contractArrayList, userArrayList);
    }

    /**
     * creates mobile contract for the user and adds it to the contract array list
     * @param tmpContract the contract that will be used to store temporarily all the info for the mobile contract
     */
    protected Contract createMobileContract(Contract tmpContract) {
        Scanner input = new Scanner(System.in);

        //get phone number and check if it is valid
        System.out.println("Please enter your phone number: ");
        while (true) {
            if (input.hasNextLine()) {
                String phoneNumber = input.nextLine();
                if (checkMobileNumber(phoneNumber)) {
                    tmpContract.setPhoneNumber(phoneNumber);
                    break;
                }
                System.out.println("Phone number must start with 6");
            }
            System.out.println("Wrong input!");
        }

        tmpContract.setMonthlyCost(100); //standard cost

        //get number of free GBs
        System.out.println("Please enter the number of free GBs that you would like to receive each month: ");
        while (true) {
            if (input.hasNextInt()) {
                int freeGB = input.nextInt();
                if (freeGB >= 0) {
                    tmpContract.setFreeGB(freeGB);
                    break;
                }
                System.out.println("Number of GBs must be zero or more");
            }
            System.out.println("Wrong input! Please enter a number");
        }

        //get number of free SMS
        System.out.println("Please enter the number of free SMS that you would like to receive each month: ");
        while (true) {
            if (input.hasNextInt()) {
                int freeSMS = input.nextInt();
                if (freeSMS >= 0) {
                    tmpContract.setFreeSMS(freeSMS);
                    break;
                }
                System.out.println("Number of SMS must be zero or more");
            }
            System.out.println("Wrong input! Please enter a number");
        }

        return tmpContract;
    }

}
