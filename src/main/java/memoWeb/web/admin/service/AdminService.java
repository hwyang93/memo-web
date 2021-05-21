package memoWeb.web.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;

import memoWeb.web.admin.repository.AdminRepository;
import memoWeb.web.main.domain.UserVO;

@Service
public class AdminService {
	private final AdminRepository adminRepository;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public List<UserVO> getUserList() {
		return adminRepository.getUserList();
	}

	public int deleteUser(String id) {
		return adminRepository.deleteUser(id);
	}

	public UserVO getUserInfo(String id) {
		return adminRepository.getUserInfo(id);
	}

	public long getUserCnt() {
		return adminRepository.getUserCnt();
	}

	public long getPostCnt() {
		return 0;
	}

	public long getGroupCnt() {
		return 0;
	}

	public List<Tuple> getMonthData() {
		return adminRepository.getMonthData();
	}
}
