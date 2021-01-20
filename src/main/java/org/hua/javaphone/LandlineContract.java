package org.hua.javaphone;

import java.util.ArrayList;
import java.util.Scanner;

public class LandlineContract extends Contract {

    public LandlineContract(ArrayList<Contract> contractArrayList, ArrayList<User> userArrayList) {
        super(contractArrayList, userArrayList);
    }

    /**
     * creates a new landline contract for the user and adds it to the contract array list
     * @param tmpContract is used to store the contract info temporarily
     */
    protected Contract createLandlineContract(Contract tmpContract) {
        Scanner input = new Scanner(System.in);

        //get phone number
        System.out.println("Please enter your phone number: ");
        while (true) {
            if (input.hasNextLine()) {
                String phoneNumber = input.nextLine();
                if (checkNumber(phoneNumber)) {
                    tmpContract.setPhoneNumber(phoneNumber);
                    break;
                }
                System.out.println("Phone number must start with 2");
            }
            System.out.println("Wrong input!");
        }

        //if user wants internet get desired speed
        System.out.println("Would you like to have internet? \n1. Yes \n2. No");
        while (true) {
            if (input.hasNextInt()) {
                int tmp = input.nextInt();
                if (tmp == 1) {
                    System.out.println("What is your preferred internet speed? \n1. ADSL \n2. VDSL");
                    if (input.hasNextInt()) {
                        int internetSpeed = input.nextInt();
                        if (internetSpeed == 1 || internetSpeed == 2) {
                            tmpContract.setInternetSpeed(internetSpeed);
                            //set monthly cost depending on the type of internet speed
                            if (internetSpeed == 1) {
                                //ADSL -> 50
                                tmpContract.setMonthlyCost(tmpContract.getMonthlyCost() + 50);
                            } else {
                                //VDSL -> 100
                                tmpContract.setMonthlyCost(tmpContract.getMonthlyCost() + 100);
                            }
                            break;
                        }
                        System.out.println("Please enter a corresponding number");
                    }
                    System.out.println("Wrong input! Please enter a corresponding number");
                } else if (tmp == 2) {
                    break;
                }
                System.out.println("Wrong input! Please enter a corresponding number");
            }
            System.out.println("Wrong input! Please enter a corresponding number");
        }

        return tmpContract;
    }

}
