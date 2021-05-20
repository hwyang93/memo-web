package memoWeb.web.post.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "POST_FILE")
public class PostFile {
	@Id
	@Column(name = "POST_FILE_IDX", insertable=false, updatable=false)
	private int postFileIdx;
	@Column(name = "POST_IDX", insertable=false, updatable=false)
	private int postIdx;
	@Column(name = "FILE_ORG_NAME")
	private String fileOrgName;
	@Column(name = "FILE_SAVE_NAME")
	private String fileSaveName;

}
