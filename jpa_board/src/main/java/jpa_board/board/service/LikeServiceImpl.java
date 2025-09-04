package jpa_board.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jpa_board.board.domain.Board;
import jpa_board.board.domain.BoardLike;
import jpa_board.board.domain.Member;
import jpa_board.board.repository.BoardRepository;
import jpa_board.board.repository.LikeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;

    @Override
    public void toggleLike(Board board, Member member) {
        Optional<BoardLike> existing = likeRepository.findByBoardAndMember(board, member);
        if(existing.isPresent()) {
            likeRepository.delete(existing.get());
        } else {
            BoardLike like = BoardLike.builder().board(board).member(member).build();
            likeRepository.save(like);
        }
    }

    @Override
    public int getLikeCount(Long boardId) {
        return likeRepository.countByBoard_BoardId(boardId);
    }
}
