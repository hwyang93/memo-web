package memoWeb.web.post.repository;

import memoWeb.web.main.domain.*;
import memoWeb.web.post.domain.Post;
import memoWeb.web.post.domain.PostLike;
import memoWeb.web.post.domain.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface PostRepository {
	UserMemo getUserMemo(int idx);
	List<Post> getPostList(Post post);
	void savePost(Post post);
	void saveLike(PostLike postLike);
	void cancelLike(PostLike postLike);
	List<PostReply> getPostReplyList(Post post);
	int getOriginNoMax(PostReply postReply);
	int getGroupOrdMax(PostReply postReply);
	void saveReply(PostReply postReply);
}
