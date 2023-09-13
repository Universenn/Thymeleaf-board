package com.example.thymeleafboard.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAll(Pageable pageable);

    Page<Board> findByTitle(Pageable pageable,String title);
//    Page<Board> findByTitleLike(Pageable pageable,String title);
//    Page<Board> getFirstByTitle(Pageable pageable,String title);
    Page<Board> findFirstByTitle(Pageable pageable,String title);


}
