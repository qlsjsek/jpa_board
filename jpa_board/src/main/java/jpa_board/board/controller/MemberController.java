package jpa_board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jpa_board.board.domain.Member;
import jpa_board.board.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/signup")
	public String signupForm() { return "signup"; }

	@PostMapping("/signup")
	public String signup(Member member) {
		if(memberService.existsByLoginId(member.getLoginId())) {
			return "signup";
		}
		memberService.signup(member);
		return "redirect:/login";
		
	}

	@GetMapping("/login")
	public String loginForm() { return "login"; }

	@PostMapping("/login")
	public String login(@RequestParam("loginId") String loginId,
	                    @RequestParam("password") String password,
	                    HttpSession session) {
	    Member member = memberService.login(loginId, password);
	    if(member != null) {
	        session.setAttribute("loginUser", member);
	        return "redirect:/";
	    }
	    return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}

	@PostMapping("/deleteMember")
	public String deleteMember(HttpSession session) {
	    Member member = (Member) session.getAttribute("loginUser");
	    if(member != null) {
	        memberService.deleteMember(member.getMemberId());
	        session.invalidate();
	    }
	    return "redirect:/";
	}
	
	@GetMapping("/checkId")
	@ResponseBody
	public boolean checkId(@RequestParam("loginId") String loginId) {
		return memberService.existsByLoginId(loginId);
	}
	
}
