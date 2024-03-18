 package com.webWeavers.weaveGlow.biz.categorization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.webWeavers.weaveGlow.biz.product.ProductDTO;

@Repository("categorizationDAO")
public class CategorizationDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECTALL = "CATEGORIZATION_PK, PRODUCT_PK, SUBCATEGORY_PK";
	private static final String SELECTONE = "";
	
	private static final String INSERT = "";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	
	public List<CategorizationDTO> selectAll(CategorizationDTO categorizationDTO) {
		return (List<CategorizationDTO>)jdbcTemplate.query(SELECTALL, new CategorizationRowMapper1());
	}

	public CategorizationDTO selectOne(CategorizationDTO categorizationDTO) {
		
	}

	public boolean insert(CategorizationDTO categorizationDTO) {
		
	}

//	public boolean update(CategorizationDTO categorizationDTO) {
//		
//	}

//	public boolean delete(CategorizationDTO categorizationDTO) {
//		
//	}

}

class CategorizationRowMapper1 implements RowMapper<CategorizationDTO> {
	@Override
	public CategorizationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategorizationDTO data = new CategorizationDTO();
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setSubcategoriPK(rs.getInt("")); 
		return data;
 }
}
class CategorizationRowMapper2 implements RowMapper<CategorizationDTO> {
	@Override
	public CategorizationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
	}
}
