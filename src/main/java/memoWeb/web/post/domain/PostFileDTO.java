package memoWeb.web.post.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
public class PostFileDTO {

	private int postFileIdx;
	private String fileOrgName;
	private String fileSaveName;
	private String fileContent;

//	@ManyToOne
//	@JoinColumn(name = "POST_IDX")
//	private Post post;

}
