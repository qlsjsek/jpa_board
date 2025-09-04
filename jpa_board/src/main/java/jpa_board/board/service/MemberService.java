package jpa_board.board.service;

import jpa_board.board.dto.SignUpDTO;

public interface MemberService {
	boolean checkMemberExist(Long memberId);
	void save(SignUpDTO signUpDTO);
	boolean checksSingUpSamePassword(SignUpDTO signUpDTO);
}
