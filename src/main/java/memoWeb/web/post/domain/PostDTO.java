package memoWeb.web.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import memoWeb.common.utils.ObjectMapperUtils;
import memoWeb.web.main.domain.UserMemo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private int postIdx;
	private int memoIdx;
	private String contents;
	private String delFlag;
	private String delDate;

	private UserMemo userMemo = new UserMemo();
	private List<PostFileDTO> postFiles = new ArrayList<>();

	public PostDTO(Post post) {
		this.postIdx = post.getPostIdx();
//		this.memoIdx = post.getMemoIdx();
		this.contents = post.getContents();
//		this.userMemo = post.getUserMemo();
		this.postFiles = this.toPostFileDTO(post.getPostFiles());
		this.delFlag  = post.getDelFlag();
		this.delDate = post.getDelDate();
	}

	public List<PostFileDTO> toPostFileDTO(List<PostFile> postFiles) {
		return ObjectMapperUtils.mapAll(postFiles, PostFileDTO.class);
	}
}
