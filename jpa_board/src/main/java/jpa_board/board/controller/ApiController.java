package jpa_board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jpa_board.board.dto.SignUpDTO;
import jpa_board.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class ApiController {
	private MemberService memberService;
	
	@GetMapping("/signup")
	public String main() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(SignUpDTO singUpDTO) {
		boolean isMemberExist = memberService.checkMemberExist(singUpDTO.getMemberId());
		if(isMemberExist) {
			log.error("회원가입 실패");
			return "redirect:/signUp";
		}
		
		boolean isSignUpSamePassword = memberService.checksSingUpSamePassword(singUpDTO);
		if(!isSignUpSamePassword) {
			log.error("회원가입 실패");
			return "redirect:/signUp";
		}
		
		memberService.save(singUpDTO);
		log.info("회원가입 성공");
		return "redirect:/login";
	}
}
