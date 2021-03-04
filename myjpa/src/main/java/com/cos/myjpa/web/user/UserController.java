package com.cos.myjpa.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.domain.user.UserRepository;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.user.dto.UserJoinReqDto;
import com.cos.myjpa.web.user.dto.UserLoginReqDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor // 생성자 만들어줌
@RestController
public class UserController {
	
	private final UserRepository userRepository;
	private final HttpSession session; // DI
	
	// 회원가입
	@PostMapping("/join") // auth(인증)
	public CommonRespDto<?> join(@RequestBody UserJoinReqDto userJoinReqDto) {
		User userEntity = userRepository.save(userJoinReqDto.toEntity());
		return new CommonRespDto<>(1, "성공", userEntity);
	}
	
	// Jpa에 없는 함수 구현
	@PostMapping("/login")
	public CommonRespDto<?> login(@RequestBody UserLoginReqDto userLoginReqDto) {
		User userEntity = userRepository.findByUsernameAndPassword(userLoginReqDto.getUsername(), userLoginReqDto.getPassword());
		
		if(userEntity == null) {
			return new CommonRespDto<>(-1, "실패", null);
		} else {
			// password 안 보이게 하기 위해
			userEntity.setPassword(null);
			
			session.setAttribute("principal", userEntity);
			return new CommonRespDto<>(1, "성공", userEntity);
		}
	}
	
	// 테스트
	@GetMapping("test/user/{id}") // 유저정보
	public CommonRespDto<?> userInfo(@PathVariable Long id) {
		
		User principal = (User)session.getAttribute("principal");
		if(principal == null) {
			return new CommonRespDto<>(-1, "실패", null);
		} else {
			User userEntity = userRepository.findById(id).get();
			return new CommonRespDto<>(1, "성공", userEntity);
		}
	}
}
