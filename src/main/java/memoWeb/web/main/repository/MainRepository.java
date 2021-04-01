package memoWeb.web.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import memoWeb.web.main.service.MemberVO;
import memoWeb.web.main.service.UserScheduleVO;

public interface MainRepository extends JpaRepository<UserScheduleVO, Long>{
	UserScheduleVO save(UserScheduleVO userSchedule);
}
