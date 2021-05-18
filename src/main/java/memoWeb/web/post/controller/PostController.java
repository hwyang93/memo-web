package memoWeb.web.post.controller;

import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.*;
import memoWeb.web.main.service.ChatService;
import memoWeb.web.post.domain.PostDTO;
import memoWeb.web.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
	public Map<String, Object> getPostList(HttpSession session, @ModelAttribute PostDTO postDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		List<PostDTO> result = postService.getPostList(postDTO);
		resultMap.put("result", result);
		return resultMap;
	}


}
