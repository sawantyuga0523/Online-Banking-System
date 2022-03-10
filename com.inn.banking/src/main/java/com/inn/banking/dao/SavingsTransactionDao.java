package com.inn.banking.dao;

import com.inn.banking.POJO.PrimaryTransaction;
import com.inn.banking.POJO.SavingsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SavingsTransactionDao extends JpaRepository<SavingsTransaction, Integer> {

    List<SavingsTransaction> getSavingTransactionByAccountNo(@Param("accountNumber") Long accountNumber);

}
