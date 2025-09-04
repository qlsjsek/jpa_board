package jpa_board.board.dto;

import lombok.Data;

@Data
public class SignUpDTO {
	private Long memberId;
	private String loginId;
	private String name;
	private String password;
	private String confirmPassword;
}
