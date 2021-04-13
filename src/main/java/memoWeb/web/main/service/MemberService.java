package memoWeb.web.main.service;

import memoWeb.web.main.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memoWeb.web.main.repository.MemberRepository;

@Service
public class MemberService {
	
	private final MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public UserVO login (UserVO member) {
		return memberRepository.findAllByUserIdAndUserPassword(member);
	}

	public UserVO signUp (UserVO member) {
		return memberRepository.save(member);
	}

	
}
