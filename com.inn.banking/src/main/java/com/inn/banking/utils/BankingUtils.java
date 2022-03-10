package com.inn.banking.utils;

import java.util.Date;

public class BankingUtils {

    private BankingUtils() {
    }

    public static String getUUID() {
        Date date = new Date();
        long accno = date.getTime();
        return String.valueOf(accno);
    }

    // commands to run
    //alter table appuser modify column enabled tinyint(1);

    //alter table appointment modify column confirmed tinyint(1);


    /*
    http://localhost:8081/banking/primaryAccount/processFund
    Type :- POST
    Json :- {"accountNumber":"16467424723611","amount":"1400","processType":"withdraw"}

    http://localhost:8081/banking/primaryAccount/processFund
    Type :- POST
    Json :- {"accountNumber":"16467424723611","amount":"1400","processType":"deposit"}

    http://localhost:8081/banking/savingAccount/processFund
    Type :- POST
    Json :- {"accountNumber":"16467424723611","amount":"1400","processType":"withdraw"}

    http://localhost:8081/banking/savingAccount/processFund
    Type :- POST
    Json :- {"accountNumber":"16467424723611","amount":"1400","processType":"deposit"}

    http://localhost:8081/banking/primaryAccount/transferBetweenAccount
    Type :- POST
    Json :- {"transferFrom":"Primary","transferTo":"Saving","transferFromAccountNumber":"16467693762851","transferToAccountNumber":"16467693762850","amount":"50"}

    http://localhost:8081/banking/recipient/addRecipient
    Type :- POST
    Json :- {"name":"test","email":"test@gmail.com","phone":"34567890","accountNumber":"1234567890","description":"just for test","appuserId":"1"}

    http://localhost:8081/banking/recipient/getRecipientByUserId/1
    Type :- GET

    http://localhost:8081/banking/appuser/login
    Type :- POST
    {"username":"root","password":"root"}

    http://localhost:8081/banking/primaryTransaction/getPrimaryTransactionByAccountNo/16467693762851
    Type :- GET
    Note :- account number in path variable

    http://localhost:8081/banking/savingsTransaction/getSavingTransactionByAccountNo/16467693762850
    Type :- GET
    Note :- account number in path variable

    http://localhost:8081/banking/primaryAccount/transferFromPrimaryToAnother
    Type :- POST
    {"accountNumber":"16467693762851","amount":"100","processType":"withdraw","recipientName":"test"}
    Note :- processType always be withdraw

    http://localhost:8081/banking/savingAccount/transferFromSavingToAnother
    Type :- POST
    {"accountNumber":"16467693762851","amount":"100","processType":"withdraw","recipientName":"test"}
    Note :- processType always be withdraw

    http://localhost:8081/banking/appointment/scheduleAppointment
    Type :- POST
    {"appuserId":1,"date":"09/03/2022","location":"Indore","description":"just to meet"}
    Note :- Date needs to be in this format only

    http://localhost:8081/banking/recipient/updateRecipient
    Type :- POST
    {"name":"test","email":"testt@gmail.com","phone":"34567890","description":"------just for test","recipientId":"1"}

    http://localhost:8081/banking/recipient/deleteRecipient/1
    Type :- GET
    Status :- Not working right now

ADMIN APIS------------------

    http://localhost:8081/banking/appuser/getAllUserProfile
    Type :- GET

    http://localhost:8081/banking/appuser/enableDisableUser/true/1
    Type :- GET
    Note :- {status}/{userId} both in path variable

    http://localhost:8081/banking/appointment/getAllAppointment
    Type :- GET

    http://localhost:8081/banking/appointment/confirmAppointment/true/1
    Type :- GET
    Note :- {status}/{appointmentId} both in path variable

     */
}
