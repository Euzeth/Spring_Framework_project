package com.test.app.Controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.test.app.Domain.Dto.MembershipDto;
import com.test.app.Domain.Service.MembershipService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MembershipController {
	private final String ADMIN_KEY = "ae39e2b27d5011a69f526b76f277ed62";

	@Autowired
	MembershipService membershipService;

	// 멤버십 가입을 눌렀을 때 멤버, 유저 매핑 다르게
	@GetMapping("/membership")
	public String membership(HttpSession session, Authentication authentication, Model model) {
		System.out.println("authentication : " + authentication);

		return membershipRequest(session);
	}

	@PostMapping("/membership")
	public String membership_post(HttpSession session) {

		return membershipRequest(session);
	}

	private String membershipRequest(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if ("ROLE_USER".equals(role)) {
			System.out.println("user's membership");
			return "redirect:/membershipU";
		} else if ("ROLE_MEMBER".equals(role)) {
			System.out.println("member's membership");
			return "redirect:/membershipM";
		}
		return "redirect:/indexlog";
	}

	@GetMapping("/membershipU")
	public void membership_U() {
		log.info("GET /memberhsipU");
	}

	@GetMapping("/membershipM")
	public void membership_M(Model model) {
		log.info("GET /memberhsipM");
		List<MembershipDto> list = membershipService.getAllMember();

		List<MembershipDto> membershipDtoList = list.stream().collect(Collectors.toList());

		model.addAttribute("list", membershipDtoList);

	}

	// membership 개별 유저 조회 관련 매핑(ID)
	@GetMapping("/membership_selectId")
	public String membership_selectId(@RequestParam String id, Model model) {
		log.info("GET /membership_M select ID");
		log.info("POST /memberhsip_M select ID");
		MembershipDto selectedId = membershipService.getMembershipId(id);
		model.addAttribute("selectedId", selectedId);
		System.out.println(selectedId);

		return "membershipM";
	}

	// membership 개별 유저 조회 관련 매핑(CODE)
	@GetMapping("/membership_selectCode")
	public String membership_selectCode(@RequestParam String membershipCode, Model model) {
		log.info("GET /membership_M select CODE");
		List<MembershipDto> list = membershipService.getMembershipCode(membershipCode);

		List<MembershipDto> selectedCodeList = list.stream().collect(Collectors.toList());

		model.addAttribute("selectedCodeList", selectedCodeList);

		System.out.println(selectedCodeList);

		return "membershipM";
	}

	// membership 개별 유저 조회 관련 매핑(종료일자)

	@InitBinder
	public void dataBinder(WebDataBinder dataBinder) {
		System.out.println("MembershipController's dataBinder.. " + dataBinder);
		// String("2022-01-01") -> LocalDate로 변환 Editor
		dataBinder.registerCustomEditor(LocalDate.class, "endDate", new MemberDtoEditor());

	}

	@GetMapping("/membership_selectDate")
	public String membership_selectDate(@Valid @ModelAttribute MembershipDto dto, BindingResult bindingResult,
			Model model) {
		log.info("GET /membership_M select EndDate");
		if (bindingResult.hasFieldErrors()) {

			for (FieldError error : bindingResult.getFieldErrors()) {
				System.out.println(error.getField() + ": " + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());
			}
			System.out.println("ERROR 발생!!");
			return "membershipM";
		}
		List<MembershipDto> list = membershipService.getMembershipDate(dto.getEndDate());
		List<MembershipDto> selectedDateList = list.stream().collect(Collectors.toList());

		model.addAttribute("selectedDateList", selectedDateList);

		System.out.println(selectedDateList);

		return "membershipM";
	}

	// membership 회원 삭제 관련 매핑
	@GetMapping("/membership_delete")
	public void membership_delete(@RequestParam String id) {
		log.info("GET /membership_M delete");
	}

	@PostMapping("/membership_delete")
	public String membership_delete_post(@RequestParam String id) {
		log.info("POST /membership_M delete");
		membershipService.removeMembership(id);
		return "redirect:membershipM";
	}

	// 유저 멤버십페이지에서 WM_1 결제
	@GetMapping("/membership/request1")
	public @ResponseBody membershipResponse pay1() {
		// url
		String url = "https://kapi.kakao.com/v1/payment/ready";

		// header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + ADMIN_KEY);
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		// parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TCSUBSCRIP");
		params.add("partner_order_id", "subscription_order_id_1");
		params.add("partner_user_id", "subscription_user_id_1");
		params.add("item_name", "WATERMELON 스트리밍 정기결제");
		params.add("quantity", "1");
		params.add("total_amount", "6900");
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:8080/app/membership/success1");
		params.add("fail_url", "http://localhost:8080/app/membership/fail");
		params.add("cancel_url", "http://localhost:8080/app/membership/cancel");

		// HEADER + PARAMETER
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

		// REQUEST
		RestTemplate rt = new RestTemplate();
		membershipResponse response = rt.postForObject(url, entity, membershipResponse.class);
		System.out.println(response);

		return response;
	}

	// 유저 멤버십페이지에서 WM_2 결제
	@GetMapping("/membership/request2")
	public @ResponseBody membershipResponse pay2() {
		// url
		String url = "https://kapi.kakao.com/v1/payment/ready";

		// header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + ADMIN_KEY);
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		// parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TCSUBSCRIP");
		params.add("partner_order_id", "subscription_order_id_1");
		params.add("partner_user_id", "subscription_user_id_1");
		params.add("item_name", "WATERMELON 스트리밍+오프라인 정기결제");
		params.add("quantity", "1");
		params.add("total_amount", "9900");
		params.add("tax_free_amount", "0");
		params.add("approval_url", "http://localhost:8080/app/membership/success2");
		params.add("fail_url", "http://localhost:8080/app/membership/fail");
		params.add("cancel_url", "http://localhost:8080/app/membership/cancel");

		// HEADER + PARAMETER
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

		// REQUEST
		RestTemplate rt = new RestTemplate();
		membershipResponse response = rt.postForObject(url, entity, membershipResponse.class);
		System.out.println(response);

		return response;
	}

	/// 유저 멤버십페이지에서 WM_1 결제 성공시 매핑
	@GetMapping("/membership/success1")
	public @ResponseBody String success1(MembershipDto dto, Authentication authentication, HttpServletRequest request) {
		log.info("GET /membership/success1");
		// 멤버십DB에 정보 ADD
		membershipService.addMembership(dto, authentication, request);
		log.info(dto.toString());
		return "Subscribe Success!";
	}

	// 유저 멤버십페이지에서 WM_2 결제 성공시 매핑
	@GetMapping("/membership/success2")
	public @ResponseBody String success2(MembershipDto dto, Authentication authentication, HttpServletRequest request) {
		log.info("GET /membership/success2");
		// 멤버십DB에 정보 ADD
		membershipService.addMembership(dto, authentication, request);
		log.info(dto.toString());
		return "Subscribe Success!";
	}

	// 유저 멤버십페이지에서 결제 취소 매핑
	@GetMapping("/membership/cancel")
	public @ResponseBody String cancel() {
		log.info("GET /membership/cancel");
		return "Subscribe Cancel..";
	}

	// 유저 멤버십페이지에서 결제 실패 매핑
	@GetMapping("/membership/fail")
	public @ResponseBody String fail() {
		log.info("GET /membership/fail");
		return "Subscribe Fail..";
	}

}

@Data
class membershipResponse {
	private String tid;
	private boolean tms_result;
	private String next_redirect_app_url;
	private String next_redirect_mobile_url;
	private String next_redirect_pc_url;
	private String android_app_scheme;
	private String ios_app_scheme;
	private String created_at;
}

class MemberDtoEditor extends PropertyEditorSupport {
	// String->Object
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("MemberDtoEditor's setAsText : " + text);
		setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}
}