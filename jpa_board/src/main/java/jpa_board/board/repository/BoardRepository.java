package jpa_board.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa_board.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {}
