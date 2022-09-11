package com.kazantsev.eng_test.repos;

import com.kazantsev.eng_test.entities.User;
import com.kazantsev.eng_test.entities.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word>findByUser(User user);
}
