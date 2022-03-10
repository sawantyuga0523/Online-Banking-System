package com.inn.banking.service;

import com.inn.banking.POJO.PrimaryAccount;
import com.inn.banking.POJO.PrimaryTransaction;
import com.inn.banking.POJO.SavingsAccount;
import com.inn.banking.dao.PrimaryAccountDao;
import com.inn.banking.dao.PrimaryTransactionDao;
import com.inn.banking.dao.SavingsAccountDao;
import com.inn.banking.serviceInterface.SavingAccountServiceInterface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@Slf4j
@Service
public class PrimaryAccountServiceInterface implements com.inn.banking.serviceInterface.PrimaryAccountServiceInterface {

    private Logger log = LogManager.getLogger(PrimaryAccountServiceInterface.class);

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    private SavingAccountServiceInterface savingAccountServiceInterface;

    @Override
    public ResponseEntity<?> processFund(Map<String, String> requestMap) {
        log.info("Inside processFund : {}", requestMap);
        try {
            if (requestMap.containsKey("accountNumber") && requestMap.containsKey("amount")) {
                if (Integer.parseInt(requestMap.get("amount")) > 0) {
                    PrimaryAccount primaryAccount = primaryAccountDao.findByaccountNumber(Long.parseLong(requestMap.get("accountNumber")));
                    if (!Objects.isNull(primaryAccount)) {
//                        double d = primaryAccount.getAccountBalance().doubleValue() + Double.parseDouble(requestMap.get("amount"));
                        BigDecimal totalAmount = new BigDecimal(0);
                        String responseMessage = "";
                        if (requestMap.containsKey("processType") && requestMap.get("processType").equals("deposit")) {
                            totalAmount = primaryAccount.getAccountBalance().add(new BigDecimal(requestMap.get("amount")));
                            responseMessage = "{\"message\":\"Deposit Successfully.\"}";
                        } else {
                            if (primaryAccount.getAccountBalance().intValue() > Integer.parseInt(requestMap.get("amount"))) {
                                log.info("primaryAccount Balance : {}", primaryAccount.getAccountBalance());
                                totalAmount = primaryAccount.getAccountBalance().subtract(new BigDecimal(requestMap.get("amount")));
                                responseMessage = "{\"message\":\"Withdraw Successfully.\"}";
                            } else {
                                return new ResponseEntity<>("{\"message\":\"Insufficient Balance.\"}", HttpStatus.OK);
                            }
                        }
                        log.info("-------> {}", totalAmount);
                        primaryAccount.setAccountBalance(totalAmount);
                        PrimaryAccount primaryAccount1 = primaryAccountDao.save(primaryAccount);
                        processFund(requestMap, primaryAccount1);
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

    private void processFund(Map<String, String> requestMap, PrimaryAccount primaryAccount) {
        PrimaryTransaction primaryTransaction = new PrimaryTransaction();
        primaryTransaction.setDate(new Date());
        if (requestMap.get("processType").equals("deposit") && !requestMap.containsKey("desc"))
            primaryTransaction.setDescription("Deposit to Saving Account");
        else if (requestMap.containsKey("desc")) {
            primaryTransaction.setDescription(requestMap.get("desc"));
        } else
            primaryTransaction.setDescription("Withdraw from Saving Account");
        if (requestMap.containsKey("desc")) {
            primaryTransaction.setType("Transfer");
        } else {
            primaryTransaction.setType("Account");
        }
        primaryTransaction.setStatus("Finished");
        primaryTransaction.setAmount(Double.parseDouble(requestMap.get("amount")));
        primaryTransaction.setAvailableBalance(primaryAccount.getAccountBalance());
        primaryTransaction.setPrimaryAccount(primaryAccount);
        primaryTransactionDao.save(primaryTransaction);
    }

    @Override
    public ResponseEntity<?> transferBetweenAccount(Map<String, String> requestMap) {
        try {
            if (validateRequestMap(requestMap)) {
                if (!requestMap.get("transferFrom").equals(requestMap.get("transferTo"))) {
                    PrimaryAccount primaryAccount;
                    SavingsAccount savingsAccount;
                    BigDecimal finalAmount;
                    Map<String, String> processFundMap = new HashMap<>();

                    if (requestMap.get("transferFrom").equalsIgnoreCase("Primary")) {
                        primaryAccount = primaryAccountDao.findByaccountNumber(Long.parseLong(requestMap.get("transferFromAccountNumber")));
                        savingsAccount = savingsAccountDao.findByaccountNumber(Long.parseLong(requestMap.get("transferToAccountNumber")));

                        if (primaryAccount.getAccountBalance().intValue() >= Integer.parseInt(requestMap.get("amount"))) {
//                            finalAmount = primaryAccount.getAccountBalance().subtract(new BigDecimal(requestMap.get("amount")));
//                            primaryAccount.setAccountBalance(finalAmount);
//                            primaryAccountDao.save(primaryAccount);
//
//                            finalAmount = savingsAccount.getAccountBalance().add(new BigDecimal(requestMap.get("amount")));
//                            savingsAccount.setAccountBalance(finalAmount);
//                            savingsAccountDao.save(savingsAccount);

//                            {"accountNumber":"16467424723611","amount":"1400","processType":"withdraw"}
                            processFundMap.put("accountNumber", primaryAccount.getAccountNumber().toString());
                            processFundMap.put("amount", requestMap.get("amount"));
                            processFundMap.put("processType", "withdraw");
                            processFund(processFundMap);

                            processFundMap.clear();

                            processFundMap.put("accountNumber", savingsAccount.getAccountNumber().toString());
                            processFundMap.put("amount", requestMap.get("amount"));
                            processFundMap.put("processType", "deposit");
                            savingAccountServiceInterface.processFund(processFundMap);

                        } else {
                            return new ResponseEntity<>("{\"message\":\"Insufficient Fund.\"}", HttpStatus.BAD_REQUEST);
                        }

                    } else {
                        primaryAccount = primaryAccountDao.findByaccountNumber(Long.parseLong(requestMap.get("transferToAccountNumber")));
                        savingsAccount = savingsAccountDao.findByaccountNumber(Long.parseLong(requestMap.get("transferFromAccountNumber")));
                        if (savingsAccount.getAccountBalance().intValue() >= Integer.parseInt(requestMap.get("amount"))) {
//                            finalAmount = primaryAccount.getAccountBalance().add(new BigDecimal(requestMap.get("amount")));
//                            primaryAccount.setAccountBalance(finalAmount);
//                            primaryAccountDao.save(primaryAccount);
//
//                            finalAmount = savingsAccount.getAccountBalance().subtract(new BigDecimal(requestMap.get("amount")));
//                            savingsAccount.setAccountBalance(finalAmount);
//                            savingsAccountDao.save(savingsAccount);

                            processFundMap.put("accountNumber", primaryAccount.getAccountNumber().toString());
                            processFundMap.put("amount", requestMap.get("amount"));
                            processFundMap.put("processType", "deposit");
                            processFund(processFundMap);

                            processFundMap.clear();

                            processFundMap.put("accountNumber", savingsAccount.getAccountNumber().toString());
                            processFundMap.put("amount", requestMap.get("amount"));
                            processFundMap.put("processType", "withdraw");
                            savingAccountServiceInterface.processFund(processFundMap);

                        } else {
                            return new ResponseEntity<>("{\"message\":\"Insufficient Fund.\"}", HttpStatus.BAD_REQUEST);
                        }
                    }
                    return new ResponseEntity<>("{\"message\":\"Amount:- " + requestMap.get("amount") + " Transfer Successful.\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"Cant able to transfer from " + requestMap.get("transferFrom").toUpperCase() + " Account to " + requestMap.get("transferTo").toUpperCase() + " Account\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"Improper data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateRequestMap(Map<String, String> requestMap) {
        return requestMap.containsKey("transferFrom") &&
                requestMap.containsKey("transferTo") &&
                requestMap.containsKey("transferFromAccountNumber") &&
                requestMap.containsKey("transferToAccountNumber") &&
                requestMap.containsKey("amount");
    }

    @Override
    public ResponseEntity<?> transferFromPrimaryToAnother(Map<String, String> requestMap) {
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
