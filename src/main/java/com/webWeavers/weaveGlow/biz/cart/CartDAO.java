package com.webWeavers.weaveGlow.biz.cart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("cartDAO")
public class CartDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 회원 장바구니 목록
	private static final String SELECTALL = "SELECT C.CART_PK, C.CART_CNT, C.MEMBER_ID, P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_IMG, P.PRODUCT_PRICE \r\n"
			+ "FROM CART C \r\n"
			+ "JOIN PRODUCT P ON (C.PRODUCT_PK = P.PRODUCT_PK) \r\n"
			+ "WHERE C.MEMBER_ID = ?";
	// ?
	private static final String SELECTONE = "SELECT C.CART_PK, C.CART_CNT, C.MEMBER_ID, P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_IMG, P.PRODUCT_PRICE \r\n"
			+ "FROM CART C \r\n"
			+ "JOIN PRODUCT P ON (C.PRODUCT_PK = P.PRODUCT_PK) \r\n"
			+ "WHERE C.MEMBER_ID = ? AND C.PRODUCT_PK = ?";
	// 장바구니 추가_상품상세페이지
	private static final String INSERT = "INSERT INTO CART (MEMBER_ID, PRODUCT_PK, CART_CNT) VALUES (?, ?, ?)";
	// 장바구니 개수 추가 (기존 수량 + 추가 수량)_상품상세페이지
	private static final String UPDATE_ADD = "UPDATE CART SET CART_CNT = CART_CNT + ? WHERE MEMBER_ID = ? AND PRODUCT_PK = ?";
	
//	 장바구니 개별 상품 수량 증가_장바구니페이지
//	private static final String UPDATE_UP = "UPDATE CART SET CART_CNT = CART_CNT + 1 WHERE MEMBER_ID = ? AND PRODUCT_PK = ?";
//	 장바구니 개별 상품 수량 감소_장바구니페이지
//	private static final String UPDATE_DOWN = "UPDATE CART SET CART_CNT = CART_CNT - 1 WHERE MEMBER_ID = ? AND PRODUCT_PK = ?";
	
	// 장바구니 개별 상품 수량,감소_장바구니 페이지
	private static final String UPDATE_CNT= "UPDATE CART SET CART_CNT = ? WHERE MEMBER_ID = ? AND PRODUCT_PK = ?";
	// 장바구니 상품 개별 삭제_장바구니페이지
	private static final String DELETE_ONE = "DELETE FROM CART WHERE MEMBER_ID = ? AND PRODUCT_PK = ?";
	// 장바구니 비우기_장바구 페이지
	private static final String DELETE_ALL = "DELETE FROM CART WHERE MEMBER_ID = ?";

	public List<CartDTO> selectAll(CartDTO cartDTO) {
		Object[] arg = { cartDTO.getMemberID() };
		try {
			return (List<CartDTO>) jdbcTemplate.query(SELECTALL, arg, new CartRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public CartDTO selectOne(CartDTO cartDTO) {
		Object[] arg = { cartDTO.getMemberID(), cartDTO.getProductPK() };
		try {
			return jdbcTemplate.queryForObject(SELECTONE, arg, new CartRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insert(CartDTO cartDTO) {
		try {
			int result = jdbcTemplate.update(INSERT, cartDTO.getMemberID(), cartDTO.getProductPK(), cartDTO.getCartCnt());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(CartDTO cartDTO) {
		int result = 0;
		try {
			if (cartDTO.getSearchCondition().equals("cntAdd")) {
				result = jdbcTemplate.update(UPDATE_ADD, cartDTO.getCartCnt(), cartDTO.getMemberID(), cartDTO.getProductPK());
			}
			else if (cartDTO.getSearchCondition().equals("cntUpdate")) {
				result = jdbcTemplate.update(UPDATE_CNT, cartDTO.getCartCnt(), cartDTO.getMemberID(), cartDTO.getProductPK());
			} 
//			else if (cartDTO.getSearchCondition().equals("cntUp")) {
//				result = jdbcTemplate.update(UPDATE_UP, cartDTO.getMemberID(), cartDTO.getProductPK());
//			} else if (cartDTO.getSearchCondition().equals("cntDown")) {
//				result = jdbcTemplate.update(UPDATE_DOWN, cartDTO.getMemberID(), cartDTO.getProductPK());
//			} 
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (result <= 0) {
			return false;
		}
		return true;
	}

	public boolean delete(CartDTO cartDTO) {
		int result = 0;
		try {
			if (cartDTO.getSearchCondition().equals("deleteOne")) {
				result = jdbcTemplate.update(DELETE_ONE, cartDTO.getMemberID(), cartDTO.getProductPK());
			} else if (cartDTO.getSearchCondition().equals("deleteAll")) {
				result = jdbcTemplate.update(DELETE_ALL, cartDTO.getMemberID());
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (result <= 0) {
			return false;
		}
		return true;
	}

}

// selectAll, selectOne
class CartRowMapper implements RowMapper<CartDTO> {
	@Override
	public CartDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartDTO data = new CartDTO();
		data.setCartPK(rs.getInt("CART_PK"));
		data.setCartCnt(rs.getInt("CART_CNT"));
		data.setMemberID(rs.getString("MEMBER_ID"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		return data;
	}
}
