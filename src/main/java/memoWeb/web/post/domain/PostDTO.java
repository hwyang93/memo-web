package memoWeb.web.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

	private UserMemo userMemo = new UserMemo();
	private List<PostFile> postFiles = new ArrayList<>();

	public PostDTO(Post post) {
		this.postIdx = post.getPostIdx();
//		this.memoIdx = post.getMemoIdx();
		this.contents = post.getContents();
//		this.userMemo = post.getUserMemo();
		this.postFiles = post.getPostFiles();
	}

}
