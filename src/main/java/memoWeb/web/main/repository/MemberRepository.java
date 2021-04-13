package memoWeb.web.main.repository;

import memoWeb.web.main.domain.UserVO;

public interface MemberRepository{
	UserVO findAllByUserIdAndUserPassword(UserVO memeber);
	UserVO save(UserVO member);
}
