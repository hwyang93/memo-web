package memoWeb.web.post.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@SequenceGenerator(
		name = "POST_FILE_SEQ",
		sequenceName = "POST_FILE_SEQ",
		initialValue = 1,
		allocationSize = 1
)
@Table(name = "POST_FILE")
public class PostFile {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "POST_FILE_SEQ"
	)
	@Column(name = "POST_FILE_IDX")
	private int postFileIdx;
	//	@Column(name = "POST_IDX", insertable=false, updatable=false)
//	private int postIdx;
	@Column(name = "FILE_ORG_NAME")
	private String fileOrgName;
	@Column(name = "FILE_SAVE_NAME")
	private String fileSaveName;

//	@ManyToOne
//	@JoinColumn(name = "POST_IDX")
//	private Post post;

}
