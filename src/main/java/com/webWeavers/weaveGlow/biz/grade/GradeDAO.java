 package com.webWeavers.weaveGlow.biz.grade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.webWeavers.weaveGlow.biz.category.CategoryDTO;

@Repository("gradeDAO")
public class GradeDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECTALL = "";
	private static final String SELECTONE = "";
	
	private static final String INSERT = "";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	
	public List<GradeDTO> selectAll(GradeDTO gradeDTO) {
		return (List<GradeDTO>)jdbcTemplate.query(SELECTALL, new GradeRowMapper());
	}

	public GradeDTO selectOne(GradeDTO gradeDTO) {
		return null;
	}

	public boolean insert(GradeDTO gradeDTO) {
		int result = jdbcTemplate.update(UPDATE,gradeDTO.getGradePK(),gradeDTO.getGradeName(),gradeDTO.getGradeDiscountRate());
		if(result<=0) {
			return false;
		}
		return true;
	}

	public boolean update(GradeDTO gradeDTO) {
		int result = jdbcTemplate.update(UPDATE,gradeDTO.getGradePK(),gradeDTO.getGradeName(),gradeDTO.getGradeDiscountRate());
		if(result<=0) {
			return false;
		}
		return true;
	}

	public boolean delete(GradeDTO gradeDTO) {
		return false;
	}

}

class GradeRowMapper implements RowMapper<GradeDTO> {
	@Override
	public GradeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		GradeDTO data = new GradeDTO();
		data.setGradePK(rs.getInt("GRADE_PK"));
		data.setGradeName(rs.getString("GRADE_NAME"));
		data.setGradeDiscountRate(rs.getInt("GRADE_DISCOUNTRATE"));
		return data;
	}
}

