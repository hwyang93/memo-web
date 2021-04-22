package memoWeb.web.myGroup.repository;

import com.querydsl.core.Tuple;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.*;

import java.util.HashMap;
import java.util.List;

public interface MyGroupRepository {
    public List<UserVO> getUserList(HashMap<String, Object> params);

    public UserVO getUserInfo(UserVO user);

    public UserRelationVO joinUserRelation(UserRelationVO userRelation);

    public List<UserRelationVO> getFriendList(UserRelationVO userRelation);

    public int getGroupIdx();

    public void createGroup(GroupsVO group);

    public void joinGroupMember(GroupMemberVO groupMember);

    public List<GroupDTO> getGroupList(UserVO user);

    public GroupsVO getGroupInfo(GroupsVO group);

    public List<GroupMemberDTO> getGroupMemberList(GroupDTO group);

    public void deleteGroupMembers (GroupDTO group);

    public void deleteGroup(GroupDTO group);

}
