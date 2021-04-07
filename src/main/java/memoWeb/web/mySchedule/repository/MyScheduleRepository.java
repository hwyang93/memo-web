package memoWeb.web.mySchedule.repository;

import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.UserScheduleVO;

import java.util.List;

public interface MyScheduleRepository {
    List<UserScheduleVO> getUserScheduleListAll (MemberVO member);
}
