package memoWeb.web.admin.repository;

import java.util.List;

import memoWeb.web.main.domain.UserVO;

public interface AdminRepository {
	List<UserVO> getUserList();
	int deleteUser(String id);

}
