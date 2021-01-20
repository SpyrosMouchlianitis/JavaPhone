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

        //add template users
        createExampleUsers(userArrayList, contractArrayList);

        Contract tmpContract = new Contract(contractArrayList, userArrayList);

        User tmpUser = new User(userArrayList);
        int afm;
        //get afm and check if it is number and has 9 digits
        while (true) {
            System.out.println("Please enter your AFM: ");
            if (input.hasNextInt()) {
                //input is integer type
                afm = input.nextInt();
                if (tmpUser.checkAfm(afm)) {
                    //set afm
                    tmpUser.setAfm(afm);
                    break;
                } else {
                    //integer was not 9 digits
                    System.out.println("AFM must have 9 digits");
                }
            } else {
                //input is not integer
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
                    //create a new contract for the user
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
                    //base cost is 100 if VDSL +100 if ADSL +50
                    //GBs, SMS, CallMinutes are free
                    break;
                case 2:
                    //we already have the user's afm since we checked whether he exists or not
                    //find contracts associated with said afm
                    //let user choose which contract to delete
                    tmpContract.setUserAfm(tmpUser.getAfm()); //set the afm that corresponds to the contract
                    tmpContract.deleteContract(); //delete the contract
                    break;
                case 3:
                    //print statistics for the contracts
                    tmpContract.printStatistics();
                    break;
                case 4:
                    //print all contracts associated with the afm
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
                    //exit programm
                    System.out.println("Thank you for choosing JavaPhone!");
                    System.exit(0);
                default:
                    System.exit(0);
            }
        }
    }

    /**
     * prints the menu
     */
    private static void printMenu() {
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Create a contract");
        System.out.println("2. Delete a contract");
        System.out.println("3. View user statistics");
        System.out.println("4. View your contracts");
        System.out.println("5. Exit");
    }

    /**
     * method used to create three example users
     * @param userArrayList holds all the users
     * @param contractArrayList holds all the contracts for the users
     */
    private static void createExampleUsers(ArrayList<User> userArrayList, ArrayList<Contract> contractArrayList) {
        User tmpUser1 = new User(userArrayList);
        tmpUser1.setAfm(123456789);
        tmpUser1.setUserCode(userArrayList.size());
        tmpUser1.setAddress("Kalymnou 15");
        tmpUser1.setIdNumber("AK 787878");
        tmpUser1.setUserType(1); //φοιτητης
        tmpUser1.setEmail("tmpUser1@gmail.com");
        userArrayList.add(tmpUser1);

        User tmpUser2 = new User(userArrayList);
        tmpUser2.setAfm(987654321);
        tmpUser2.setUserCode(userArrayList.size());
        tmpUser2.setAddress("Papandreou 2");
        tmpUser2.setIdNumber("AK 453269");
        tmpUser2.setUserType(2); //ιδιωτης
        tmpUser2.setEmail("tmpUser2@gmail.com");
        userArrayList.add(tmpUser2);

        User tmpUser3 = new User(userArrayList);
        tmpUser3.setAfm(789321659);
        tmpUser3.setUserCode(userArrayList.size());
        tmpUser3.setAddress("Serifou 10");
        tmpUser3.setIdNumber("AK 123456");
        tmpUser3.setUserType(3); //επαγγελματιας
        tmpUser3.setEmail("tmpUser3@gmail.com");
        userArrayList.add(tmpUser3);

        Contract tmpContract1 = new Contract(contractArrayList, userArrayList);
        tmpContract1.setPhoneNumber("2102532479");
        tmpContract1.setUserAfm(tmpUser1.getAfm());
        tmpContract1.setContractCode(123);
        tmpContract1.setContractType(1); //landline
        tmpContract1.setContractDuration(2); //24 months
        tmpContract1.setInternetSpeed(2); //VDSL
        tmpContract1.setPaymentMethod(1); //credit card
        tmpContract1.setBillingMethod(2); //electronic bill
        tmpContract1.setFreeCallMinutes(1500);
        tmpContract1.setStartingDate("12/12/2021");
        tmpContract1.setMonthlyCost(100);
        tmpContract1.setDiscount(0.30);
        contractArrayList.add(tmpContract1);

        Contract tmpContract2 = new Contract(contractArrayList, userArrayList);
        tmpContract2.setPhoneNumber("6945464441");
        tmpContract2.setUserAfm(tmpUser2.getAfm());
        tmpContract2.setContractCode(1234);
        tmpContract2.setContractType(2); //mobile
        tmpContract2.setContractDuration(1); //12 months
        tmpContract2.setMonthlyCost(100);
        tmpContract2.setPaymentMethod(2); // cash
        tmpContract2.setBillingMethod(1); //printed bill
        tmpContract2.setFreeCallMinutes(1500);
        tmpContract2.setFreeSMS(1500);
        tmpContract2.setFreeGB(1500);
        tmpContract2.setStartingDate("15/02/2021");
        tmpContract2.setDiscount(0.15);
        contractArrayList.add(tmpContract2);

        Contract tmpContract3 = new Contract(contractArrayList, userArrayList);
        tmpContract3.setPhoneNumber("2102232574");
        tmpContract3.setUserAfm(tmpUser3.getAfm());
        tmpContract3.setContractCode(1235);
        tmpContract3.setContractType(1); //landline
        tmpContract3.setContractDuration(2); //24 months
        tmpContract3.setInternetSpeed(1); //ADSL
        tmpContract3.setPaymentMethod(3); //standing order in a bank account
        tmpContract3.setBillingMethod(2); //electronic bill
        tmpContract3.setFreeCallMinutes(1500);
        tmpContract3.setStartingDate("23/10/2021");
        tmpContract3.setDiscount(0.20);
        contractArrayList.add(tmpContract3);
    }
}
