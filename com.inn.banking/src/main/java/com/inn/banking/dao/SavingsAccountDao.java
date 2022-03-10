package com.inn.banking.dao;

import com.inn.banking.POJO.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountDao extends JpaRepository<SavingsAccount, Integer> {

    SavingsAccount findByaccountNumber(Long accountNumber);

}
