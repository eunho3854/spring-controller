package com.cos.myiocdi;

import lombok.Getter;

@Getter
// @Component // IoC
// @Configuration // IoC
// @Service // IoC
// @Repository // IoC

// IoC 컨테이너에 등록됨 (런타임 때)
public class Robot {
	
	private String name = "건담";
}
