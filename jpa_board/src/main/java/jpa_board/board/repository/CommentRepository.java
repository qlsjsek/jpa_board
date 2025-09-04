package jpa_board.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa_board.board.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByBoard_BoardId(Long boardId);
}
