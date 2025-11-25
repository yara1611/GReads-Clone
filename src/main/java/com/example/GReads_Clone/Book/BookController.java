package com.example.GReads_Clone.Book;

import com.example.GReads_Clone.BaseReadingListEntry;
import com.example.GReads_Clone.ReadingNow;
import com.example.GReads_Clone.ReadingNowService;
import com.example.GReads_Clone.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private ApiService apiService;
    @Autowired
    ReadingNowService readingNowService;

    @GetMapping("/{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return apiService.fetchBookByIsbn(isbn);
    }

    @PostMapping("/addBook/{isbn}")
    public ResponseEntity<Map<String,Object>> addBook(@PathVariable String isbn){

        String message =  apiService.createBook(isbn);
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("Message", message);
        responseData.put("statusCode",200);
        return ResponseEntity.ok().body(responseData);
    }
    @DeleteMapping("/deleteBook/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
         apiService.deleteBook(isbn);
         return ResponseEntity.ok().body("Book with id <<"+isbn+">> deleted successfully");
    }

    @PostMapping("/createReadingList")
    public ResponseEntity<String> createRL(@RequestParam Long userId){
        readingNowService.createList(userId);
        return ResponseEntity.ok().body("Created Successfully");
    }

    @PostMapping("/addBookToList")
    public ResponseEntity<String> addToList(@RequestParam Long listId, @RequestParam String isbn){
        readingNowService.addBookToList(listId,isbn);
        return ResponseEntity.ok().body("Added Successfully");
    }
    @GetMapping("/getBooksInList")
    public List<BaseReadingListEntry> getAllBooks(@RequestParam Long listId){
        return readingNowService.getAllEntries(listId);
    }
}
