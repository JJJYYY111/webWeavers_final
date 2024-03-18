 package com.webWeavers.weaveGlow.biz.grade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
		return (List<GradeDTO>)jdbcTemplate.query(SELECTALL, new GradeRowMapper1());
	}

	public GradeDTO selectOne(GradeDTO gradeDTO) {
		
	}

	public boolean insert(GradeDTO gradeDTO) {
		
	}

	public boolean update(GradeDTO gradeDTO) {
		
	}

	public boolean delete(GradeDTO gradeDTO) {
		
	}

}

class GradeRowMapper1 implements RowMapper<GradeDTO> {
	@Override
	public GradeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
	}
}

