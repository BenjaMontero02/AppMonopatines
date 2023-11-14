package com.ScootersApp.repository;

import com.ScootersApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

     User findByMail(String mail);
     Boolean existsByMail(String mail);

}
