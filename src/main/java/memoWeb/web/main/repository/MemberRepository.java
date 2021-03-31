package memoWeb.web.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import memoWeb.web.main.service.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, Long>{
	MemberVO findAllByUserIdAndUserPassword(String userId, String userPassword);
	MemberVO save(MemberVO member);
}
