package memoWeb.web.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "POST_LIKE")
@Builder(builderMethodName = "PostLikeBuilder")
public class PostLike {
	@Id
	@Column(name = "POST_IDX")
	private int postIdx;
	@Column(name = "USER_ID")
	private String userId;

	public static PostLikeBuilder toEntity(PostLikeDTO postLikeDTO) {
		return PostLikeBuilder()
				.postIdx(postLikeDTO.getPostIdx())
				.userId(postLikeDTO.getUserId());
	}
}
