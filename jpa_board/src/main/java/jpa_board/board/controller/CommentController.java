package jpa_board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jpa_board.board.domain.Board;
import jpa_board.board.domain.Comment;
import jpa_board.board.domain.Member;
import jpa_board.board.service.BoardService;
import jpa_board.board.service.CommentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/board/{boardId}/comment")
    public String addComment(@PathVariable("boardId") Long boardId, @RequestParam("content") String content, HttpSession session) {
        Member member = (Member) session.getAttribute("loginUser");
        Board board = boardService.findById(boardId);

        Comment comment = Comment.builder()
                .content(content)
                .member(member)
                .board(board)
                .build();
        commentService.save(comment);
        return "redirect:/board/" + boardId;
    }

    @PostMapping("/comment/{commentId}/delete")
    public String deleteComment(@PathVariable("commentId") Long commentId, HttpSession session) {
        Member member = (Member) session.getAttribute("loginUser");
        Comment comment = commentService.findByBoardId(commentId).stream()
                .filter(c -> c.getCommentId().equals(commentId)).findFirst().orElseThrow();
        if(comment.getMember().getMemberId().equals(member.getMemberId())) {
            commentService.delete(commentId);
        }
        return "redirect:/board/" + comment.getBoard().getBoardId();
    }
}
