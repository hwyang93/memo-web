package memoWeb.web.post.domain;

import lombok.*;
import memoWeb.web.main.domain.UserMemo;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
@Table(name = "POST")
@Builder(builderMethodName = "PostBuilder")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_IDX", insertable=false, updatable=false)
	private int postIdx;
	@Column(name = "MEMO_IDX")
	private int memoIdx;
	@Column(name = "CONTENTS")
	private String contents;

//	@OneToOne
//	UserMemo userMemo;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "POST_IDX")
	private List<PostFile> postFiles;


	public static PostBuilder toEntity(PostDTO postDTO) {
		return PostBuilder()
				.postIdx(postDTO.getPostIdx())
				.memoIdx(postDTO.getMemoIdx())
				.contents(postDTO.getContents());
	}


}
