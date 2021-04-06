package memoWeb.web.main.service;

import memoWeb.web.main.domain.MemberVO;
import memoWeb.web.main.domain.QMemberVO;
import memoWeb.web.main.domain.UserScheduleVO;
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

	public MemberVO login (MemberVO member) {
		return mainRepository.getMember(member);
	}

	public MemberVO signUp (MemberVO member) {
		return mainRepository.signUp(member);
	}

	public UserScheduleVO saveUserSchedule (UserScheduleVO userSchedule) {
		return mainRepository.saveUserSchedule(userSchedule);
	}

	public List<UserScheduleVO> getScheduleList (MemberVO member) {
		return mainRepository.getScheduleList(member);
	}



}
