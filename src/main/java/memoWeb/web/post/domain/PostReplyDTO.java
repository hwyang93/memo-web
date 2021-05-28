package memoWeb.web.post.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@Data
public class PostReplyDTO {
	private int idx;
	private int postIdx;
	private int originNo;
	private String regDate;
	private String contents;
	private String userId;
	private int groupOrd;

	public PostReplyDTO(PostReply postReply) {
		this.idx = postReply.getIdx();
		this.postIdx = postReply.getPostIdx();
		this.originNo = postReply.getOriginNo();
		this.regDate = postReply.getRegDate();
		this.contents = postReply.getContents();
		this.userId = postReply.getUserId();
		this.groupOrd = postReply.getGroupOrd();
	}
}
