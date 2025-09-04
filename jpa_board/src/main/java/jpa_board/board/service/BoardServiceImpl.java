package jpa_board.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jpa_board.board.domain.Board;
import jpa_board.board.domain.Member;
import jpa_board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public Board save(Board board, Member writer) {
        board.setWriter(writer);
        return boardRepository.save(board);
    }

    @Override
    public List<Board> findAll() { return boardRepository.findAll(); }

    @Override
    public Board findById(Long id) { return boardRepository.findById(id).orElseThrow(); }

    @Override
    public void delete(Long id) { boardRepository.deleteById(id); }

	@Override
	public void update(Board board) {
		boardRepository.save(board);
		
	}
}
