package memoWeb.web.myGroup.service;

import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.myGroup.repository.MyGroupRepository;
import memoWeb.web.mySchedule.repository.MyScheduleRepository;
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

    public List<MemberVO> getUserList(String keyword) {
        return myGroupRepository.getUserList(keyword);
    }
}
