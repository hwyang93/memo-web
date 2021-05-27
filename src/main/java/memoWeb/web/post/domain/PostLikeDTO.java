package memoWeb.web.post.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostLikeDTO {
	private int postIdx;
	private String userId;

}
