package jpa_board.board.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jpa_board.board.domain.Member;
import jpa_board.board.dto.SignUpDTO;
import jpa_board.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository = null;
	private final PasswordEncoder passwordEndcoder;

	@Override
	public boolean checkMemberExist(Long memberId) {
		Optional<Member> memberOptional = memberRepository.findById(memberId);
		if(memberOptional.isPresent()) {
			log.error("동일한 아이디가 존재");
			return true;
		} else {
			log.info("동일한 아이디 없음");
			return false;
		}
	}

	@Override
	public void save(SignUpDTO signUpDTO) {
		String encodingPassword = passwordEndcoder.encode(signUpDTO.getPassword());
		signUpDTO.setPassword(encodingPassword);
		log.info("로그인한 ID{}", signUpDTO.getLoginId());
		
		Member member = Member.builder()
						.loginId(signUpDTO.getLoginId())
						.name(signUpDTO.getName())
						.password(encodingPassword)
						.build();
		memberRepository.save(member);
	}

	@Override
	public boolean checksSingUpSamePassword(SignUpDTO signUpDTO) {
		if(!(signUpDTO.getConfirmPassword().equals(signUpDTO.getPassword()))) {
			log.error("비밀번호를 다시 입력해 주세요");
			return false;
		} else {
			log.info("비밀번호 일치");
			return true;
		}
	}
}
