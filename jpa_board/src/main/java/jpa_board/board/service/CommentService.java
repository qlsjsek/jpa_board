package jpa_board.board.service;

import java.util.List;

import jpa_board.board.domain.Comment;

public interface CommentService {
    Comment save(Comment comment);
    void delete(Long id);
    List<Comment> findByBoardId(Long boardId);
}
