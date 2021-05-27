package memoWeb.web.post.domain;

import lombok.*;
import memoWeb.common.utils.ObjectMapperUtils;
import memoWeb.web.main.domain.UserMemo;

import javax.persistence.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
@SequenceGenerator(
		name="POST_SEQ",
		sequenceName="POST_SEQ",
		initialValue=1,
		allocationSize=1
)
@Table(name = "POST")
@Builder(builderMethodName = "PostBuilder")
public class Post {
	@Id
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="POST_SEQ"
	)
	@Column(name = "POST_IDX")
	private int postIdx;
//	@Column(name = "MEMO_IDX", insertable=false, updatable=false)
//	private int memoIdx;
	@Column(name = "CONTENTS")
	private String contents;
	@Column(name="DEL_FLAG")
	private String delFlag;
	@Column(name="DEL_DATE")
	private String delDate;
	@Column(name = "REG_DATE")
	private String regDate;

	@OneToOne
	@JoinColumn(name = "MEMO_IDX")
	private UserMemo userMemo;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "POST_IDX")
	private List<PostFile> postFiles;
	@OneToMany
	@JoinColumn(name = "POST_IDX")
	private List<PostLike> postLikes;

	public static PostBuilder toEntity(PostDTO postDTO) {

		return PostBuilder()
				.postIdx(postDTO.getPostIdx())
				.contents(postDTO.getContents())
				.delFlag(postDTO.getDelFlag())
				.delDate(postDTO.getDelDate())
				.userMemo(postDTO.getUserMemo())
				.postFiles(toPostFileEntity(postDTO.getPostFiles()));
	}


	private static List<PostFile> toPostFileEntity(List<PostFileDTO> postFiles) {
		return ObjectMapperUtils.mapAll(postFiles, PostFile.class);
	}
}
