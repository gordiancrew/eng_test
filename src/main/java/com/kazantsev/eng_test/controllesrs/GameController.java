package com.kazantsev.eng_test.controllesrs;

import com.kazantsev.eng_test.entities.User;
import com.kazantsev.eng_test.entities.Word;
import com.kazantsev.eng_test.repos.UserRepository;
import com.kazantsev.eng_test.repos.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GameController {
    private String WORD_EQUALS = "GOOOOOOOOD JOB";
    private String WORD_MISTAKE = "BAD";
    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/game")
    public String gameplace(Model model, HttpSession session) {
        if (session.getAttribute("gamecount") == null) {
            session.setAttribute("gamecount", 0);
        }
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        List<Word> wordsList = wordRepository.findByUser(user);
        int numberWords = wordsList.size();
        int randomWord = (int) (Math.random() * numberWords);
        Word word = wordsList.get(randomWord);
        model.addAttribute("word", word);
        return "gameplace";
    }

    @PostMapping("/game")
    public String gamePost(HttpSession session, Model model, @RequestParam String answer, @RequestParam String id) {
        String result = WORD_MISTAKE;
        int progressCountPass = -1;
        Word word = wordRepository.getById(Integer.parseInt(id));
        if (word.getRusWord().equals(answer)) {
            result = WORD_EQUALS;
            progressCountPass = 1;
        }
        int newGameCount = (int) session.getAttribute("gamecount") + progressCountPass;
        session.setAttribute("gamecount", newGameCount);
        word.setCountPass(word.getCountPass() + progressCountPass);
        model.addAttribute("answer", answer);
        model.addAttribute("result", result);
        model.addAttribute("word", word);
        wordRepository.save(word);
        if (word.getCountPass() == 10) {
            wordRepository.delete(word);
        }
        return "gameresult";
    }
}
