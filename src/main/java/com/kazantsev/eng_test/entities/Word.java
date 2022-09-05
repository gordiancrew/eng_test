package com.kazantsev.eng_test.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String engWord;
    private String rusWord;
    private int countPass;

    @ManyToOne
    private User user;

}
