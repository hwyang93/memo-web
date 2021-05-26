package memoWeb.web.admin.repository;

import java.util.List;

import com.querydsl.core.Tuple;

import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.post.domain.Post;

public interface AdminRepository {
	List<UserVO> getUserList();
	long deleteUser(UserVO member);
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
	long deletePost(Post post);
	List<GroupsVO> getGroupList();
	GroupsVO getGroupDetail(int idx);

}
