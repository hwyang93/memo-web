package memoWeb.web.mySchedule.service;

import com.querydsl.jpa.impl.JPAQuery;
import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.mySchedule.repository.MyScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyScheduleService {
    private final MyScheduleRepository myScheduleRepository;

    @Autowired
    public MyScheduleService(MyScheduleRepository myScheduleRepository) {
        this.myScheduleRepository = myScheduleRepository;
    }

    public List<UserScheduleVO> getUserScheduleListAll(MemberVO member) {
        return myScheduleRepository.getUserScheduleListAll(member);
    }
}
