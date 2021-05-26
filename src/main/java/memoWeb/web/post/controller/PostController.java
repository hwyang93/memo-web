package memoWeb.web.post.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.common.utils.FileUtils;
import memoWeb.web.main.domain.*;
import memoWeb.web.main.service.ChatService;
import memoWeb.web.post.domain.PostDTO;
import memoWeb.web.post.domain.PostFile;
import memoWeb.web.post.domain.PostFileDTO;
import memoWeb.web.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/post/{userId}")
	public Map<String, Object> getPostList(@ModelAttribute PostDTO postDTO, @PathVariable String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		postDTO.getUserMemo().setUserId(userId);
		List<PostDTO> result = postService.getPostList(postDTO);
		for (PostDTO post : result) {
			for (PostFileDTO postFile : post.getPostFiles()) {
				String fileContent = FileUtils.getFileContent("D:/memo-web/post/"+postFile.getFileSaveName());
				postFile.setFileContent(fileContent);
			}
		}
		resultMap.put("postList", result);
		return resultMap;
	}

	@PostMapping("/post/userMemo/{idx}")
	public Map<String, Object> savePost(HttpSession session, @PathVariable int idx, MultipartHttpServletRequest request) {
//	public Map<String, Object> savePost(HttpSession session, @RequestBody PostDTO postDTO, MultipartHttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		PostDTO postDTO = new PostDTO();
//		PostFile postFile = null;
		PostFileDTO postFileDTO = null;
		UserMemo userMemo = postService.getUserMemo(idx);
		postDTO.setUserMemo(userMemo);
		postDTO.setContents(request.getParameter("contents"));

		Iterator<String> iterator = request.getFileNames();
		while (iterator.hasNext()) {
			List<MultipartFile> files = request.getFiles(iterator.next());
			for (MultipartFile file : files) {
				try {
					postFileDTO = new PostFileDTO();
					String newFileName = FileUtils.fileUpload(file);
					postFileDTO.setFileSaveName(newFileName);
					postFileDTO.setFileOrgName(file.getName());
					postDTO.getPostFiles().add(postFileDTO);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		postService.savePost(postDTO);
		return resultMap;
	}
}
