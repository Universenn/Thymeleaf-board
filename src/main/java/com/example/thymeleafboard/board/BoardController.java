package com.example.thymeleafboard.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class  BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String boardList(Model model, @PageableDefault Pageable pageable) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("list", boards);
        // model.addAttribute 로 넘겨줘야 thymeleaf 로 사용 가능핟
//        int size = boards.size();
        return "board/list";
    }


    @GetMapping("/post")
    public String boardGetPost(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElseThrow(null);
            model.addAttribute("board", board);
        }
        return "board/post";
    }


    @PostMapping("/post")
        public String boardPost(@Valid Board board,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/post";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
