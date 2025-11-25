package com.example.GReads_Clone;

import com.example.GReads_Clone.User.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Setter
@Getter
public class ReadingNow{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "readingNowList", cascade = CascadeType.ALL)
    @JsonManagedReference //serialize this side
    private List<BaseReadingListEntry> books = new ArrayList<>();
}
