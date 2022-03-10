package com.inn.banking.dao;

import com.inn.banking.POJO.AppUser;
import com.inn.banking.POJO.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RecipientDao extends JpaRepository<Recipient, Integer> {

    Recipient findByaccountNumberAndUserId(@Param("accountNumber") String accountNumber,
                                           @Param("userId") Integer userId);

    List<Recipient> getRecipientByUserId(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    Integer updateRecipient(@Param("name") String name,
                            @Param("email") String email,
                            @Param("phone") String phone,
                            @Param("description") String description,
                            @Param("recipientId") Integer recipientId);

}
