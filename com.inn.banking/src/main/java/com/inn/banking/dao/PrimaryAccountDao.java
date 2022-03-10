package com.inn.banking.dao;

import com.inn.banking.POJO.PrimaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryAccountDao extends JpaRepository<PrimaryAccount, Integer> {

    PrimaryAccount findByaccountNumber(Long accountNumber);

}
