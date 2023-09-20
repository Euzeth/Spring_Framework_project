package com.test.app.Domain.Service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.Config.auth.PrincipalDetails;
import com.test.app.Controller.MembershipController;
import com.test.app.Domain.Dto.MembershipDto;
import com.test.app.Domain.Mapper.MembershipMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MembershipService{
	
	@Autowired
	MembershipMapper membershipMapper;
	
	@Autowired
	MembershipController membershipController;
	
	
	public List<MembershipDto> getAllMember(){
		return membershipMapper.membershipsearchAll();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void addMembership(MembershipDto dto, Authentication authentication, HttpServletRequest request) {
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusMonths(1).minusDays(1);
		String requestURL = request.getRequestURL().toString();
		
		//request1, 2에 따라서 코드 다르게 적용
		if (requestURL.contains("/membership/success1")) {
	        dto.setMembershipCode("WMM_1");
	    } else if (requestURL.contains("/membership/success2")) {
	        dto.setMembershipCode("WMM_2");
	    }
		
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		dto.setId(principalDetails.getUsername());
		dto.setName(principalDetails.getName());
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		membershipMapper.insert(dto);

	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void modifyMembership(MembershipDto dto) {
		membershipMapper.update(dto);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void removeMembership(String id) {
		membershipMapper.delete(id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<MembershipDto> getMembershipCode(String membershipCode) {
		List<MembershipDto> selectedCode = membershipMapper.selectCode(membershipCode);
        return selectedCode;
    }
	
	@Transactional(rollbackFor = Exception.class)
	public List<MembershipDto> getMembershipDate(LocalDate endDate){
		List<MembershipDto> selectedDate = membershipMapper.selectDate(endDate);
		return selectedDate;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public MembershipDto getMembershipId(String id) {
		MembershipDto selectedId = membershipMapper.selectId(id);
		return selectedId;
	}
	
	
}