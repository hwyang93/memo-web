package memoWeb.web.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;

import memoWeb.web.admin.repository.AdminRepository;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.post.domain.Post;

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

	public List<Tuple> getMonthData() {
		return adminRepository.getMonthData();
	}

	public List<Tuple> getuGroupCnt(String id) {
		return adminRepository.getuGroupCnt(id);
	}

	public long getuPostCnt(String id) {
		return adminRepository.getuPostCnt(id);
	}

	public long getPostCnt() {
		return adminRepository.getPostCnt();
	}
	
	public long getMemoCnt() {
		return adminRepository.getMemoCnt();
	}

	public long getScheduleCnt() {
		return adminRepository.getScheduleCnt();
	}

	public long getGscheduleCnt() {
		return adminRepository.getGscheduleCnt();
	}

	public List<Post> getPostList() {
		return adminRepository.getPostList();
	}

	public Post getPostDetail(int idx) {
		return adminRepository.getPostDetail(idx);
	}

	public int deletePost(int idx) {
		return adminRepository.deletePost(idx);
	}

	public List<GroupsVO> getGroupList() {
		return adminRepository.getGroupList();
	}

	public GroupsVO getGroupDetail(int idx) {
		return adminRepository.getGroupDetail(idx);
	}
}
