package com.example.GReads_Clone.Book;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class GoogleBooksResponse {
    private List<Item> items;

    @Data
    public static class Item {
        private VolumeInfo volumeInfo;
    }

    @Data
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private List<IndustryIdentifier> industryIdentifiers;
        private Integer pageCount;
        private String language;
    }

    @Data
    public static class IndustryIdentifier {
        private String type;       // e.g., "ISBN_10" or "ISBN_13"
        private String identifier; // the actual number
    }
}
