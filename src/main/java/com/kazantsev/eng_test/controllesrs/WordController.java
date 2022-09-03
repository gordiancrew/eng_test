package com.kazantsev.eng_test.controllesrs;

import com.kazantsev.eng_test.repos.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WordController {
    @Autowired
    WordRepository wordRepository;
    @GetMapping("/viewwords")
    public String viewwords(Model model){
List words=wordRepository.findAll();
model.addAttribute("words",words);
        return "viewwords";
    }

}
