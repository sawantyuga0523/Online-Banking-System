package com.inn.banking.dao;

import com.inn.banking.POJO.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

public interface AppUserDao extends JpaRepository<AppUser, Integer> {

    @Transactional
    @Modifying
    Integer updateProfile(@Param("firstname") String firstname,
                          @Param("lastname") String lastname,
                          @Param("phone") String phone,
                          @Param("email") String email,
                          @Param("username") String username,
                          @Param("userId") Integer userId);

    AppUser findByusername(String email);

    AppUser findByUsernameAndPasswordAndEnabled(String username, String password, Boolean enabled);

//    @NamedQuery(name = "AppUser.enableDisableUser", query = "update AppUser a set a.enabled=:status where a.id=:userId")

    @Transactional
    @Modifying
    Integer enableDisableUser(@Param("status") boolean status,
                              @Param("userId") Integer userId);
    
    List<AppUser> findByRole(String role);

}
