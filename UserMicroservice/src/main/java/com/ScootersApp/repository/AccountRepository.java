package com.ScootersApp.repository;

import com.ScootersApp.domain.Account;
import com.ScootersApp.domain.UserAccountID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

}
