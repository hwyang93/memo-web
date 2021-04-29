package memoWeb.web.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
