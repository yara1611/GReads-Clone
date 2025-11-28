package com.example.GReads_Clone.ReadingLists;

import com.example.GReads_Clone.Book.ApiService;
import com.example.GReads_Clone.User.UserService;
import com.example.GReads_Clone.enums.ReadingListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReadingListService {
    @Autowired
    ReadingListRepository readingListRepository;
    @Autowired
    ReadingListEntryRepository readingListEntryRepository;
    @Autowired
    UserService userService;
    @Autowired
    ApiService bookService;

    public void createList(Long userId, ReadingListType type){
        ReadingList readingNow = new ReadingList();
        readingNow.setUser(userService.getUser(userId));
        readingNow.setListType(type);
        readingListRepository.save(readingNow);
    }

    //created for each user once and never again
    public void createUserBaseLists(Long userId){
        if(checkUserList(userId)){
            System.out.println("Once is enough");
        }
        else {
            createList(userId, ReadingListType.READING_NOW);
            createList(userId, ReadingListType.WANT_TO_READ);
            createList(userId, ReadingListType.FINISHED);
        }
    }

    public boolean checkUserList(Long userId){
       if(readingListRepository.existsByUserIdAndListType(userId,ReadingListType.READING_NOW)
       |readingListRepository.existsByUserIdAndListType(userId,ReadingListType.WANT_TO_READ)
       | readingListRepository.existsByUserIdAndListType(userId,ReadingListType.FINISHED)){
           return true;
       }
       return false;
    }
    public void createReadingNowList(Long userId){
        createList(userId,ReadingListType.READING_NOW);
    }
    //adds new entry
    public void addBookToList(Long listId, String isbn){

       ReadingList list = readingListRepository.findById(listId).get();
       ReadingListEntry entry = new ReadingListEntry();
       entry.setBook(bookService.fetchBookFromDB(isbn));
       entry.setAddedAt(LocalDateTime.now());
       //from helper functions
       list.addEntry(entry);
       readingListEntryRepository.save(entry);

       System.out.println("Added to list successfully");
    }

    public void createEntry(String isbn){

    }
    public List<ReadingListEntry> getAllEntries(Long listId){
        List<ReadingListEntry> entries = readingListRepository.findById(listId).get().getEntries();
        return entries;
    }

}
