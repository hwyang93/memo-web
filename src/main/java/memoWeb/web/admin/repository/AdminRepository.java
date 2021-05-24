package memoWeb.web.admin.repository;

import java.util.List;

import com.querydsl.core.Tuple;

import memoWeb.web.main.domain.UserVO;
import memoWeb.web.post.domain.Post;

public interface AdminRepository {
	List<UserVO> getUserList();
	int deleteUser(String id);
	UserVO getUserInfo(String id);
	List<Tuple> getMonthData();
	List<Tuple> getuGroupCnt(String id);
	long getuPostCnt(String id);
	long getPostCnt();
	long getMemoCnt();
	long getScheduleCnt();
	long getGscheduleCnt();
	List<Post> getPostList();
	Post getPostDetail(int idx);
	int deletePost(int idx);

}
