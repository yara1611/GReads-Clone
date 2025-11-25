package com.example.GReads_Clone;

import com.example.GReads_Clone.Book.Book;
import com.example.GReads_Clone.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Entity
public class BaseReadingListEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reading_now_list_id")
    @JsonBackReference //to prevent infinite recursion (ignore this side)
    private ReadingNow readingNowList;

    @ManyToOne
    @JoinColumn(name = "book_isbn", nullable = false)

    private Book book;

    private LocalDateTime addedAt = LocalDateTime.now();
}
