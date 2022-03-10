package com.inn.banking.service;

import com.inn.banking.POJO.AppUser;
import com.inn.banking.POJO.PrimaryAccount;
import com.inn.banking.POJO.SavingsAccount;
import com.inn.banking.dao.AppUserDao;
import com.inn.banking.dao.PrimaryAccountDao;
import com.inn.banking.dao.SavingsAccountDao;
import com.inn.banking.utils.BankingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

//@Slf4j
@Service
public class AppUserServiceInterface implements com.inn.banking.serviceInterface.AppUserServiceInterface {

    private Logger log = LogManager.getLogger(AppUserServiceInterface.class);

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Override
    public ResponseEntity<?> signUp(Map<String, String> requestMap) {
        try {
            if (validateSignUpMap(requestMap)) {
                AppUser user = appUserDao.findByusername(requestMap.get("username"));
                log.info("User : {}", user);
                if (Objects.isNull(user)) {
                    Map<String, String> accountMap = createAccoutnNumber();
                    SavingsAccount savingsAccount = new SavingsAccount();
                    savingsAccount.setAccountNumber(Long.parseLong(accountMap.get("primaryAccount")));
                    savingsAccount.setAccountBalance(new BigDecimal(0.0));
                    PrimaryAccount primaryAccount = new PrimaryAccount();
                    primaryAccount.setAccountNumber(Long.parseLong(accountMap.get("savingAccount")));
                    primaryAccount.setAccountBalance(new BigDecimal(0.0));

                    AppUser appUser = new AppUser();
                    appUser.setFirstName(requestMap.get("firstname"));
                    appUser.setLastName(requestMap.get("lastname"));
                    appUser.setEmail(requestMap.get("email"));
                    appUser.setPhone(requestMap.get("phone"));
                    appUser.setUsername(requestMap.get("username"));
                    appUser.setPassword(requestMap.get("password"));
                    appUser.setEnabled(Boolean.TRUE);
                    appUser.setRole("user");
                    appUser.setSavingsAccount(savingsAccountDao.save(savingsAccount));
                    appUser.setPrimaryAccount(primaryAccountDao.save(primaryAccount));

                    appUserDao.save(appUser);
                    return new ResponseEntity<>("{\"message\":\"SignUp Successfully.\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"Username already exist.\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"Improper data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        //    username,password,firstname,lastname,email,phone,
        return requestMap.containsKey("username") && requestMap.containsKey("password") && requestMap.containsKey("firstname") && requestMap.containsKey("lastname") && requestMap.containsKey("email") && requestMap.containsKey("phone");
    }

    private Map<String, String> createAccoutnNumber() {
        Map<String, String> accountMap = new HashMap<>();
        try {
            String generatedAccount = BankingUtils.getUUID();
            log.info("generatedAccount : {}", generatedAccount);
            String primaryAccount = generatedAccount + "0";
            String savingAccount = generatedAccount + "1";
            accountMap.put("primaryAccount", primaryAccount);
            accountMap.put("savingAccount", savingAccount);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return accountMap;
    }

    @Override
    public ResponseEntity<?> getUserProfile(Integer userId) {
        try {
            return new ResponseEntity<>(appUserDao.findById(userId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateProfile(Map<String, String> requestMap) {
        try {
            if (validateUpdateProfileMap(requestMap)) {
                Optional<AppUser> appUser = appUserDao.findById(Integer.parseInt(requestMap.get("userId")));
                if (!Objects.isNull(appUser)) {
                    appUserDao.updateProfile(requestMap.get("firstname"), requestMap.get("lastname"), requestMap.get("phone"), requestMap.get("email"), requestMap.get("username"), Integer.parseInt(requestMap.get("userId")));
                    return new ResponseEntity<>("{\"message\":\"Profile Updated Successfully.\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"User Doesnt Exist.\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"Improper data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateUpdateProfileMap(Map<String, String> requestMap) {
        return requestMap.containsKey("firstname") && requestMap.containsKey("lastname") && requestMap.containsKey("phone") && requestMap.containsKey("email") && requestMap.containsKey("username") && requestMap.containsKey("userId");
    }

    @Override
    public ResponseEntity<?> login(Map<String, String> requestMap) {
        try {
            AppUser appUser = appUserDao.findByUsernameAndPasswordAndEnabled(requestMap.get("username"), requestMap.get("password"), true);
            if (!Objects.isNull(appUser)) {
                appUser.setPassword(null);
                return new ResponseEntity<>(appUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"message\":\"Bad Credentials.\"}", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllUserProfile() {
        try {
        	return new ResponseEntity<>(appUserDao.findByRole("user"), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> enableDisableUser(String status, Integer userId) {
        try {
            appUserDao.enableDisableUser(Boolean.parseBoolean(status), userId);
            String message = "";
            if (Boolean.parseBoolean(status)) {
                message = "{\"message\":\"Account Enabled.\"}";
            } else {
                message = "{\"message\":\"Account Disabled.\"}";
            }
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
