package jpa_board.board.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
	private String loginId;
	private String name;
	private String password;
	
	private boolean deleted = false;
	
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Board> boards;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "member")
    private List<BoardLike> likes;
}
