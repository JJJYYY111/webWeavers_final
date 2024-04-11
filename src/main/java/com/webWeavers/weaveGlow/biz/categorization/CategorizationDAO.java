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

	private static final String SELECTALL = "SELECT CATEGORIZATION_PK, PRODUCT_PK, SUBCATEGORY_PK FROM CATEGORIZATION WHERE PRODUCT_PK=?";
	// 관리자_상품카테고리 선택
	private static final String SELECTONE = "SELECT CZ.CATEGORIZATION_PK, CZ.PRODUCT_PK, CZ.SUBCATEGORY_PK, C.CATEGORY_NAME FROM CATEGORIZATION CZ JOIN SUBCATEGORY SC ON CZ.SUBCATEGORY_PK = SC.SUBCATEGORY_PK JOIN CATEGORY C ON SC.CATEGORY_PK = C.CATEGORY_PK WHERE PRODUCT_PK=? LIMIT 1";
	// 관리자_상품카테고리 추가
	private static final String INSERT = "INSERT INTO CATEGORIZATION (PRODUCT_PK, SUBCATEGORY_PK) VALUES (?,?)";
	private static final String UPDATE = "UPDATE CATEGORIZATION SET SUBCATEGORY_PK = ? WHERE PRODUCT_PK = ?";
	// 관리자_상품카테고리 삭제
	private static final String DELETE = "DELETE FROM CATEGORIZATION WHERE PRODUCT_PK = ?";

	public List<CategorizationDTO> selectAll(CategorizationDTO categorizationDTO) {
		Object[] args = { categorizationDTO.getProductPK() };
		try {
			return jdbcTemplate.query(SELECTALL, args, new CategorizationRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public CategorizationDTO selectOne(CategorizationDTO categorizationDTO) {
		Object[] args = { categorizationDTO.getProductPK() };
		try {
			return jdbcTemplate.queryForObject(SELECTONE, args, new CategorizationRowMapper2());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insert(CategorizationDTO categorizationDTO) {
		int result = jdbcTemplate.update(INSERT, categorizationDTO.getProductPK(),
				categorizationDTO.getSubCategoryPK());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean update(CategorizationDTO categorizationDTO) {
		int result = jdbcTemplate.update(UPDATE, categorizationDTO.getProductPK(),
				categorizationDTO.getSubCategoryPK());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(CategorizationDTO categorizationDTO) {
		int result = jdbcTemplate.update(DELETE, categorizationDTO.getProductPK());
		if (result <= 0) {
			return false;
		}
		return true;
	}

}

class CategorizationRowMapper implements RowMapper<CategorizationDTO> {
	@Override
	public CategorizationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategorizationDTO data = new CategorizationDTO();
		data.setCategorizationPK(rs.getInt("CATEGORIZATION_PK"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setSubCategoryPK(rs.getInt("SUBCATEGORY_PK"));
		return data;
	}
}

// selectOne 관리자 상품 카테고리 선택
class CategorizationRowMapper2 implements RowMapper<CategorizationDTO> {
	@Override
	public CategorizationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategorizationDTO data = new CategorizationDTO();
		data.setCategorizationPK(rs.getInt("CATEGORIZATION_PK"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setSubCategoryPK(rs.getInt("SUBCATEGORY_PK"));
		data.setCategoryName(rs.getString("CATEGORY_NAME"));
		return data;
	}
}
