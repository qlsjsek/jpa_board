package jpa_board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jpa_board.board.domain.Board;
import jpa_board.board.domain.Member;
import jpa_board.board.service.BoardService;
import jpa_board.board.service.LikeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final LikeService likeService;
	
	@GetMapping("/board")
	public String boardList(Model model) {
	    model.addAttribute("boards", boardService.findAll());
	    return "board/list";
	}

	@GetMapping("/board/{boardId}")
	public String boardDetail(@PathVariable("boardId") Long boardId, Model model, HttpSession session) {
	    Board board = boardService.findById(boardId);
	    model.addAttribute("board", board);
	    model.addAttribute("comments", board.getComments());
	    model.addAttribute("likeCount", board.getLikes().size());
	    
        Member loginUser = (Member) session.getAttribute("loginUser");
        boolean isAuthor = loginUser != null && loginUser.getMemberId().equals(board.getWriter().getMemberId());
        model.addAttribute("isAuthor", isAuthor);
        
	    return "board/detail";
	}

	@GetMapping("/board/new")
	public String createForm() { 
		return "board/form"; 
	}
	
	@GetMapping("/board/{boardId}/edit")
	public String editForm(@PathVariable("boardId") Long boardId, HttpSession session, Model model) {
	    Board board = boardService.findById(boardId);
	    Member loginUser = (Member) session.getAttribute("loginUser");

	    if (loginUser == null || !loginUser.getMemberId().equals(board.getWriter().getMemberId())) {
	        return "redirect:/board/" + boardId; 
	    }

	    model.addAttribute("board", board);
	    return "board/edit"; 
	}

	@PostMapping("/board/new")
	public String create(Board board, HttpSession session) {
	    Member member = (Member) session.getAttribute("loginUser");
	    boardService.save(board, member);
	    return "redirect:/board";
	}

	@PostMapping("/board/{boardId}/delete")
	public String delete(@PathVariable("boardId") Long boardId, HttpSession session) {
	    Member member = (Member) session.getAttribute("loginUser");
	    Board board = boardService.findById(boardId);
	    if(board.getWriter().getMemberId().equals(member.getMemberId())) {
	        boardService.delete(boardId);
	    }
	    return "redirect:/board";
	}
	
    @PostMapping("/board/{boardId}/edit")
    public String edit(@PathVariable("boardId") Long boardId, @RequestParam("title") String title,
                       @RequestParam("content") String content, HttpSession session) {
        Board board = boardService.findById(boardId);
        Member loginUser = (Member) session.getAttribute("loginUser");

        if (loginUser != null && loginUser.getMemberId().equals(board.getWriter().getMemberId())) {
            board.setTitle(title);
            board.setContent(content);
            boardService.update(board);
        }

        return "redirect:/board/" + boardId;
    }
	
	  @PostMapping("/board/{boardId}/like")
	    public String toggleLike(@PathVariable("boardId") Long boardId, HttpSession session) {
	        Member member = (Member) session.getAttribute("loginUser");
	        if(member == null) return "redirect:/login";

	        Board board = boardService.findById(boardId);
	        likeService.toggleLike(board, member);

	        return "redirect:/board/" + boardId;
	    }
}
