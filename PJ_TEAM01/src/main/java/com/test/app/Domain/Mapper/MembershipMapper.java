package com.test.app.Domain.Mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.app.Domain.Dto.MembershipDto;

@Mapper
public interface MembershipMapper {
	
	@Insert("insert into tbl_membership values(#{id},#{membershipCode},#{name},#{startDate},#{endDate})")
	public int insert(MembershipDto dto);
	
	@Update("update tbl_membership set name=#{name},membershipCode=#{membershipCode},startDate=#{startDate},endDate=#{endDate} where id=#{id}")
	public int update(MembershipDto dto);
	
	@Delete("delete from tbl_membership where id=#{id}")
	public void delete(String id);
	
	@Select("select * from tbl_membership order by membershipCode asc")
	public List<MembershipDto> membershipsearchAll();
	
	@Select("select * from tbl_membership where endDate=#{endDate}")
	public List<MembershipDto> selectDate(LocalDate date);
	
	@Select("select * from tbl_membership where id=#{id}")
	public MembershipDto selectId(String id);
	
	@Select("select * from tbl_membership where membershipCode=#{membershipCode}")
	public List<MembershipDto> selectCode(String membershipCode);
}