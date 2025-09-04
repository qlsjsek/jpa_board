package jpa_board.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa_board.board.domain.Board;
import jpa_board.board.domain.BoardLike;
import jpa_board.board.domain.Member;

public interface LikeRepository extends JpaRepository<BoardLike, Long> {
	Optional<BoardLike> findByBoardAndMember(Board board, Member member);
    int countByBoard_BoardId(Long boardId);
}