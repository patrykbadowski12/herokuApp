package com.studia.HerokuApp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String picture;
    private LocalDateTime joined;
    private LocalDateTime lastVisit;
    private Integer counter;

    public User(String name, String picture, LocalDateTime joined, LocalDateTime lastVisit, Integer counter) {
        this.name = name;
        this.picture = picture;
        this.joined = joined;
        this.lastVisit = lastVisit;
        this.counter = counter;
    }
}
