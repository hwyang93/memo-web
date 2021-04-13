package memoWeb.web.myGroup.repository;

import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.UserRelationVO;

import java.util.List;

public interface MyGroupRepository {
    public List<UserVO> getUserList (String keyword);
    public UserRelationVO joinUserRelation (UserRelationVO userRelation);
}
