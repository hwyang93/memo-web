package memoWeb.web.main.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import memoWeb.web.post.domain.Post;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USER_MEMO")
@SequenceGenerator(
		name = "USER_MEMO_SEQ",
		sequenceName = "USER_MEMO_SEQ",
		initialValue = 1,
		allocationSize = 1
)
public class UserMemo implements Serializable {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "USER_MEMO_SEQ"
	)
	@Column(name = "IDX")
	private int idx;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "USER_ID", insertable = false, updatable = false)
	private String userId;

	@Column(name = "REG_DATE", insertable = false, updatable = false,
			columnDefinition = "Date default sysdate")
	private String regDate;

	@Column(name = "MEMO_PLACE")
	private String memoPlace;

	@Column(name = "LON")
	private String lon;

	@Column(name = "LAT")
	private String lat;

	@Column(name = "MEMO")
	private String memo;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserVO user;
}
