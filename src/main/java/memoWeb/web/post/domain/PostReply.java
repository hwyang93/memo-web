package memoWeb.web.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SequenceGenerator(
		name="POST_REPLY_SEQ",
		sequenceName="POST_REPLY_SEQ",
		initialValue=1,
		allocationSize=1
)
@Table(name = "POST_REPLY")
//@Builder(builderMethodName = "PostLikeBuilder")
public class PostReply {
	@Id
	@Column(name = "IDX")
	private int idx;
	@Column(name = "POST_IDX")
	private int postIdx;
//	@Column(name = "GROUP_IDX")
//	private int groupIdx;
	@Column(name = "REG_DATE")
	private String regDate;
	@Column(name = "CONTENTS")
	private String contents;
	@Column(name = "USER_ID")
	private String userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_IDX")
	private PostReply parent;

	@OneToMany(mappedBy = "parent")
	private List<PostReply> children = new ArrayList<>();

//	public static PostReply.PostLikeBuilder toEntity(PostLikeDTO postLikeDTO) {
//		return PostLikeBuilder()
//				.postIdx(postLikeDTO.getPostIdx())
//				.userId(postLikeDTO.getUserId());
//	}
}
