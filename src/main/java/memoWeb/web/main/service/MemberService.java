package memoWeb.web.main.service;

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
	
	public MemberVO login (String userId, String userPassword) {
		return memberRepository.findAllByUserIdAndUserPassword(userId, userPassword);
	}
	
	public MemberVO signUp (MemberVO member) {
		return memberRepository.save(member);
	}
	
	
}
