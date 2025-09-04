package jpa_board.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jpa_board.board.domain.Comment;
import jpa_board.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) { return commentRepository.save(comment); }

    @Override
    public void delete(Long id) { commentRepository.deleteById(id); }

    @Override
    public List<Comment> findByBoardId(Long boardId) {
        return commentRepository.findByBoard_BoardId(boardId);
    }
}