package com.cos.myjpa.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI 주입
@RestController
public class TestController {
	
	private final PostRepository postRepository;
	
	@PostMapping("/test/post")
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) { // title, content
		Post postEntity = postRepository.save(postSaveReqDto.toEntity()); // 실패 => Exception을 탄다.
		return new CommonRespDto<>(1, "성공" ,postEntity);
	}
	
	@GetMapping("/test/post")
	public CommonRespDto<?> findAll() {
		java.util.List<Post> postsEntity = postRepository.findAll();
		return new CommonRespDto<>(1, "성공", postsEntity);
	}
	
	@GetMapping("/test/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id) {
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		return new CommonRespDto<>(1, "성공", postEntity);
	}
	
	@PutMapping("/test/post/{id}")
	public CommonRespDto<?> update(@PathVariable Long id, @RequestBody PostUpdateReqDto postUpdateReqDto) {
		
		// 1. 수정할 id를 찾아야 함
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		// 2. 저장하기
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());
		
		Post postUpdateEntity = postRepository.save(postEntity); // 더티 체킹을 사용해야 하는데 그러면 @Service 만들어야 가능함!
		
		return new CommonRespDto<>(1, "성공", postUpdateEntity);
	}
	
	@DeleteMapping("/test/post/{id}")
	public CommonRespDto<?> deleteById(@PathVariable Long id) {
		postRepository.deleteById(id);
		return new CommonRespDto<>(1, "성공", null); 
	}
}
