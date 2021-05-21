package memoWeb.web.admin.repository;

import java.util.List;

import com.querydsl.core.Tuple;

import memoWeb.web.main.domain.UserVO;

public interface AdminRepository {
	List<UserVO> getUserList();
	int deleteUser(String id);
	UserVO getUserInfo(String id);
	long getUserCnt();
	List<Tuple> getMonthData();
	List<Tuple> getuGroupCnt(String id);
	long getuPostCnt(String id);
	long getMemoCnt();
	long getScheduleCnt();
	long getGscheduleCnt();

}
