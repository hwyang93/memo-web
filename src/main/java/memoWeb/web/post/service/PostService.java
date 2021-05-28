package memoWeb.web.post.service;

import memoWeb.common.utils.ObjectMapperUtils;
import memoWeb.web.main.domain.*;
import memoWeb.web.main.repository.ChatRepository;
import memoWeb.web.post.domain.*;
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

	public UserMemo getUserMemo(int idx) {
		return postRepository.getUserMemo(idx);
	}

	public List<PostDTO> getPostList(PostDTO postDTO) {
		Post post = Post.toEntity(postDTO).build();
		List<Post> postList = postRepository.getPostList(post);
		return ObjectMapperUtils.mapAll(postList, PostDTO.class);
	}

	public void savePost(PostDTO postDTO) {
		Post post = Post.toEntity(postDTO).build();
		postRepository.savePost(post);
	}

	public void saveLike(PostLikeDTO postLikeDTO) {
		PostLike postLike = PostLike.toEntity(postLikeDTO).build();
		postRepository.saveLike(postLike);
	}
	public void cancelLike(PostLikeDTO postLikeDTO) {
		PostLike postLike = PostLike.toEntity(postLikeDTO).build();
		postRepository.cancelLike(postLike);
	}

	public List<PostReplyDTO> getPostReplyList(PostDTO postDTO) {
		Post post = Post.toEntity(postDTO).build();
		List<PostReply> result = postRepository.getPostReplyList(post);
		return ObjectMapperUtils.mapAll(result, PostReplyDTO.class);
	}

	public int getOriginNoMax(PostReplyDTO postReplyDTO) {
		PostReply postReply = PostReply.toEntity(postReplyDTO).build();
		return postRepository.getOriginNoMax(postReply);
	}
	public int getGroupOrdMax(PostReplyDTO postReplyDTO) {
		PostReply postReply = PostReply.toEntity(postReplyDTO).build();
		return postRepository.getGroupOrdMax(postReply);
	}

	public void saveReply(PostReplyDTO postReplyDTO) {
		PostReply postReply = PostReply.toEntity(postReplyDTO).build();
		postRepository.saveReply(postReply);
	}

}
