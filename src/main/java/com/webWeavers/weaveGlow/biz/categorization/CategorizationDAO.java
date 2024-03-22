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

	private static final String SELECTALL = "SELECT CATEGORIZATION_PK, PRODUCT_PK, SUBCATEGORY_PK FROM CATEGORIZATION WHERE CATEGORIZATION_PK=?";
	private static final String SELECTONE = "";

	private static final String INSERT = "INSERT INTO CATEGORIZATION (PRODUCT_PK, SUBCATEGORY_PK) VALUES (?,?)";
	private static final String UPDATE = "UPDATE CATEGORIZATION SET SUBCATEGORY_PK = ? WHERE PRODUCT_PK = ?";
	private static final String DELETE = "";

	public List<CategorizationDTO> selectAll(CategorizationDTO categorizationDTO) {
		Object[] args = { categorizationDTO.getCategorizationPK() };
		try {
			return jdbcTemplate.query(SELECTALL, args, new CategorizationRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public CategorizationDTO selectOne(CategorizationDTO categorizationDTO) {
		return null;
	}

	public boolean insert(CategorizationDTO categorizationDTO) {
		int result = jdbcTemplate.update(INSERT, categorizationDTO.getProductPK(),
				categorizationDTO.getSubcategoryPK());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean update(CategorizationDTO categorizationDTO) {
		int result = jdbcTemplate.update(UPDATE, categorizationDTO.getProductPK(),
				categorizationDTO.getSubcategoryPK());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(CategorizationDTO categorizationDTO) {
		return false;
	}

}

class CategorizationRowMapper implements RowMapper<CategorizationDTO> {
	@Override
	public CategorizationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategorizationDTO data = new CategorizationDTO();
		data.setCategorizationPK(rs.getInt("CATEGORIZATION_PK"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setSubcategoryPK(rs.getInt("SUBCATEGORY_PK"));
		return null;
	}
}
