package com.test.app.Controller.member;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.app.Config.auth.PrincipalDetails;
import com.test.app.Domain.Dto.MemberDto;
import com.test.app.Domain.Service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@GetMapping("/add")
	public void f1(MemberDto dto) {
		log.info("GET /member/add");
		memberService.addMember(dto);
	}

	@GetMapping("/selectall")
	public void f2() {
		log.info("GET /member/selectall");
	}

	@GetMapping("/update")
	public void f3(MemberDto dto) {
		log.info("GET /member/update");
		memberService.modifyMember(dto);
	}
	
	@PostMapping("/update")
	public String f10(MemberDto dto, Authentication authentication) {
		log.info("POST /member/update");
		memberService.modifyMember(dto);
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		principalDetails.setMember(dto);
		
		return "redirect:user";
	}
	
	@GetMapping("/remove")
	public void f4(@RequestParam String id) {
		log.info("GET /member/delete");
		memberService.removeMember(id);
	}
	
	@PostMapping("/remove")
	public void f5(@RequestParam String id) {
		log.info("POST /member/delete");
		memberService.removeMember(id);
	}

	@GetMapping("/login")
	public void f5() {
		log.info("GET /login");
	}

	@GetMapping("/join")
	public void f6() {
		log.info("GET /join");
	}

	@PostMapping("/join")
	public String f6(@ModelAttribute MemberDto dto) {
		memberService.addMember(dto);
		log.info("POST /join");
		return "/member/login";
	}

	@GetMapping("/mypage")
	public String f7(HttpSession session, Authentication authentication, Model model) {
		System.out.println("authentication : " + authentication);
		
		
		return MypageRequest(session);
	}	
		

	@PostMapping("/mypage")
	public String f7Post(HttpSession session) {
		return MypageRequest(session);
	}

	private String MypageRequest(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if ("ROLE_USER".equals(role)) {
			System.out.println("user's mypage");
			return "redirect:/member/user";
		} else if ("ROLE_MEMBER".equals(role)) {
			System.out.println("member's mypage");
			return "redirect:/member/member";
		}
		return "redirect:/indexlog";
	}

	@GetMapping("/user")
	public void f8() {
		log.info("GET /user");
	}

	@GetMapping("/member")
	public void f9(Model model) {
		log.info("GET /member");
		List<MemberDto> list = memberService.getAllMember();
		
		List<MemberDto> userDtoList = list.stream()
	            .filter(dto -> "ROLE_USER".equals(dto.getRole()))
	            .collect(Collectors.toList());

		userDtoList.forEach((dto) -> {
	        System.out.println(dto);
	    });

	    model.addAttribute("list", userDtoList);
	}

}