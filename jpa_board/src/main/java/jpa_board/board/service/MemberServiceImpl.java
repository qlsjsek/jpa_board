package jpa_board.board.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jpa_board.board.domain.Member;
import jpa_board.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public void signup(Member member) { 
    	member.setPassword(passwordEncoder.encode(member.getPassword()));
    	memberRepository.save(member); 
    }

    @Override
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> passwordEncoder.matches(password, m.getPassword()))
                .orElse(null);
    }

    @Override
    public void deleteMember(Long memberId) { 
    	Member member = memberRepository.findById(memberId).orElse(null);
    	if(member != null) {
    		member.setDeleted(true);
    		memberRepository.save(member);
    	}
    }

	@Override
	public boolean existsByLoginId(String loginId) {
		return memberRepository.existsByLoginId(loginId);
	}
}
