package jpa_board.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa_board.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByLoginId(String loginId);
	boolean existsByLoginId(String loginId);
	
}
