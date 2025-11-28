package com.example.GReads_Clone.ReadingLists;

import com.example.GReads_Clone.Book.Book;
import com.example.GReads_Clone.enums.bookStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
@Entity
@Setter
@Getter
public class ReadingListEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reading_list_id")
    @JsonBackReference //to prevent infinite recursion (ignore this side)
    private ReadingList readingList;

    @ManyToOne
    @JoinColumn(name = "book_isbn", nullable = false)

    private Book book;

    private int currentProgress;
    @Enumerated(EnumType.STRING)
    private bookStatus status;
    private LocalDateTime addedAt = LocalDateTime.now();
}

