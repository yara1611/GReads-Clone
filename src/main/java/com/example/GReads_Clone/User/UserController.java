package com.example.GReads_Clone.User;

import com.example.GReads_Clone.ReadingLists.ReadingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReadingListService readingListService;


    @GetMapping("/")
    public List<User> home() {
        return userService.getUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.createUser(user);
        readingListService.createUserBaseLists(user.getId());
        return ResponseEntity.ok().body(user.toString());
    }

}
