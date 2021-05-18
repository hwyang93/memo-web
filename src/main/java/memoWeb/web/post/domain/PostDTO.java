package memoWeb.web.post.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import memoWeb.web.main.domain.UserMemo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDTO {
	private int postIdx;
	private int memoIdx;
	private String contents;

//	private UserMemo userMemo;
	private List<PostFile> postFiles;

	@Autowired
	private static ModelMapper modelMapper;

	public PostDTO() {}

	public PostDTO(Post post) {
		this.postIdx = post.getPostIdx();
		this.memoIdx = post.getMemoIdx();
		this.contents = post.getContents();
//		this.userMemo = post.getUserMemo();
		this.postFiles = post.getPostFiles();
	}
	public static PostDTO toDto(Post post) {
		return modelMapper.map(post, PostDTO.class);
	}

	public static List<PostDTO> toDtoList(List<Post> post) {
		return post.stream()
				.map(PostDTO::toDto)
				.collect(Collectors.toList());
	}

}
