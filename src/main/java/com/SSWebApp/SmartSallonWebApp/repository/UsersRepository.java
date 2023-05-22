package com.SSWebApp.SmartSallonWebApp.repository;

import com.SSWebApp.SmartSallonWebApp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SSWebApp.SmartSallonWebApp.models.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUserName(String username);

    List<Users> findAllByUserName(String username);
}
