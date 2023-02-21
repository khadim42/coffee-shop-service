package com.kh.coffeeshopservice.repository;



import com.kh.coffeeshopservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String userName);


}
