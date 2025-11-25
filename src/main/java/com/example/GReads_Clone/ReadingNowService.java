package com.example.GReads_Clone;

import com.example.GReads_Clone.Book.ApiService;
import com.example.GReads_Clone.Book.BookService;
import com.example.GReads_Clone.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingNowService {
    @Autowired
    ReadingNowRepository readingNowRepository;
    @Autowired
    BaseReadingListEntryRepository baseReadingListEntryRepository;
    @Autowired
    UserService userService;
    @Autowired
    ApiService bookService;

    public void createList(Long userId){
        ReadingNow readingNow = new ReadingNow();
        readingNow.setUser(userService.getUser(userId));
        readingNowRepository.save(readingNow);
    }

    //adds new entry
    public void addBookToList(Long listId, String isbn){
       ReadingNow list = readingNowRepository.findById(listId).get();
       BaseReadingListEntry entry = new BaseReadingListEntry();
       entry.setBook(bookService.fetchBookFromDB(isbn));
       entry.setReadingNowList(list);
       entry.setAddedAt(LocalDateTime.now());
       baseReadingListEntryRepository.save(entry);

       System.out.println("Added to list successfully");
    }

    public List<BaseReadingListEntry> getAllEntries(Long listId){
        List<BaseReadingListEntry> entries = readingNowRepository.findById(listId).get().getBooks();
        return entries;
    }

}
