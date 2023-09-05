package com.test.app.Domain.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.app.Domain.Dto.MemberDto;
import com.test.app.Domain.Mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService{
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	private BCryptPasswordEncoder passworEncoder;
	
	public List<MemberDto> getAllMember(){
		
		return memberMapper.membersearchAll();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void addMember(MemberDto dto) {
		String BpassworEncoder = passworEncoder.encode(dto.getPw());
		dto.setPw(BpassworEncoder);
		memberMapper.insert(dto);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void modifyMember(MemberDto dto) {
		memberMapper.update(dto);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void removeMember(String id) {
		memberMapper.delete(id);
	}
	
	public String getMemberName(String id) {
        MemberDto member = memberMapper.selectAt(id);
        if (member != null) {
            return member.getName();
        }
        return null; // 사용자가 없을 경우 예외 처리 필요
    }
	
}