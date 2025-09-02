package jpa_board.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class member {
	@Id
	@Column(name = "member_id", nullable = false, length = 20)
	private Long memberId;
	@Column(name= "login_id", nullable = false, length = 20)
	private String loginId;
	@Column(name = "name", nullable = false, length = 20)
	private String name;
	@Column(name= "password", nullable = false, length = 20)
	private String password;
	
}
