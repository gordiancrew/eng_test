package com.kazantsev.eng_test.repos;

import com.kazantsev.eng_test.entities.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word,Integer> {
}
