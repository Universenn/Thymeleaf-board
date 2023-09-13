package com.example.thymeleafboard.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardRepository boardRepository;

    @PostMapping()
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @GetMapping("/{id}")
    public Board findById(@PathVariable("id") Long id) {
        return boardRepository.findById(id).orElseThrow(null);
    }


    @GetMapping("/title/{title}")
    public Page<Board> findByTitle(@PathVariable String title, @PageableDefault(size = 3) Pageable pageable) {
        if (title.isEmpty()) {
            return boardRepository.findAll(pageable);
        } else {
//            return boardRepository.findByTitle(pageable,title);
//            return boardRepository.findByTitleLike(pageable,title);
            return boardRepository.findFirstByTitle(pageable,title);
        }
    }

    @GetMapping("")
    public Page<Board> findAll(@PageableDefault(size = 5) Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @PutMapping("/{id}")
    public Board edit(@PathVariable Long id, Board board) {
        Board findId = boardRepository.findById(id).orElseThrow(null);
        Board editBoard = boardRepository.save(findId);
        return editBoard;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Board findId = boardRepository.findById(id).orElseThrow(null);
        boardRepository.delete(findId);
        System.out.println("삭제되었습니다.");
    }
}
