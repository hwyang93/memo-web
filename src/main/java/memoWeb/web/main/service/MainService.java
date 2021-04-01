package memoWeb.web.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memoWeb.web.main.repository.MainRepository;
import memoWeb.web.main.repository.MemberRepository;

@Service
public class MainService {
	
	private final MainRepository mainRepository;

	@Autowired
	public MainService(MainRepository mainRepository) {
		this.mainRepository = mainRepository;
	}
	
	public UserScheduleVO saveUserSchedule (UserScheduleVO userSchedule) {
		return mainRepository.save(userSchedule);
	}
	
	
}
