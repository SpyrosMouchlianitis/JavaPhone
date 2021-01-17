package org.hua.javaphone;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author it21958
 */
public class it21958 {
    public static void main(String []args) {
        Scanner input = new Scanner(System.in);

        //we assume that userArrayList(0) corresponds to contractArrayList(0)

        //create arrayList for the users
        ArrayList<User> userArrayList = new ArrayList<>();
        //create arrayList for the contracts
        ArrayList<Contract> contractArrayList = new ArrayList<>();

        //add template user
        User tmpUser = new User(userArrayList);
        tmpUser.setAfm(123456789);
        tmpUser.setUserCode(userArrayList.size());
        tmpUser.setAddress("Plastira 171");
        tmpUser.setIdNumber("AK 787878");
        tmpUser.setUserType(1);
        userArrayList.add(tmpUser);
        System.out.println("user type: " + userArrayList.get(0).getUserType());

        //add template tmpContract
        Contract tmpContract = new Contract(contractArrayList, userArrayList);
        tmpContract.setPhoneNumber("2102626977");
        tmpContract.setUserAfm(tmpUser.getAfm());
        tmpContract.setContractCode(123);
        tmpContract.setContractType(1);
        tmpContract.setContractDuration(2);
        tmpContract.setInternetSpeed(2);
        tmpContract.setPaymentMethod(1);
        tmpContract.setBillingMethod(2);
        tmpContract.setFreeCallMinutes(1500);
        tmpContract.setStartingDate("12/12/2021");
        tmpContract.setPhoneNumber("2102626977");
        //tmpContract.createTemplateDiscount(0);
        contractArrayList.add(tmpContract);

        tmpUser = new User(userArrayList);
        int afm;
        //get afm and check if it is number and has 9 digits
        while (true) {
            System.out.println("Please enter your AFM: ");
            if (input.hasNextInt()) {
                afm = input.nextInt();
                if (tmpUser.checkAfm(afm)) {
                    //set afm
                    tmpUser.setAfm(afm);
                    break;
                } else {
                    System.out.println("AFM must have 9 digits");
                }
            } else {
                System.out.println("Wrong input! AFM must be a number!");
                input.next();
            }
        }

        //check if user exists. if the user does not exist then create a new user
        int index = tmpUser.findUser(tmpUser.getAfm());
        if (index != -1) {
            //user exists
            tmpUser = userArrayList.get(index);
            System.out.println("Welcome to JavaPhone!");
        } else {
            //user doesn't exist
            tmpUser.createUser(tmpUser);
            System.out.println("New user created");
            index = userArrayList.size() - 1;
            System.out.println("Welcome to JavaPhone!");
        }

        while (true) {

            System.out.println();

            printMenu();

            switch (input.nextInt()){
                case 1:
                    tmpContract = new Contract(contractArrayList, userArrayList);
                    tmpContract.setUserAfm(tmpUser.getAfm()); //set the afm of the current user to correspond to the contract
                    tmpContract.createContract(tmpContract);
                    //find and print the newest contract
                    int newestContractIndex = 0;
                    for (int i = 0; i < contractArrayList.size(); i++) {
                        if (contractArrayList.get(i).getUserAfm() == tmpUser.getAfm()) {
                            newestContractIndex = i;
                        }
                    }
                    System.out.println("index: " + newestContractIndex);
                    contractArrayList.get(index).printContract(newestContractIndex, index);
                    //TODO discount
                    //base cost is 100 if VDSL +100 if ADSL +50, GBs, SMS, CallMinutes are free
                    break;
                case 2:
                    //we already have the user's afm since we checked whether he exists or not
                    //find contracts associated with said afm
                    //let user choose which tmpContract to delete
                    //delete tmpContract
                    tmpContract.setUserAfm(tmpUser.getAfm()); //set the afm that corresponds to the contract
                    tmpContract.deleteContract(); //delete the contract
                    break;
                case 3:
                    tmpContract.printStatistics();
                    break;
                case 4:
                    boolean found = false;
                    for (int i = 0; i < contractArrayList.size(); i++) {
                        if (contractArrayList.get(i).getUserAfm() == userArrayList.get(index).getAfm()) {
                            contractArrayList.get(i).printContract(i, index);
                            found = true;
                        }
                    }
                    if (!found)
                        System.out.println("No contracts were found");

                    break;
                case 5:
                    System.out.println("Thank you for choosing JavaPhone!");
                    System.exit(0);
                default:
                    System.exit(0);
            }
        }
    }

    private static void printMenu() {
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Create a contract");
        System.out.println("2. Delete a contract");
        System.out.println("3. View user statistics");
        System.out.println("4. View your contracts");
        System.out.println("5. Exit");
    }
}
