package jpa_board.board.service;


import jpa_board.board.domain.Member;

public interface MemberService {
    void signup(Member member);
    Member login(String loginId, String password);
    void deleteMember(Long memberId);
    boolean existsByLoginId(String loginId);
}
