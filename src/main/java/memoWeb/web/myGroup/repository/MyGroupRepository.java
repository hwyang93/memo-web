package memoWeb.web.myGroup.repository;

import com.querydsl.core.Tuple;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.*;

import java.util.HashMap;
import java.util.List;

public interface MyGroupRepository {
	List<UserDTO> getUserList(HashMap<String, Object> params);

	UserVO getUserInfo(UserVO user);

	UserRelationDTO joinUserRelation(UserRelationDTO userRelation);

	List<UserRelationDTO> getFriendList(UserRelationVO userRelation);

	int getGroupIdx();

	void createGroup(GroupsVO group);

	void joinGroupMember(GroupMemberVO groupMember);

	List<GroupDTO> getGroupList(UserVO user);

	GroupsVO getGroupInfo(GroupsVO group);

	List<GroupMemberDTO> getGroupMemberList(GroupDTO group);

	void deleteGroupMembers(GroupDTO group);

	void deleteGroup(GroupDTO group);

	List<UserRelationDTO> getFriendReqList(UserDTO user);

	long updateUserRelationStatus(UserRelationDTO userRelation);

	long deleteUserRelation(UserRelationDTO userRelation);

}
