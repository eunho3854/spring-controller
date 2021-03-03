package com.cos.myiocdi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Component(특별한 용도 없으면), Configuration(설정파일), Service(서비스), Repository, Bean
// Configuration, Service, Repository, Bean -> Component를 상속 받음

// RestController, Controller -> IoC(싱글톤) 등록 new PostController(주입(new Robot()));
@RestController
public class Postcontroller {
	
	// IoC 컨테이너에서 가져옴
	// @Autowired
	private final Robot robot; // DI
	
	public Postcontroller(Robot robot) {
		this.robot = robot;
	}


	@GetMapping("/")
	public String home() {
		return "home " + robot.getName();
	}
}
