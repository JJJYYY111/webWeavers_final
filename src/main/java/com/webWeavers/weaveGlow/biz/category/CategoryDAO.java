 package com.webWeavers.weaveGlow.biz.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("categoryDAO")
public class CategoryDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECTALL = "SELECT CATEGORY_NAME, CATEGORY_PK FROM CATEGORY"; 
	private static final String SELECTONE = "SELECT CATEGORY_NAME, CATEGORY_PK FROM CATEGORY WHERE CATEGORY_PK = ?";
	
	private static final String INSERT = "";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	
	public List<CategoryDTO> selectAll(CategoryDTO categoryDTO) {
		return (List<CategoryDTO>)jdbcTemplate.query(SELECTALL, new CategoryRowMapper());
	}

	public CategoryDTO selectOne(CategoryDTO categoryDTO) {
		Object[] args = { categoryDTO.getCategoryPK(), categoryDTO.getCategoryName() };
		return jdbcTemplate.queryForObject(SELECTONE, args, new CategoryRowMapper());
	}

	public boolean insert(CategoryDTO categoryDTO) {
		return false;
	}

	public boolean update(CategoryDTO categoryDTO) {
		return false;
	}

	public boolean delete(CategoryDTO categoryDTO) {
		return false;
	}

}

class CategoryRowMapper implements RowMapper<CategoryDTO> {
	@Override
	public CategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryDTO data = new CategoryDTO();
		data.setCategoryPK(rs.getInt("CATEGORY_PK"));
		data.setCategoryName(rs.getString("CATEGORY_NAME"));
		return data;
	}
}

