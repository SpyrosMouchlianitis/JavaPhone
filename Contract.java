package org.hua.javaphone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contract extends User {

    private ArrayList<Contract> contractArrayList;
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

    public void setUserAfm(int userAfm) {
        this.userAfm = userAfm;
    }

    public int getUserAfm() {
        return userAfm;
    }

    public int getFreeGB() {
        return freeGB;
    }

    public void setFreeGB(int freeGB) {
        this.freeGB = freeGB;
    }

    public int getFreeSMS() {
        return freeSMS;
    }

    public void setFreeSMS(int freeSMS) {
        this.freeSMS = freeSMS;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setFreeCallMinutes(int freeCallMinutes) {
        this.freeCallMinutes = freeCallMinutes;
    }

    public int getFreeCallMinutes() {
        return freeCallMinutes;
    }

    public void setContractDuration(int contractDuration) {
        this.contractDuration = contractDuration;
    }

    public int getContractDuration() {
        return contractDuration;
    }

    public void setMonthlyCost(int monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public int getMonthlyCost() {
        return monthlyCost;
    }

    public void setBillingMethod(int billingMethod) {
        this.billingMethod = billingMethod;
    }

    public int getBillingMethod() {
        return billingMethod;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public int getContractType() {
        return contractType;
    }

    public void setContractCode(int contractCode) {
        this.contractCode = contractCode;
    }

    public int getContractCode() {
        return contractCode;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setInternetSpeed(int internetSpeed) {
        this.internetSpeed = internetSpeed;
    }

    public int getInternetSpeed() {
        return internetSpeed;
    }

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
                        createLandlineContract(tmpContract);
                        break;
                    } else if (tmpContract.getContractType() == 2) {
                        //contract is mobile
                        createMobileContract(tmpContract);
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
                    tmpContract.setContractDuration(duration);
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
            //check for overlapping
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
                if (billingMethod == 1 || billingMethod == 2) {
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

    private void createMobileContract(Contract tmpContract) {
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
    }

    private void createLandlineContract(Contract tmpContract) {
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
    }

    //print contract details
    public void printContract(int contractIndex, int userIndex) {
        System.out.println("User code: " + userArrayList.get(userIndex).getUserCode());
        System.out.println("Contract Code: " + contractArrayList.get(contractIndex).getContractCode());
        System.out.println("Phone Number: " + contractArrayList.get(contractIndex).getPhoneNumber());
        System.out.println("Monthly Cost: " + contractArrayList.get(contractIndex).getMonthlyCost());
        System.out.println("Discount: " + contractArrayList.get(contractIndex).getDiscount());
        double finalDiscount = contractArrayList.get(contractIndex).getMonthlyCost() * contractArrayList.get(contractIndex).getDiscount();
        double costAfterDiscount = contractArrayList.get(contractIndex).getMonthlyCost() - finalDiscount;
        System.out.println("Cost after discount: " + costAfterDiscount);
        System.out.println("AFM: " + contractArrayList.get(contractIndex).getUserAfm());
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

    //1st digit must be 2
    private boolean checkNumber(String userInput) {
        Pattern pattern = Pattern.compile("^2");
        Matcher matcher = pattern.matcher(userInput);
        return matcher.find();
    }

    //1st digit must be 6
    private boolean checkMobileNumber(String userInput) {
        Pattern pattern = Pattern.compile("^6");
        Matcher matcher = pattern.matcher(userInput);
        return matcher.find();
    }

    //delete contract
    public void deleteContract() {

        boolean found = false;
        int j = 1;
        int[] indexes = new int[contractArrayList.size() + 1]; //indexing starts from 1
        int k = 1;

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

            Scanner input = new Scanner(System.in);
            while (true) {
                if (input.hasNextInt()) {
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
            return false;
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

    /**
     * this method sets the discount for the template users
     * @param index represents the index of the user in the contract list
     */
    public void createTemplateDiscount(int index) {
    }
}