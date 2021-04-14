package memoWeb.web.mySchedule.service;

import memoWeb.web.main.domain.UserScheduleVO;
import memoWeb.web.main.domain.UserVO;
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

    public List<UserScheduleVO> getUserScheduleListAll(UserVO member) {
        return myScheduleRepository.getUserScheduleListAll(member);
    }

	public UserScheduleVO getScheduleDetail(int idx) {
		return myScheduleRepository.getScheduleDetail(idx);
	}

	public long updateSchedule(UserScheduleVO userScheduleVO) {
		return myScheduleRepository.updateSchedule(userScheduleVO);
	}

	public long deleteSchedule(int idx) {
		return myScheduleRepository.deleteSchedule(idx);
	}

}
