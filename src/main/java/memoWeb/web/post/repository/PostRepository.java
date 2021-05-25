package memoWeb.web.post.repository;

import memoWeb.web.main.domain.*;
import memoWeb.web.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface PostRepository {
	UserMemo getUserMemo(int idx);
	List<Post> getPostList(Post post);
	void savePost(Post post);
}
