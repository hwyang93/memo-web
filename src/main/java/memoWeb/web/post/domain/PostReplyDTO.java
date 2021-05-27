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
	private int groupIdx;
	private String regDate;
	private String contents;
	private String userId;

}
