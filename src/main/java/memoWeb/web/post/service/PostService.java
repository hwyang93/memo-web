package memoWeb.web.post.service;

import memoWeb.web.main.domain.*;
import memoWeb.web.main.repository.ChatRepository;
import memoWeb.web.post.domain.Post;
import memoWeb.web.post.domain.PostDTO;
import memoWeb.web.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PostService {

	private final PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<PostDTO> getPostList(PostDTO postDTO) {
		Post post = Post.toEntity(postDTO).build();
		List<Post> postList = postRepository.findAll();
		return PostDTO.toDtoList(postList);
	}
}
