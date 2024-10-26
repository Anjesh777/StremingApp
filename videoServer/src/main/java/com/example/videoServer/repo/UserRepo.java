package com.example.videoServer.repo;

import com.example.videoServer.dto.UserDTO;
import com.example.videoServer.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {


    Users findByEmail(String email);
    @Query("SELECT new com.example.videoServer.dto.UserDTO(u.city, u.district, u.email, u.isVerified, u.username) FROM Users u WHERE u.email = :email")
    //!! package name should be give when custom jpa query is used
    public UserDTO findByEmailExcludingPassword(@Param("email") String email);


}
