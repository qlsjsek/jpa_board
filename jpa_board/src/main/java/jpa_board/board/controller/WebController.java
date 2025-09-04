package jpa_board.board.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebController {
	@GetMapping("/signup")
	public String signup(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Optional<Object> idOptional = Optional.ofNullable(session.getAttribute("userId"));
		if(idOptional.isPresent()) {
			return "redirect:/member/list";
		}
		return "signup";
	}
}
