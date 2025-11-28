package com.example.GReads_Clone.ReadingLists;

import com.example.GReads_Clone.User.User;
import com.example.GReads_Clone.enums.ReadingListType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "readingList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference //serialize this side
    private List<ReadingListEntry> entries = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ReadingListType listType;

    public void addEntry(ReadingListEntry entry) {
        entry.setReadingList(this);
        entries.add(entry);
    }

    public void removeEntry(ReadingListEntry entry) {
        entries.remove(entry);
        entry.setReadingList(null);
    }
}


