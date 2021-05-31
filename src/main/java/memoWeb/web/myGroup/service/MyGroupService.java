package memoWeb.web.myGroup.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupDTO;
import memoWeb.web.myGroup.domain.GroupMemberDTO;
import memoWeb.web.myGroup.domain.GroupMemberVO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.myGroup.domain.UserRelationDTO;
import memoWeb.web.myGroup.domain.UserRelationVO;
import memoWeb.web.myGroup.repository.MyGroupRepository;

@Service
public class MyGroupService {

	@Autowired
	private final MyGroupRepository myGroupRepository;

	public MyGroupService(MyGroupRepository myGroupRepository) {
		this.myGroupRepository = myGroupRepository;
	}

	public List<UserDTO> getUserList(HashMap<String, Object> params) {
		return myGroupRepository.getUserList(params);
	}

	public UserVO getUserInfo(UserVO user) {
		return myGroupRepository.getUserInfo(user);

	}

	public long joinUserRelation(UserRelationVO userRelation) {
		return myGroupRepository.joinUserRelation(userRelation);
	}

	public List<UserRelationDTO> getFriendList(UserRelationVO userRelation) {
		return myGroupRepository.getFriendList(userRelation);
	}

	public List<GroupDTO> getGroupList(UserVO user) {
		return myGroupRepository.getGroupList(user);
	}

	public GroupsVO getGroupInfo(GroupsVO group) {
		return myGroupRepository.getGroupInfo(group);
	}

	public List<GroupMemberDTO> getGroupMemberList(GroupDTO group) {
		return myGroupRepository.getGroupMemberList(group);
	}

	public int getGroupIdx() {
		return myGroupRepository.getGroupIdx();
	}
	
	public void createGroup(GroupDTO groupDTO) {
		GroupsVO group = GroupsVO.toEntity(groupDTO).build();
		myGroupRepository.createGroup(group);
		this.joinGroupMember(groupDTO.getGroupMembers());
	}

	public void joinGroupMember(List<GroupMemberVO> groupMemberList) {
		groupMemberList.forEach(myGroupRepository::joinGroupMember);
	}

	public void deleteGroup(GroupDTO group) {
		myGroupRepository.deleteGroupSchedule(group);
		myGroupRepository.deleteGroupMembers(group);
		myGroupRepository.deleteGroup(group);
	}

	public List<UserRelationDTO> getFriendReqList(UserDTO user) {
		return myGroupRepository.getFriendReqList(user);
	}

	public void friendConsent(UserRelationDTO userRelation) {
		userRelation.setRelationStatus("I");
		UserRelationVO joinInfo = new UserRelationVO();
		joinInfo.setUserId(userRelation.getFollowUserId());
		joinInfo.setFollowUserId(userRelation.getUserId());
		joinInfo.setRelationStatus("I");
		long result = myGroupRepository.updateUserRelationStatus(userRelation);
		myGroupRepository.joinUserRelation(joinInfo);
	}

	public long deleteUserRelation(UserRelationDTO userRelation) {
		long result = myGroupRepository.deleteUserRelation(userRelation);
		if (userRelation.getRelationStatus().equals("I")) {
			UserRelationDTO userRelation2 = new UserRelationDTO();
			userRelation2.setUserId(userRelation.getFollowUserId());
			userRelation2.setFollowUserId(userRelation.getUserId());
			myGroupRepository.deleteUserRelation(userRelation2);
		}
		return result;
	}

}
