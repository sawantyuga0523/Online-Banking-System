package com.inn.banking.dao;

import com.inn.banking.POJO.PrimaryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrimaryTransactionDao extends JpaRepository<PrimaryTransaction, Integer> {

    List<PrimaryTransaction> getPrimaryTransactionByAccountNo(@Param("accountNumber") Long accountNumber);
}
