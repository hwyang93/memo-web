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
@Builder(builderMethodName = "PostReplyBuilder")
public class PostReply {
	@Id
	@Column(name = "IDX")
	private int idx;
	@Column(name = "POST_IDX")
	private int postIdx;
	@Column(name = "ORIGIN_NO")
	private int originNo;
	@Column(name = "REG_DATE")
	private String regDate;
	@Column(name = "CONTENTS")
	private String contents;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "GROUP_ORD")
	private int groupOrd;

	public static PostReplyBuilder toEntity(PostReplyDTO postReplyDTO) {
		return PostReplyBuilder()
				.postIdx(postReplyDTO.getPostIdx())
				.originNo(postReplyDTO.getOriginNo())
				.contents(postReplyDTO.getContents())
				.userId(postReplyDTO.getUserId())
				.groupOrd(postReplyDTO.getGroupOrd());
	}
}
