package memoWeb.web.main.service;

import memoWeb.web.main.domain.MemberVO;
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
	
	public MemberVO login (MemberVO member) {
		return memberRepository.findAllByUserIdAndUserPassword(member);
	}

	public MemberVO signUp (MemberVO member) {
		return memberRepository.save(member);
	}

	
}
