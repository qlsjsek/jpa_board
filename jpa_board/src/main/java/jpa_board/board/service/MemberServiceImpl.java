package jpa_board.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jpa_board.board.domain.Member;
import jpa_board.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void signup(Member member) { memberRepository.save(member); }

    @Override
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    @Override
    public void deleteMember(Long memberId) { memberRepository.deleteById(memberId); }

	@Override
	public boolean existsByLoginId(String loginId) {
		return memberRepository.existsByLoginId(loginId);
	}
}
