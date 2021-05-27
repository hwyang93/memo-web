package memoWeb.web.admin.repository;

import java.util.List;

import com.querydsl.core.Tuple;

import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupDTO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.post.domain.Post;
import memoWeb.web.post.domain.PostDTO;

public interface AdminRepository {
	List<UserDTO> getUserList();
	long deleteUser(UserDTO member);
	UserVO getUserInfo(String id);
	List<Tuple> getMonthData();
	List<Tuple> getuGroupCnt(String id);
	long getuPostCnt(String id);
	long getPostCnt();
	long getMemoCnt();
	long getScheduleCnt();
	long getGscheduleCnt();
	List<PostDTO> getPostList();
	Post getPostDetail(int idx);
	long deletePost(PostDTO post);
	List<GroupDTO> getGroupList();
	GroupsVO getGroupDetail(int idx);
	long getGroupActive();
	long getGroupOther();
	long deleteGroup(GroupDTO groups);

}
