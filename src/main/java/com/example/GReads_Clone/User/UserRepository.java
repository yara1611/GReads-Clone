package com.example.GReads_Clone.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Access DB, Data Access Layer
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
