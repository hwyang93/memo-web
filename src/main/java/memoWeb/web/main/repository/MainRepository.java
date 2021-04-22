package memoWeb.web.main.repository;

import memoWeb.web.main.domain.*;

import java.util.List;

public interface MainRepository{
	UserVO getMember(UserVO member);
	UserVO signUp(UserVO member);
	UserScheduleVO saveUserSchedule(UserScheduleVO userSchedule);
	List<UserScheduleVO> getUserScheduleList(UserVO member);
	List<GroupScheduleDTO> getGroupScheduleList(UserVO user);
	GroupSchedule saveGroupSchedule(GroupSchedule groupSchedule);
	UserMemo saveUserMemo(UserMemo userMemo);
}
