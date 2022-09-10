package com.kazantsev.eng_test.controllesrs;

import com.kazantsev.eng_test.entities.Word;
import com.kazantsev.eng_test.repos.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class GameController {
    private String WORD_EQUALS = "GOOOOOOOOD JOB";
    private String WORD_MISTAKE = "BAD";
    @Autowired
    private WordRepository wordRepository;


    @GetMapping("/game")
    public String gameplace(Model model) {
        List<Word> wordsList = wordRepository.findAll();
        int numberWords = wordsList.size();
        int randomWord = (int) (Math.random() * numberWords);
        Word word = wordsList.get(randomWord);
        model.addAttribute("word", word);

        return "gameplace";
    }

    @PostMapping("/game")
    public String gamePost(Model model, @RequestParam String answer, @RequestParam String id) {
        String result = WORD_MISTAKE;
        Word word = wordRepository.getById(Integer.parseInt(id));
        if (word.getRusWord().equals(answer)) {
            result = WORD_EQUALS;
        }
        model.addAttribute("answer",answer);
        model.addAttribute("result", result);
        model.addAttribute("word", word);
        return "gameresult";
    }
}
