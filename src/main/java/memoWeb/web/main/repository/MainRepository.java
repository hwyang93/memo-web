package memoWeb.web.main.repository;

import memoWeb.web.main.domain.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;

import memoWeb.web.main.domain.UserScheduleVO;

import java.util.List;

public interface MainRepository{
	MemberVO getMember(MemberVO member);
	MemberVO signUp(MemberVO member);
	UserScheduleVO saveUserSchedule(UserScheduleVO userSchedule);
	List<UserScheduleVO> getScheduleList(MemberVO member);
}
