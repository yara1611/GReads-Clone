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

    @PostMapping("/getUser")
    public ResponseEntity<String> getUser(@RequestBody Long userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok().body(user.toString());
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("Successfully delete user <"+userId+">");
    }



//        @DeleteMapping("/{userId}/entries/{entryId}")
//        public ResponseEntity<String> removeEntry(@PathVariable Long userId,
//                                                  @PathVariable Long entryId) {
//            readingListService.removeEntryFromList(userId, entryId);
//            return ResponseEntity.ok("Entry removed successfully");
//        }



}
