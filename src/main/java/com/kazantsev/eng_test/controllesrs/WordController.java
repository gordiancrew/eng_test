package com.kazantsev.eng_test.controllesrs;

import com.kazantsev.eng_test.entities.User;
import com.kazantsev.eng_test.entities.Word;
import com.kazantsev.eng_test.repos.UserRepository;
import com.kazantsev.eng_test.repos.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WordController {
    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/viewwords")
    public String viewwords(Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        List words = wordRepository.findByUser(user);
        model.addAttribute("words", words);
        return "viewwords";
    }

    @GetMapping("/addword")
    public String addword(HttpSession session) {
        System.out.println(session.getAttribute("ses"));
        return "addword";
    }

    @PostMapping("/addword")
    public String pastWord(Model model, @RequestParam String engword, @RequestParam String rusword) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        Word word = new Word();
        word.setEngWord(engword);
        word.setRusWord(rusword);
        word.setCountPass(0);
        word.setUser(user);
        wordRepository.save(word);
        return "redirect:/viewwords";
    }

}
