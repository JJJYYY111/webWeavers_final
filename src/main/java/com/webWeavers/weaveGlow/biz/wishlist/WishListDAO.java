package com.webWeavers.weaveGlow.biz.wishlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("wishListDAO")
public class WishListDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 마이페이지_찜 목록
	private static final String SELECTALL = "SELECT W.WISHLIST_PK, W.MEMBER_ID, P.PRODUCT_PK, P.PRODUCT_IMG, P.PRODUCT_NAME FROM WISHLIST W "
			+ "JOIN PRODUCT P ON (W.PRODUCT_PK = P.PRODUCT_PK) WHERE MEMBER_ID = ?";
	// 상품상세페이지_찜 유무
	private static final String SELECTONE = "SELECT WISHLIST_PK, MEMBER_ID, PRODUCT_PK FROM WISHLIST WHERE MEMBER_ID = ? AND PRODUCT_PK = ?";
	// 상품 찜 추가
	private static final String INSERT = "INSERT INTO WISHLIST (MEMBER_ID, PRODUCT_PK) VALUES (?, ?);";
//	private static final String UPDATE = "";
	// 상품 찜 취소
	private static final String DELETE = "DELETE FROM WISHLIST WHERE MEMBER_ID = ? AND PRODUCT_PK = ?";

	public List<WishListDTO> selectAll(WishListDTO wishListDTO) {
		Object[] args = { wishListDTO.getMemberID() };
		try {
			return (List<WishListDTO>) jdbcTemplate.query(SELECTALL, args, new WishListRowMapper1());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WishListDTO selectOne(WishListDTO wishListDTO) {
		Object[] args = { wishListDTO.getMemberID(), wishListDTO.getProductPK() };
		try {
			return jdbcTemplate.queryForObject(SELECTONE, args, new WishListRowMapper2());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insert(WishListDTO wishListDTO) {
		try {
			int result = jdbcTemplate.update(INSERT, wishListDTO.getMemberID(), wishListDTO.getProductPK());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

//	private boolean update(WishListDTO wishListDTO) {
//		return false;
//	}

	public boolean delete(WishListDTO wishListDTO) {
		try {
			int result = jdbcTemplate.update(DELETE, wishListDTO.getMemberID(), wishListDTO.getProductPK());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

// selectAll
class WishListRowMapper1 implements RowMapper<WishListDTO> {
	@Override
	public WishListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		WishListDTO data = new WishListDTO();
		data.setWishListPK(rs.getInt("WISHLIST_PK"));
		data.setMemberID(rs.getString("MEMBER_ID"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		return data;
	}
}

// selectOne
class WishListRowMapper2 implements RowMapper<WishListDTO> {
	@Override
	public WishListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		WishListDTO data = new WishListDTO();
		data.setWishListPK(rs.getInt("WISHLIST_PK"));
		data.setMemberID(rs.getString("MEMBER_ID"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		System.out.println("로그2 : " + data);
		return data;
	}
}
