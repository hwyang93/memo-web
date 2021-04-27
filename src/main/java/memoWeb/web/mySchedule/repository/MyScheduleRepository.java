package memoWeb.web.mySchedule.repository;

import java.util.List;

import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;

public interface MyScheduleRepository {
    List<UserScheduleVO> getUserScheduleListAll (UserDTO user);
	UserScheduleVO getScheduleDetail(int idx);
	long updateSchedule(UserScheduleVO userScheduleVO);
	long deleteSchedule(int idx);
}
