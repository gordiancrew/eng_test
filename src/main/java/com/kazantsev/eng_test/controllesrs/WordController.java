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

@Controller
public class WordController {
    @Autowired
    private WordRepository wordRepository;

    @GetMapping("/viewwords")
    public String viewwords(Model model) {
        List words = wordRepository.findAll();
        model.addAttribute("words", words);
        return "viewwords";
    }

    @GetMapping("/addword")
    public String addword(){

        return "addword";
    }

    @PostMapping("/addword")
    public String pastWord(Model model, @RequestParam String engword, @RequestParam String rusword){
        Word word=new Word();
        word.setEngWord(engword);
        word.setRusWord(rusword);
        word.setCountPass(0);
        wordRepository.save(word);
        return "redirect:/viewwords";
    }

}
