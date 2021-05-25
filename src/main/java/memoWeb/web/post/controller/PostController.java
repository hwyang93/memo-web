package memoWeb.web.post.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.common.utils.FileUtils;
import memoWeb.web.main.domain.*;
import memoWeb.web.main.service.ChatService;
import memoWeb.web.post.domain.PostDTO;
import memoWeb.web.post.domain.PostFile;
import memoWeb.web.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {

	private final PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/post")
	public Map<String, Object> getPostList(@ModelAttribute PostDTO postDTO, @RequestParam HashMap<String, String> params) {
		postDTO.getUserMemo().setUserId(params.get("userId"));
		Map<String, Object> resultMap = new HashMap<>();
		List<PostDTO> result = postService.getPostList(postDTO);
		resultMap.put("result", result);
		return resultMap;
	}

	@PostMapping("/post/userMemo/{idx}")
	public Map<String, Object> savePost(HttpSession session, @PathVariable int idx, MultipartHttpServletRequest request) {
//	public Map<String, Object> savePost(HttpSession session, @RequestBody PostDTO postDTO, MultipartHttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		PostDTO postDTO = new PostDTO();
		PostFile postFile = null;
		UserMemo userMemo = postService.getUserMemo(idx);
		postDTO.setUserMemo(userMemo);
		postDTO.setContents(request.getParameter("contents"));

		Iterator<String> iterator = request.getFileNames();
		while (iterator.hasNext()) {
			List<MultipartFile> files = request.getFiles(iterator.next());
			for (MultipartFile file : files) {
				try {
					postFile = new PostFile();
					String newFileName = FileUtils.fileUpload(file);
					postFile.setFileSaveName(newFileName);
					postFile.setFileOrgName(file.getName());
					postDTO.getPostFiles().add(postFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		postService.savePost(postDTO);
		return resultMap;
	}
}
