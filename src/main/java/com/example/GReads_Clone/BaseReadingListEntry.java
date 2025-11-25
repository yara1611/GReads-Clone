package com.example.GReads_Clone;

import com.example.GReads_Clone.Book.Book;
import com.example.GReads_Clone.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseReadingListEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_isbn", nullable = false)
    private Book book;

    private LocalDateTime addedAt = LocalDateTime.now();
}
