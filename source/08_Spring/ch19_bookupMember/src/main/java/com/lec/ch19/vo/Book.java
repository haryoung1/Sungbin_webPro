package com.lec.ch19.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Book {
	private int bnum;
	private String btitle;
	private String bwriter;
	private Date brdate;
	private String bimg1;
	private String bimg2;
	private String binfo;
	// 페이징용 변수
	private int startRow;
	private int endRow;
	// 검색을 위한 필드
	
}
