package com.example.GReads_Clone.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void createUser(User user){
        //Optional<User> userOptional = userRepository.
        userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }


    public void deleteUser(Long id){
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student with id ("+id+") doesn't exist");
        }
        userRepository.deleteById(id);
    }


}
