package memoWeb.web.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import memoWeb.web.main.domain.MemberVO;

public interface MemberRepository{
	MemberVO findAllByUserIdAndUserPassword(MemberVO memeber);
	MemberVO save(MemberVO member);
}
