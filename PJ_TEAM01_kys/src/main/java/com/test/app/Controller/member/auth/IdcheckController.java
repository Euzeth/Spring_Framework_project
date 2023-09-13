package com.test.app.Controller.member.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.app.Domain.Service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IdcheckController {

	@Autowired
	MemberService memberService;
	
	@PostMapping("/checkDuplicate")
	public ResponseEntity<String> checkDuplicate(@RequestParam("id") String id) {
	    try {
	        boolean duplicate_id = memberService.idcheck(id);
	        return ResponseEntity.ok(String.valueOf(duplicate_id));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("중복 확인 중에 오류가 발생했습니다.");
	    }
	}
	
	
		
		
}