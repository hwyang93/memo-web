package memoWeb.web.myGroup.repository;

import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.UserRelationVO;

import java.util.List;

public interface MyGroupRepository {
    public List<UserVO> getUserList (String keyword);
    public UserVO getUserInfo (UserVO user);
    public UserRelationVO joinUserRelation (UserRelationVO userRelation);
    public List<UserRelationVO> getFriendList (UserRelationVO userRelation);
}
