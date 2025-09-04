package jpa_board.board.service;

import java.util.List;

import jpa_board.board.domain.Board;
import jpa_board.board.domain.Member;

public interface BoardService {
    Board save(Board board, Member writer);
    List<Board> findAll();
    Board findById(Long id);
    void delete(Long id);
    void update(Board board);
}
