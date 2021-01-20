package org.hua.javaphone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contract extends User {

    private final ArrayList<Contract> contractArrayList;
    private int freeCallMinutes;
    private int contractDuration; //12 -> option 1 or 24 months -> option 2
    private int monthlyCost;
    private int billingMethod; //printed or electronic bill
    private int paymentMethod; //credit, cash, standing order in a bank account
    private int contractType; //landline -> option 1 or mobile -> option 2
    private int contractCode;
    private double discount;
    private String phoneNumber;
    private String startingDate;
    private int internetSpeed; //ADSL, VDSL
    private int freeGB;
    private int freeSMS;
    private int userAfm;
    private final int maxFreeCallMinutes = 3000;

    public Contract(ArrayList<Contract> contractArrayList, ArrayList<User> userArrayList) {
        super(userArrayList); //get userArrayList into Contract class
        this.contractArrayList = contractArrayList;
    }

    /**
     * sets the user's afm
     * @param userAfm user's afm
     */
    public void setUserAfm(int userAfm) {
        this.userAfm = userAfm;
    }

    /**
     * gets user's afm
     * @return user's afm
     */
    public int getUserAfm() {
        return userAfm;
    }

    /**
     * gets the number of free GBs
     * @return the number of free GBs
     */
    public int getFreeGB() {
        return freeGB;
    }

    /**
     * sets the number of free GBs
     * @param freeGB
     */
    public void setFreeGB(int freeGB) {
        this.freeGB = freeGB;
    }

    /**
     * gets the number of free sms
     * @return the number of free sms
     */
    public int getFreeSMS() {
        return freeSMS;
    }

    /**
     * sets the nubmer of free sms
     * @param freeSMS
     */
    public void setFreeSMS(int freeSMS) {
        this.freeSMS = freeSMS;
    }

    /**
     * sets the payment method
     * 1 -> credit card
     * 2 -> cash
     * 3 -> standing order in bank account
     * @param paymentMethod integer from 1 to 3 corresponding to the payment method
     */
    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * gets the payment method
     * @return integer from 1 to 3 corresponding to the payment method
     */
    public int getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * sets the number of free call minutes
     * @param freeCallMinutes the number of free call minutes
     */
    public void setFreeCallMinutes(int freeCallMinutes) {
        this.freeCallMinutes = freeCallMinutes;
    }

    /**
     * gets the number of free call minutes
     * @return the number of free call minutes
     */
    public int getFreeCallMinutes() {
        return freeCallMinutes;
    }

    /**
     * sets the duration of the contract
     * 1 -> 12 months
     * 2 -> 24 months
     * @param contractDuration integer 1 or 2 corresponding to the duration of the contract
     */
    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    /**
     * gets the duration of the contract
     * 1 -> 12 months
     * 2 -> 24 months
     * @return integer 1 or 2 corresponding to the duration of the contract
     */
    public int getContractDuration() {
        return contractDuration;
    }

    /**
     * sets the monthly cost of the contract
     * @param monthlyCost the monthly cost of the contract
     */
    public void setMonthlyCost(int monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    /**
     * gets the monthly cost of the contract
     * @return the monthly cost of the contract
     */
    public int getMonthlyCost() {
        return monthlyCost;
    }

    /**
     * sets the billing method
     * 1 -> printed
     * 2 -> online bill
     * @param billingMethod integer 1 or 2 corresponding to the billing method
     */
    public void setBillingMethod(int billingMethod) {
        this.billingMethod = billingMethod;
    }

    /**
     * gets the billing method
     * 1 -> printed bill
     * 2 -> online bill
     * @return integer 1 or 2 corresponding to the billing method
     */
    public int getBillingMethod() {
        return billingMethod;
    }

    /**
     * sets the contract type
     * 1 -> landline
     * 2 -> mobile
     * @param contractType integer 1 or 2 corresponding to the type of the contract
     */
    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    /**
     * gets the contract type
     * 1 -> landline
     * 2 -> mobile
     * @return integer 1 or 2 corresponding to the contract type
     */
    public int getContractType() {
        return contractType;
    }

    /**
     * sets the contract code, a random positive integer up to 999
     * @param contractCode the contract code
     */
    public void setContractCode(int contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * gets the contract code
     * @return the contract code
     */
    public int getContractCode() {
        return contractCode;
    }

    /**
     * sets the contract's discount
     * @param discount the amount of the discount (i.e 15% -> 0.15)
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * gets the amount of the discount
     * @return the amount of the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * sets the phone number entered by the user
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * gets the phone number entered by the user
     * @return the phone number entered by the user
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * sets the contract's starting date
     * @param startingDate the starting date of the contract
     */
    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * gets the starting date of the contract
     * @return the starting date of the contract
     */
    public String getStartingDate() {
        return startingDate;
    }

    /**
     * sets the contract's internet speed
     * 1 -> ADSL
     * 2 -> VDSL
     * @param internetSpeed integer 1 or 2 corresponding to the internet speed of the contract
     */
    public void setInternetSpeed(int internetSpeed) {
        this.internetSpeed = internetSpeed;
    }

    /**
     * gets the internet speed of the contract
     * 1 -> ADSL
     * 2 -> VDSL
     * @return integer 1 or 2 corresponding to the internet speed of the contract
     */
    public int getInternetSpeed() {
        return internetSpeed;
    }

    /**
     * create a new contract for the user
     * @param tmpContract has the afm of the user and is used to create the contract
     */
    public void createContract(Contract tmpContract) {
        Scanner input = new Scanner(System.in);

        //get contract type
        System.out.println("What type of contract would you like? \n1. Landline\n2. Mobile");
        while (true) {
            if (input.hasNextInt()) {
                int contractType = input.nextInt();
                if (contractType == 1 || contractType == 2) {
                    tmpContract.setContractType(contractType);
                    if (tmpContract.getContractType() == 1) {
                        //contract is landline
                        LandlineContract landlineContract = new LandlineContract(contractArrayList, userArrayList);
                        tmpContract = landlineContract.createLandlineContract(tmpContract);
                        break;
                    } else if (tmpContract.getContractType() == 2) {
                        //contract is mobile
                        MobileContract mobileContract = new MobileContract(contractArrayList, userArrayList);
                        tmpContract = mobileContract.createMobileContract(tmpContract);
                        break;
                    }
                }
                System.out.println("Wrong input! Please enter a corresponding number!");
            }
            System.out.println("Wrong input! Please enter a corresponding number!");
        }

        //create a random contract code and check if it already exists
        Random random = new Random();
        final int CODE_BOUND = 999;
        int code = random.nextInt(CODE_BOUND);
        int i = 0;
        while (i < contractArrayList.size()) {
            if (contractArrayList.get(i).getContractCode() == code) {
                code = random.nextInt(CODE_BOUND);
                i = 0;
            } else {
                i++;
            }
        }
        tmpContract.setContractCode(code);

        //get amount of free call minutes
        System.out.println("Please enter your desired amount of free call minutes per month: ");
        while (true) {
            if (input.hasNextInt()) {
                int minutes = input.nextInt();
                if (minutes >= 0 && minutes <= maxFreeCallMinutes) {
                    tmpContract.setFreeCallMinutes(minutes);
                    if (minutes > 1000 && tmpContract.getContractType() == 1) {
                        //free call minutes > 1000 && contract is landline -> discount + 8%
                        double tmpDiscount = tmpContract.getDiscount() + 0.08;
                        tmpContract.setDiscount(tmpDiscount);
                    } else if (minutes > 1000 && tmpContract.getContractType() == 2) {
                        //free call minutes > 1000 && contract is mobile -> discount + 11%
                        double tmpDiscount = tmpContract.getDiscount() + 0.11;
                        tmpContract.setDiscount(tmpDiscount);
                    }
                    break;
                } else {
                    System.out.println("Cannot have negative minutes and cannot be more than " + maxFreeCallMinutes);
                }
            }
            System.out.println("Wrong input! Please enter a number");
        }

        //get duration of contract
        System.out.println("Please select the duration of your contract: \n1. 12 months \n2. 24 months");
        while (true) {
            if (input.hasNextInt()) {
                int duration = input.nextInt();
                if (duration == 1 || duration == 2) {
                    tmpContract.setContractDuration(duration); //1->12 months, 2->24 months
                    break;
                }
                System.out.println("Please enter the corresponding number");
            }
            System.out.println("Wrong input! Please enter the corresponding number");
        }

        //get starting date
        System.out.println("Please enter your desired starting date in this format dd/MM/yyyy: ");
        while (true) {
            //we assume the user uses the aforementioned date format
            tmpContract.setStartingDate(input.next());
            //check for overlapping -> cannot have two contracts with the same phone number at the same time
            if (checkForOverlapping()) {
                System.out.println("Please enter another starting date or change the duration of your contract. (Contracts cannot have overlapping dates)");
            } else {
                //all good
                break;
            }
        }


        //get billing method
        System.out.println("Please choose your billing method: \n1. Printed \n2. Online");
        while (true) {
            if (input.hasNextInt()) {
                int billingMethod = input.nextInt();
                if (billingMethod == 1 || billingMethod == 2) { //1->printed bill, 2->online bill
                    tmpContract.setBillingMethod(billingMethod);
                    //if billing method is online user gets 2% discount
                    if (tmpContract.getBillingMethod() == 2) {
                        double tmpDiscount = tmpContract.getDiscount() + 0.02;
                        tmpContract.setDiscount(tmpDiscount);
                    }
                    break;
                }
                System.out.println("Please enter the corresponding number");
            }
            System.out.println("Wrong input! Please enter the corresponding number");
        }

        //get payment method
        System.out.println("Please select your payment method: \n1. Credit Card \n2. Cash \n3. Standing order in a bank account");
        while (true) {
            if (input.hasNextInt()) {
                int paymentMethod = input.nextInt();
                if (paymentMethod == 1 || paymentMethod == 2 || paymentMethod == 3) {
                    tmpContract.setPaymentMethod(paymentMethod);
                    //if payment method is: credit card or Standing order in a bank account -> discount + 5%
                    if (tmpContract.getPaymentMethod() == 1 || tmpContract.getPaymentMethod() == 3) {
                        double tmpDiscount = tmpContract.getDiscount() + 0.05;
                        tmpContract.setDiscount(tmpDiscount);
                    }
                    break;
                }
                System.out.println("Please enter the corresponding number");
            }
            System.out.println("Wrong input! Please enter the corresponding number");
        }

        //discount based on user type(student, professional)
        int index = findUser(tmpContract.getUserAfm());
        double tmpDiscount;
        if (userArrayList.get(index).getUserType() == 1) {
            //student -> 15% discount
            tmpDiscount = tmpContract.getDiscount() + 0.15;
            tmpContract.setDiscount(tmpDiscount);
        } else if (userArrayList.get(index).getUserType() == 3) {
            //professional -> 10% discount
            tmpDiscount = tmpContract.getDiscount() + 0.10;
            tmpContract.setDiscount(tmpDiscount);
        }

        //if discount > 45% then discount must be set to 45%
        if (tmpContract.getDiscount() > 0.45) {
            tmpContract.setDiscount(0.45);
        }

        //add contract
        contractArrayList.add(tmpContract);
    }

     /**
     * print contract details such as: user code, contract code,
     * phone number, monthly cost, discount, cost after discount
     * user afm, starting date, contract duration, billing method
     * payment method, internet speed if the contract is landline
     * number of free call minutes, number of free gbs and sms if the contract is mobile
     * @param contractIndex index of the contract to print
     * @param userIndex index of the user
     */
    public void printContract(int contractIndex, int userIndex) {
        System.out.println("User code: " + userArrayList.get(userIndex).getUserCode());
        System.out.println("Contract Code: " + contractArrayList.get(contractIndex).getContractCode());
        System.out.println("Phone Number: " + contractArrayList.get(contractIndex).getPhoneNumber());
        System.out.println("Monthly Cost: " + contractArrayList.get(contractIndex).getMonthlyCost());
        System.out.println("Discount: " + contractArrayList.get(contractIndex).getDiscount());
        double finalDiscount = contractArrayList.get(contractIndex).getMonthlyCost() * contractArrayList.get(contractIndex).getDiscount();
        double costAfterDiscount = contractArrayList.get(contractIndex).getMonthlyCost() - finalDiscount;
        System.out.println("Cost after discount: " + costAfterDiscount);
        System.out.println("AFM: " + userArrayList.get(userIndex).getAfm());
        System.out.println("Starting Date: " + contractArrayList.get(contractIndex).getStartingDate());

        if (contractArrayList.get(contractIndex).getContractDuration() == 1) {
            System.out.println("Duration: 12 months");
        } else {
            System.out.println("Duration: 24 months");
        }

        if (contractArrayList.get(contractIndex).getBillingMethod() == 1) {
            System.out.println("Billing Method: Printed Bill");
        } else {
            System.out.println("Billing Method: Online Bill");
        }

        if (contractArrayList.get(contractIndex).getPaymentMethod() == 1) {
            System.out.println("Payment Method: Credit Card");
        } else if (contractArrayList.get(contractIndex).getPaymentMethod() == 2) {
            System.out.println("Payment Method: Cash");
        } else {
            System.out.println("Payment Method: Standing order in a bank account");
        }

        System.out.println("Free Call Minutes: " + contractArrayList.get(contractIndex).getFreeCallMinutes());

        if (contractArrayList.get(contractIndex).getContractType() == 1) {
            if (contractArrayList.get(contractIndex).getInternetSpeed() == 1) {
                System.out.println("Internet Speed: ADSL (24 Mbps)");
            } else if (contractArrayList.get(contractIndex).getInternetSpeed() == 2) {
                System.out.println("Internet Speed: VDSL (50 Mbps)");
            } else {
                System.out.println("Internet Speed: -");
            }
        } else {
            System.out.println("Free GB per month: " + contractArrayList.get(contractIndex).getFreeGB());
            System.out.println("Free SMS per month: " + contractArrayList.get(contractIndex).getFreeSMS());
        }
        System.out.println("");
    }

    /**
     * checks if the phone number entered by the user starts with 2
     * @param userInput the phone number entered by the user
     * @return true if the phone number starts with 2 else false
     */
    protected boolean checkNumber(String userInput) {
        Pattern pattern = Pattern.compile("^2");
        Matcher matcher = pattern.matcher(userInput);
        return matcher.find();
    }

    /**
     * checks if the phone number entered by the user starts with 6
     * @param userInput the phone number entered by the user
     * @return true if the phone number starts with 6 else false
     */
    protected boolean checkMobileNumber(String userInput) {
        Pattern pattern = Pattern.compile("^6");
        Matcher matcher = pattern.matcher(userInput);
        return matcher.find();
    }

    /**
     * prints all the contracts associated with the user's afm
     * the user selects which contract to delete
     * deletes the contract selected
     */
    public void deleteContract() {

        boolean found = false;
        int j = 1;
        int[] indexes = new int[contractArrayList.size() + 1]; //indexing starts from 1

        //finds and prints all the contracts associated with this afm
        for (int i = 0; i < contractArrayList.size(); i++) {
            if (contractArrayList.get(i).getUserAfm() == this.getUserAfm()) {
                int userIndex = findUser(this.getUserAfm());
                System.out.println("Contract: " + j);
                contractArrayList.get(i).printContract(i, userIndex);
                System.out.println();
                indexes[j] = i;
                j++;
                found = true;
            }
        }

        if (found) {
            System.out.println("Please choose the contract that you would like to delete: ");

            //gets the index of the contract to delete and deletes it
            Scanner input = new Scanner(System.in);
            while (true) {
                if (input.hasNextInt()) { //check if the input is number
                    int indexToDelete = input.nextInt();
                    if (indexToDelete >= 0 && indexToDelete < indexes.length) {
                        contractArrayList.remove(indexes[indexToDelete]);
                        break;
                    }
                    System.out.println("Please enter a corresponding number");
                }
                System.out.println("Please enter a corresponding number");
            }
        } else {
            System.out.println("Looks like there are no contracts associated with this AFM: " + this.getUserAfm());
        }
    }

    /**
     * checks for overlapping dates in user's contracts
     * when the user creates a new contract he/she enters the desired starting date for that contract
     * this method checks if that date overlaps with another contract
     * @return true if there overlapping else returns false
     */
    private boolean checkForOverlapping() {
    //TODO find link to site that I used
        String date1 = getStartingDate();
        String date2 = getStartingDate(); //initialized the same so that we don't have any problems

        //search for other contracts
        boolean found = false;
        for (Contract contract : contractArrayList) {
            if (contract.getUserAfm() == this.getUserAfm() && contract.getContractCode() != this.getContractCode()) {
                date1 = contract.getStartingDate();
                found = true;
            }
        }

        if (!found) {
            return false; //no contracts where found so there cannot be any overlapping dates
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        try {
            //get dates
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);

            Calendar startingDate = Calendar.getInstance();
            startingDate.setTime(d1);

            Calendar newDate = Calendar.getInstance();
            newDate.setTime(d2);

            Calendar endDate = Calendar.getInstance();
            endDate.setTime(d1);

            //get variable Duration
            if (contractDuration == 1) {
                //add 1 year to the contract length
                endDate.add(Calendar.YEAR, 1);
            } else {
                //add 2 years to the contract length
                endDate.add(Calendar.YEAR, 2);
            }

            return endDate.compareTo(newDate) > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * print statistics for all the contracts such as:
     * the minimum amount of free call minutes selected by the users
     * the average amount of free call minutes selected by the users
     * the maximum amount of free call minutes selected by the users
     * etc
     */
    public void printStatistics() {
        int minFreeCallMinutesLandline = maxFreeCallMinutes;
        int minFreeCallMinutesMobile = maxFreeCallMinutes;
        if (getContractType() == 1) {
            minFreeCallMinutesLandline = getFreeCallMinutes();
        } else {
            minFreeCallMinutesMobile = getFreeCallMinutes();
        }
        int minFreeGB = getFreeGB();
        int minFreeSMS = getFreeSMS();

        for (int i = 0; i < contractArrayList.size(); i++) {
            if (contractArrayList.get(i).getContractType() == 1) {
                if (contractArrayList.get(i).getFreeCallMinutes() < minFreeCallMinutesLandline) {
                    minFreeCallMinutesLandline = contractArrayList.get(i).getFreeCallMinutes();
                }
            } else {
                if (contractArrayList.get(i).getFreeCallMinutes() < minFreeCallMinutesMobile) {
                    minFreeCallMinutesMobile = contractArrayList.get(i).getFreeCallMinutes();
                }
                if (contractArrayList.get(i).getFreeGB() < minFreeGB) {
                    minFreeGB = contractArrayList.get(i).getFreeGB();
                }
                if (contractArrayList.get(i).getFreeSMS() < minFreeSMS) {
                    minFreeSMS = contractArrayList.get(i).getFreeSMS();
                }
            }
        }

        int maxFreeCallMinutesLandline = -1;
        int maxFreeCallMinutesMobile = -1;
        if (getContractType() == 1) {
            maxFreeCallMinutesLandline = getFreeCallMinutes();
        } else {
            maxFreeCallMinutesMobile = getFreeCallMinutes();
        }
        int maxFreeGB = getFreeGB();
        int maxFreeSMS = getFreeSMS();

        for (int i = 0; i < contractArrayList.size(); i++) {
            if (contractArrayList.get(i).getContractType() == 1) {
                if (contractArrayList.get(i).getFreeCallMinutes() > maxFreeCallMinutesLandline) {
                    maxFreeCallMinutesLandline = contractArrayList.get(i).getFreeCallMinutes();
                }
            } else {
                if (contractArrayList.get(i).getFreeCallMinutes() > maxFreeCallMinutesMobile) {
                    maxFreeCallMinutesMobile = contractArrayList.get(i).getFreeCallMinutes();
                }if (contractArrayList.get(i).getFreeGB() > maxFreeGB) {
                    maxFreeGB = contractArrayList.get(i).getFreeGB();
                }
                if (contractArrayList.get(i).getFreeSMS() > maxFreeSMS) {
                    maxFreeSMS = contractArrayList.get(i).getFreeSMS();
                }
            }
        }

        int sumFreeCallMinutesLandline = 0;
        int sumFreeCallMinutesMobile = 0;
        int sumFreeGB = 0;
        int sumFreeSMS = 0;
        int landlineCounter = 0;
        int mobileCounter = 0;
        for (int i = 0; i < contractArrayList.size(); i++) {
            if (contractArrayList.get(i).getContractType() == 1) {
                sumFreeCallMinutesLandline += contractArrayList.get(i).getFreeCallMinutes();
                landlineCounter++;
            } else {
                sumFreeCallMinutesMobile += contractArrayList.get(i).getFreeCallMinutes();
                sumFreeGB += contractArrayList.get(i).getFreeGB();
                sumFreeSMS += contractArrayList.get(i).getFreeSMS();
                mobileCounter++;
            }
        }

        long avgFreeCallMinutesLandline = 0;
        if (landlineCounter != 0) {
            avgFreeCallMinutesLandline = sumFreeCallMinutesLandline / landlineCounter;
        }

        long avgFreeCallMinutesMobile = 0;
        long avgFreeGB = 0;
        long avgFreeSMS = 0;
        if (mobileCounter != 0) {
            avgFreeCallMinutesMobile = sumFreeCallMinutesMobile / mobileCounter;
            avgFreeGB = sumFreeGB/mobileCounter;
            avgFreeSMS = sumFreeSMS/mobileCounter;
        }


        System.out.println("Landline:\t\t\t\t\t\t\tMobile:");
        System.out.println("Min free call minutes: " + minFreeCallMinutesLandline + "\t\t\tMin free call minutes: " + minFreeCallMinutesMobile);
        System.out.println("Max free call minutes: " + maxFreeCallMinutesLandline + "\t\t\tMax free call minutes: " + maxFreeCallMinutesMobile);
        System.out.println("Average free call minutes: " + avgFreeCallMinutesLandline + "\t\tAverage free call minutes " + avgFreeCallMinutesMobile);
        System.out.println("                            \t\tMin free GB: " + minFreeGB);
        System.out.println("                            \t\tMax free GB: " + maxFreeGB);
        System.out.println("                            \t\tAverage free GB: " + avgFreeGB);
        System.out.println("                            \t\tMin free SMS: " + minFreeSMS);
        System.out.println("                            \t\tMax free SMS: " + maxFreeSMS);
        System.out.println("                            \t\tAverage free SMS: " + avgFreeSMS);
    }

}
