package memoWeb.web.mySchedule.repository;

import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;

import java.util.List;

public interface MyScheduleRepository {
    List<UserScheduleVO> getUserScheduleListAll (UserVO member);
}
