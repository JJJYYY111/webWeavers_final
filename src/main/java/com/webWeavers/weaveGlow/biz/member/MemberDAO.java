package com.webWeavers.weaveGlow.biz.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SELECTALL = "";
	private static final String SELECTONE_LOGIN = "SELECT M.MEMBER_ID, M.MEMBER_PASSWORD, M.MEMBER_NAME, M.MEMBER_BIRTH, M.MEMBER_PHONE, M.MEMBER_NICKNAME, M.MEMBER_EMAIL, M.MEMBER_MARKETING, GRADE_PK "
			+ "	FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PASSWORD=?";
	private static final String SELECTONE_MEMBER_NICKNAMECHECK = "SELECT MEMBER_ID, M.MEMBER_PASSWORD, M.MEMBER_NAME, M.MEMBER_BIRTH, M.MEMBER_PHONE, M.MEMBER_NICKNAME, M.MEMBER_EMAIL, M.MEMBER_MARKETING, GRADE_PK "
			+ "   FROM MEMBER WHERE MEMBER_NICKNAME=?";
	private static final String SELECTONE_MEMBERINFO = "SELECT M.MEMBER_ID, M.MEMBER_PASSWORD, M.MEMBER_NAME, M.MEMBER_BIRTH, M.MEMBER_PHONE, M.MEMBER_NICKNAME, M.MEMBER_EMAIL, M.MEMBER_MARKETING, M.GRADE_PK,  G.GRADE_NAME"
			+ "  FROM MEMBER M JOIN GRADE G " + "   ON (M.GRADE_PK = G.GRADE_PK) WHERE M.MEMBER_ID=?";
	private static final String SELECTONE_IDCHECK = "SELECT MEMBER_ID, MEMBER_PASSWORD, MEMBER_NAME, MEMBER_BIRTH, MEMBER_PHONE, MEMBER_NICKNAME, MEMBER_EMAIL, MEMBER_MARKETING, GRADE_PK "
			+ "   FROM MEMBER WHERE MEMBER_ID=?";

	private static final String INSERT = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PASSWORD, MEMBER_NAME, MEMBER_BIRTH, MEMBER_PHONE, MEMBER_NICKNAME, MEMBER_EMAIL, MEMBER_MARKETING) VALUES (?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE MEMBER SET MEMBER_EMAIL=?, MEMBER_NICKNAME=?, MEMBER_PHONE=?, MEMBER_PASSWORD=? WHERE MEMBER_ID=?";

	private static final String UPDATE_UNREGISTER = "UPDATE MEMBER SET GRADE_PK=5 WHERE MEMBER_ID=?";

	private static final String DELETE = "";

	public List<MemberDTO> selectAll(MemberDTO memberDTO) {
		return null;
		//return (List<MemberDTO>)jdbcTemplate.query(SELECTALL, new MemberRowMapper1());
	}

	public MemberDTO selectOne(MemberDTO memberDTO) {

		if (memberDTO.getSearchCondition().equals("login")) {
			Object[] args = { memberDTO.getMemberID(), memberDTO.getMemberPassword() };
			return jdbcTemplate.queryForObject(SELECTONE_LOGIN, args, new MemberRowMapper2());
		} 
		else if(memberDTO.getSearchCondition().equals("MEMBER_NICKNAMECheck")) {
			Object[] args = { memberDTO.getMemberNickname() };
			return jdbcTemplate.queryForObject(SELECTONE_MEMBER_NICKNAMECHECK, args, new MemberRowMapper2());
		}
		else if(memberDTO.getSearchCondition().equals("memberInfo")) {
			Object[] args = { memberDTO.getMemberID() };
			return jdbcTemplate.queryForObject(SELECTONE_MEMBERINFO, args, new MemberRowMapper1());
		}
		else if(memberDTO.getSearchCondition().equals("idCheck")) {
			Object[] args = { memberDTO.getMemberID() };
			return jdbcTemplate.queryForObject(SELECTONE_IDCHECK, args, new MemberRowMapper2());
		}
		return null;
	}
	
	public boolean insert(MemberDTO memberDTO) {
		int result = jdbcTemplate.update(INSERT,memberDTO.getMemberID(),memberDTO.getMemberPassword(),memberDTO.getMemberName(),(memberDTO.getMemberBirth().getTime()),memberDTO.getMemberPhone(),memberDTO.getMemberNickname(),memberDTO.getMemberEmail(),memberDTO.getMemberMarketing());
		if(result<=0) {
			return false;
		}
		return true;
	}
	public boolean update(MemberDTO memberDTO) {
		int result;
		if (memberDTO.getSearchCondition().equals("updateInfo")) {
			result = jdbcTemplate.update(UPDATE, memberDTO.getMemberID(), memberDTO.getMemberPassword());
			if(result <= 0) {
				return false;
			}
		} 
		else if(memberDTO.getSearchCondition().equals("unregisterUpdateInfo")) {
			Object[] args = { memberDTO.getMemberNickname() };
			result = jdbcTemplate.update(UPDATE_UNREGISTER, memberDTO.getMemberNickname());
			if(result <= 0) {
				return false;
			}
		}
		return true;		
	}
	
	
	private boolean delete(MemberDTO memberDTO) {
		
		return false;
	}

	// RowMapper 인터페이스 사용
	
	class MemberRowMapper1 implements RowMapper<MemberDTO> {
		@Override
		public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberDTO data = new MemberDTO();
			data.setMemberID(rs.getString("MEMBER_ID"));
			data.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
			data.setMemberName(rs.getString("MEMBER_NAME"));
			data.setMemberBirth(rs.getDate("MEMBER_BIRTH"));
			data.setMemberPhone(rs.getString("MEMBER_PHONE"));
			data.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
			data.setMemberEmail(rs.getString("MEMBER_EMAIL"));
			data.setMemberMarketing(rs.getInt("MEMBER_MARKETING"));
			data.setGradePK(rs.getInt("GRADE_PK"));
			data.setGradeName(rs.getString("GRADE"));
			return data;
		}
	}
	class MemberRowMapper2 implements RowMapper<MemberDTO> {
		@Override
		public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberDTO data = new MemberDTO();
			data.setMemberID(rs.getString("MEMBER_ID"));
			data.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
			data.setMemberName(rs.getString("MEMBER_NAME"));
			data.setMemberBirth(rs.getDate("MEMBER_BIRTH"));
			data.setMemberPhone(rs.getString("MEMBER_PHONE"));
			data.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
			data.setMemberEmail(rs.getString("MEMBER_EMAIL"));
			data.setMemberMarketing(rs.getInt("MEMBER_MARKETING"));
			data.setGradePK(rs.getInt("GRADE_PK"));
			return data;
		}
	}
	class MemberRowMapper3 implements RowMapper<MemberDTO> {
		@Override
		public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberDTO data = new MemberDTO();
			data.setMemberID(rs.getString("MEMBER_ID"));
			data.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
			data.setMemberPhone(rs.getString("MEMBER_PHONE"));
			data.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
			data.setMemberEmail(rs.getString("MEMBER_EMAIL"));
			return data;
		}
	}
	class MemberRowMapper4 implements RowMapper<MemberDTO> {
		@Override
		public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemberDTO data = new MemberDTO();
			data.setMemberID(rs.getString("MEMBER_ID"));
			return data;
		}
	}
	

}
