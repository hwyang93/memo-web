package memoWeb.web.mySchedule.repository;

import java.util.List;

import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;

public interface MyScheduleRepository {
    List<UserScheduleVO> getUserScheduleListAll (UserVO member);
	UserScheduleVO getScheduleDetail(int idx);
	long updateSchedule(UserScheduleVO userScheduleVO);
	long deleteSchedule(int idx);
}
