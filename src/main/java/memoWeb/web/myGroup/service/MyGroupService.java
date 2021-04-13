package memoWeb.web.myGroup.service;

import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.UserRelationVO;
import memoWeb.web.myGroup.repository.MyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyGroupService {
    private final MyGroupRepository myGroupRepository;

    @Autowired
    public MyGroupService(MyGroupRepository myGroupRepository) {
        this.myGroupRepository = myGroupRepository;
    }

    public List<UserVO> getUserList(String keyword) {
        return myGroupRepository.getUserList(keyword);
    }

    public UserRelationVO joinUserRelation (UserRelationVO userRelation) {
        return null;

    }
}
