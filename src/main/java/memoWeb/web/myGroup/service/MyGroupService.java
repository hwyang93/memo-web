package memoWeb.web.myGroup.service;

import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupMemberVO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.myGroup.domain.UserRelationVO;
import memoWeb.web.myGroup.repository.MyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MyGroupService {

    @Autowired
    private final MyGroupRepository myGroupRepository;

    public MyGroupService(MyGroupRepository myGroupRepository) {
        this.myGroupRepository = myGroupRepository;
    }

    public List<UserVO> getUserList(HashMap<String, Object> params) {
        return myGroupRepository.getUserList(params);
    }

    public UserVO getUserInfo (UserVO user){
        return myGroupRepository.getUserInfo(user);
    };

    public UserRelationVO joinUserRelation (UserRelationVO userRelation) {
        return myGroupRepository.joinUserRelation(userRelation);
    }

    public List<UserRelationVO> getFriendList (UserRelationVO userRelation) {
        return myGroupRepository.getFriendList(userRelation);
    }

    public List<GroupsVO> getGroupList (UserVO user) {
        return myGroupRepository.getGroupList(user);
    }

    public GroupsVO getGroupInfo (GroupsVO group) {
        return myGroupRepository.getGroupInfo(group);
    }

    public int getGroupIdx() {
        return myGroupRepository.getGroupIdx();
    }

    public void createGroup(GroupsVO group) {
         myGroupRepository.createGroup(group);
    }

    public void joinGroupMember(List<GroupMemberVO> groupMemberList) {
        groupMemberList.forEach(myGroupRepository::joinGroupMember);
    }


}
