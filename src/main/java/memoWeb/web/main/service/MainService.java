package memoWeb.web.main.service;

import memoWeb.web.main.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memoWeb.web.main.repository.MainRepository;

import java.util.List;

@Service
public class MainService {
	
	private final MainRepository mainRepository;

	@Autowired
	public MainService(MainRepository mainRepository) {
		this.mainRepository = mainRepository;
	}

	public UserVO login (UserVO member) {
		return mainRepository.getMember(member);
	}

	public UserVO signUp (UserVO member) {
		return mainRepository.signUp(member);
	}

	public UserScheduleVO saveUserSchedule (UserScheduleVO userSchedule) {
		return mainRepository.saveUserSchedule(userSchedule);
	}

	public List<UserScheduleVO> getUserScheduleList (UserVO member) {
		return mainRepository.getUserScheduleList(member);
	}

	public List<GroupScheduleDTO> getGroupScheduleList (UserVO user) { return mainRepository.getGroupScheduleList(user); }

	public GroupSchedule saveGroupSchedule(GroupSchedule groupSchedule) {
		return mainRepository.saveGroupSchedule(groupSchedule);
	}

	public UserMemo saveUserMemo(UserMemo userMemo) {
		return mainRepository.saveUserMemo(userMemo);
	}

}
