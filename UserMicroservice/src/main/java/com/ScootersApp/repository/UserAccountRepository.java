package com.ScootersApp.repository;

import com.ScootersApp.domain.Account;
import com.ScootersApp.domain.User;
import com.ScootersApp.domain.UserAccount;
import com.ScootersApp.domain.UserAccountID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UserAccountID> {

    @Query("SELECT ua FROM UserAccount ua WHERE ua.id.user.ID =:id")
    List<UserAccount> findAllById_User(@Param("id") Long id);

    @Query("SELECT ua FROM UserAccount ua WHERE ua.id.account.id =:id")
    List<UserAccount> findAllById_Account(@Param("id") Long id);

    @Query("SELECT ua FROM UserAccount ua WHERE ua.id.user.ID =:idUser AND ua.id.account.id =:idAccount")
    UserAccount findById(@Param("idUser") Long idUser, @Param("idAccount") Long idAccount);
    void deleteById_UserAndId_Account(User u, Account account);
}
