package com.cos.myjpa.domain.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity // 실행될 때 테이블을 만들어줌
public class Post {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Table, auto_increment, Sequence
	// Table = 가상으로 테이블을 1부터 끝까지 다 만들어줌
	// Sequence = 1씩 자동 증가 (오라클)
	// Identity = 자기 데이터베이스가 기본적으로 따라가는 전략을 그대로 따라함
	private Long id;
	
	@Column(length = 60, nullable = false) // 20자로 제한, null이 불가능함
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	// 누가 적었는지? user 정보
	
	// 시간
	@CreationTimestamp // 값 들어올 때 자동으로 현재 시간이 들어감.
	private LocalDateTime createDate;
}
