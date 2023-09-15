package com.test.app.Domain.Dto;

import java.util.List;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	private String id;
	private String pw;
	private String name;
	private String addr;
	private String phone;
	private String role;
	private List<String> searchHistory;
	
}