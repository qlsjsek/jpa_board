package jpa_board.board.service;

import jpa_board.board.domain.Board;
import jpa_board.board.domain.Member;

public interface LikeService {
    void toggleLike(Board board, Member member);
    int getLikeCount(Long boardId);
}
