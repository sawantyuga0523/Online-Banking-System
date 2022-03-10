package com.inn.banking.service;

import com.inn.banking.POJO.SavingsAccount;
import com.inn.banking.POJO.SavingsTransaction;
import com.inn.banking.dao.SavingsAccountDao;
import com.inn.banking.dao.SavingsTransactionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class SavingAccountServiceInterface implements com.inn.banking.serviceInterface.SavingAccountServiceInterface {

    private Logger log = LogManager.getLogger(AppUserServiceInterface.class);

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private SavingsTransactionDao savingsTransactionDao;

    @Override
    public ResponseEntity<?> processFund(Map<String, String> requestMap) {
        try {
            if (requestMap.containsKey("accountNumber") && requestMap.containsKey("amount")) {
                if (Integer.parseInt(requestMap.get("amount")) > 0) {
                    SavingsAccount primaryAccount = savingsAccountDao.findByaccountNumber(Long.parseLong(requestMap.get("accountNumber")));
                    if (!Objects.isNull(primaryAccount)) {
//                        double d = primaryAccount.getAccountBalance().doubleValue() + Double.parseDouble(requestMap.get("amount"));
                        BigDecimal totalAmount;
                        String responseMessage = "";
                        if (requestMap.containsKey("processType") && requestMap.get("processType").equals("deposit")) {
                            totalAmount = primaryAccount.getAccountBalance().add(new BigDecimal(requestMap.get("amount")));
                            responseMessage = "{\"message\":\"Deposit Successfully.\"}";
                        } else {
                            if (primaryAccount.getAccountBalance().intValue() > Integer.parseInt(requestMap.get("amount"))) {
                                totalAmount = primaryAccount.getAccountBalance().subtract(new BigDecimal(requestMap.get("amount")));
                                responseMessage = "{\"message\":\"Withdraw Successfully.\"}";
                            } else {
                                return new ResponseEntity<>("{\"message\":\"Insufficient Balance.\"}", HttpStatus.OK);
                            }
                        }
                        log.info("-------> {}", totalAmount);
                        primaryAccount.setAccountBalance(totalAmount);
                        SavingsAccount savingsAccount = savingsAccountDao.save(primaryAccount);
                        processFund(requestMap, savingsAccount);
                        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("{\"message\":\"Invalid account no.\"}", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("{\"message\":\"Invalid amount.\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"Improper data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void processFund(Map<String, String> requestMap, SavingsAccount savingsAccount) {
        SavingsTransaction savingsTransaction = new SavingsTransaction();
        savingsTransaction.setDate(new Date());
        if (requestMap.get("processType").equals("deposit"))
            savingsTransaction.setDescription("Deposit to Saving Account");
        else if (requestMap.containsKey("desc")) {
            savingsTransaction.setDescription(requestMap.get("desc"));
        } else
            savingsTransaction.setDescription("Withdraw from Saving Account");
        if (requestMap.containsKey("desc")) {
            savingsTransaction.setType("Transfer");
        } else {
            savingsTransaction.setType("Account");
        }
        savingsTransaction.setStatus("Finished");
        savingsTransaction.setAmount(Double.parseDouble(requestMap.get("amount")));
        savingsTransaction.setAvailableBalance(savingsAccount.getAccountBalance());
        savingsTransaction.setSavingsAccount(savingsAccount);
        savingsTransactionDao.save(savingsTransaction);
    }

    @Override
    public ResponseEntity<?> transferFromSavingToAnother(Map<String, String> requestMap) {
        try {
            if (requestMap.containsKey("accountNumber") && requestMap.containsKey("amount") && requestMap.containsKey("recipientName")) {
                requestMap.put("desc", "Transfer to recipent " + requestMap.get("recipientName"));
                return processFund(requestMap);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
