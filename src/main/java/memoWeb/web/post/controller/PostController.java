package memoWeb.web.post.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.common.utils.FileUtils;
import memoWeb.web.main.domain.*;
import memoWeb.web.main.service.ChatService;
import memoWeb.web.post.domain.*;
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
	public Map<String, Object> getPostList(HttpSession session, @ModelAttribute PostDTO postDTO, @PathVariable String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		postDTO.getUserMemo().setUserId(userId);
		List<PostDTO> result = postService.getPostList(postDTO);
		for (PostDTO post : result) {
			for (PostFileDTO postFile : post.getPostFiles()) {
				String fileContent = FileUtils.getFileContent("D:/memo-web/post/" + postFile.getFileSaveName());
				postFile.setFileContent(fileContent);
			}
			for (PostLikeDTO postLike : post.getPostLikes()) {
				if (postLike.getUserId().equals(((UserDTO) session.getAttribute(CommonConstants.SESSION)).getUserId())) {
					post.setLikeYn("Y");
				}
			}
			List<PostReplyDTO> postReplyList = postService.getPostReplyList(post);
			post.setPostReplyList(postReplyList);
		}
		resultMap.put("postList", result);
		return resultMap;
	}

	@PostMapping("/post/userMemo/{idx}")
	public Map<String, Object> savePost(HttpSession session, @PathVariable int idx, MultipartHttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		PostDTO postDTO = new PostDTO();
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
					String newFileName = FileUtils.fileUpload(file, "post");
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

	@PostMapping("/postLike")
	public Map<String, Object> savePost(@RequestBody PostLikeDTO postLikeDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		postService.saveLike(postLikeDTO);
		resultMap.put("result", "success");
		return resultMap;
	}

	@DeleteMapping("/postLike")
	public Map<String, Object> cancelPost(@RequestBody PostLikeDTO postLikeDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		postService.cancelLike(postLikeDTO);
		resultMap.put("result", "success");
		return resultMap;
	}

	@GetMapping("/postReply")
	public Map<String, Object> cancelPost(@ModelAttribute PostDTO postDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		List<PostReplyDTO> result = postService.getPostReplyList(postDTO);
		resultMap.put("result", result);
		return resultMap;
	}

	@PostMapping("/postReply")
	public Map<String, Object> saveReply(HttpSession session, @RequestBody PostReplyDTO postReplyDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		PostDTO postDTO = new PostDTO();
		postReplyDTO.setUserId(((UserDTO) session.getAttribute(CommonConstants.SESSION)).getUserId());
		if (postReplyDTO.getOriginNo() == 0) {
			int originNo = postService.getOriginNoMax(postReplyDTO) + 1;
			postReplyDTO.setOriginNo(originNo);
			postReplyDTO.setGroupOrd(0);
		} else {
			int groupOrd = postService.getGroupOrdMax(postReplyDTO) + 1;
			postReplyDTO.setGroupOrd(groupOrd);
		}
		postService.saveReply(postReplyDTO);

		postDTO.setPostIdx(postReplyDTO.getPostIdx());

		List<PostReplyDTO> postReplyList = postService.getPostReplyList(postDTO);

		resultMap.put("result", postReplyList);

		return resultMap;
	}
}
