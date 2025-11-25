package com.example.GReads_Clone.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ApiService {

    private final WebClient webClient;
    @Autowired
    BookRepository bookRepository;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Book fetchBookByIsbn(String isbn) {
        // Call Google Books API
        GoogleBooksResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", "isbn:" + isbn)
                        .build())
                .retrieve()
                .bodyToMono(GoogleBooksResponse.class)
                .block();

        if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
            return null; // No book found
        }

        GoogleBooksResponse.VolumeInfo volumeInfo = response.getItems().get(0).getVolumeInfo();

        // Find ISBN_13 if exists
        String isbn13 = isbn;
        if (volumeInfo.getIndustryIdentifiers() != null) {
            for (GoogleBooksResponse.IndustryIdentifier id : volumeInfo.getIndustryIdentifiers()) {
                if ("ISBN_13".equals(id.getType())) {
                    isbn13 = id.getIdentifier();
                    break;
                }
            }
        }

        // Map to your Book entity
        Book book = new Book();
        book.setIsbn(isbn13);
        book.setTitle(volumeInfo.getTitle());
        book.setAuthor(volumeInfo.getAuthors() != null ? String.join(", ", volumeInfo.getAuthors()) : "Unknown");


        return book;
    }

    public String createBook(String isbn){
        Book book = fetchBookByIsbn(isbn);
        if(!bookRepository.existsById(book.getIsbn())){
            bookRepository.save(book);
            return "Book "+ book.getTitle()+" added Successfully";
        }
        else{
            return "Book exists";
        }
    }
    public Book fetchBookFromDB(String isbn){
        if(bookRepository.existsById(isbn)){
            createBook(isbn);
        };
        return bookRepository.findById(isbn).get();
    }

    public void deleteBook(String isbn){
        if(bookRepository.existsById(isbn)) {
            bookRepository.deleteById(isbn);
        }
    }

}
