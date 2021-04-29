package memoWeb.web.main.repository;

import memoWeb.web.main.domain.*;

import java.util.List;

public interface MainRepository{
	UserDTO getMember(UserDTO user);
	UserVO signUp(UserVO member);
	UserScheduleVO saveUserSchedule(UserScheduleVO userSchedule);
	List<UserScheduleVO> getUserScheduleList(UserDTO user);
	List<GroupScheduleDTO> getGroupScheduleList(UserDTO user);
	GroupSchedule saveGroupSchedule(GroupSchedule groupSchedule);
	UserMemo saveUserMemo(UserMemo userMemo);
	List<UserMemoDTO> getUserMemoList(UserDTO user);
	List<UserDTO> getFriendList(UserDTO user);

}
