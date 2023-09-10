package com.example.thymeleafboard.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String boardList(Model model, @PageableDefault Pageable pageable) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("list", boards);
        // model.addAttribute 로 넘겨줘야 thymeleaf 로 사용 가능핟
//        int size = boards.size();
        return "board/list";
    }
}
