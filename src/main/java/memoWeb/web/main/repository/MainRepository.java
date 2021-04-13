package memoWeb.web.main.repository;

import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;

import java.util.List;

public interface MainRepository{
	UserVO getMember(UserVO member);
	UserVO signUp(UserVO member);
	UserScheduleVO saveUserSchedule(UserScheduleVO userSchedule);
	List<UserScheduleVO> getScheduleList(UserVO member);
}
