package jpa_board.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpa_board.board.domain.Member;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
}
