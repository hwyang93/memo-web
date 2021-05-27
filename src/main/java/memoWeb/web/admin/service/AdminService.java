package memoWeb.web.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;

import memoWeb.web.admin.repository.AdminRepository;
import memoWeb.web.main.domain.UserDTO;
import memoWeb.web.main.domain.UserVO;
import memoWeb.web.myGroup.domain.GroupDTO;
import memoWeb.web.myGroup.domain.GroupsVO;
import memoWeb.web.post.domain.Post;
import memoWeb.web.post.domain.PostDTO;

@Service
public class AdminService {
	private final AdminRepository adminRepository;
	
	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public List<UserDTO> getUserList() {
		List<UserDTO> list = adminRepository.getUserList();
		if(list.isEmpty()) {
			list = null;
		}
		return list;
	}

	public long deleteUser(UserDTO member) {
		return adminRepository.deleteUser(member);
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

	public List<PostDTO> getPostList() {
		List<PostDTO> list = adminRepository.getPostList();
		if(list.isEmpty()) {
			list = null;
		}
		return list;
	}

	public Post getPostDetail(int idx) {
		return adminRepository.getPostDetail(idx);
	}

	public long deletePost(PostDTO post) {
		return adminRepository.deletePost(post);
	}

	public List<GroupDTO> getGroupList() {
		List<GroupDTO> list = adminRepository.getGroupList();
		if(list.isEmpty()) {
			list = null;
		}
		return list;
	}

	public GroupsVO getGroupDetail(int idx) {
		return adminRepository.getGroupDetail(idx);
	}

	public long getGroupActive() {
		return adminRepository.getGroupActive();
	}

	public long getGroupOther() {
		return adminRepository.getGroupOther();
	}

	public long deleteGroup(GroupDTO groups) {
		return adminRepository.deleteGroup(groups);
	}
}
