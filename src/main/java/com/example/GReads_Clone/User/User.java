package com.example.GReads_Clone.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "user_sequence")
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true)
    private String email;
    @Column
    private String name;
    @Column(nullable = false)
    private String password;


    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
