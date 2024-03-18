 package com.webWeavers.weaveGlow.biz.subcategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("subcategoryDAO")
public class SubCategoryDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECTALL = "";
	private static final String SELECTONE = "";
	
	private static final String INSERT = "";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	
	public List<SubCategoryDTO> selectAll(SubCategoryDTO SubCategoryDTO) {
		return (List<SubCategoryDTO>)jdbcTemplate.query(SELECTALL, new SubCategoryRowMapper1());
	}

	public SubCategoryDTO selectOne(SubCategoryDTO subCategoryDTO) {
		
	}

	public boolean insert(SubCategoryDTO subCategoryDTO) {
		
	}

	public boolean update(SubCategoryDTO subCategoryDTO) {
		
	}

	public boolean delete(SubCategoryDTO subCategoryDTO) {
		
	}

}

class SubCategoryRowMapper1 implements RowMapper<SubCategoryDTO> {
	@Override
	public SubCategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
	}
}

