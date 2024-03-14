package com.webWeavers.weaveGlow.biz.buyproduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("buyProductDAO")
public class BuyProductDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECTALL = "SELECT B.BUYPRODUCT_PK, B.BUYPRODUCT_CNT, S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_DELIVERYADDRESS, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_PK, IF(R.REVIEW_PK IS NOT NULL, 1, 0) REVIEWCHECK FROM BUYPRODUCT B JOIN SERIAL S ON(B.SERIAL_PK= S.SERIAL_PK) JOIN PRODUCT P ON(B.PRODUCT_PK=P.PRODUCT_PK) LEFT JOIN REVIEW R ON (B.BUYPRODUCT_PK = R.BUYPRODUCT_PK) WHERE S.MEMBER_ID=? ORDER BY B.SERIAL_PK DESC";
	private static final String SELECTALL_RECENT = "SELECT B.BUYPRODUCT_CNT, S.SERIAL_PK, S.SERIAL_REGDATE, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_PK FROM SERIAL S JOIN BUYPRODUCT B ON(B.SERIAL_PK= S.SERIAL_PK) JOIN PRODUCT P ON(B.PRODUCT_PK=P.PRODUCT_PK) WHERE S.MEMBER_ID= ? AND S.SERIAL_PK = (SELECT MAX(SERIAL_PK) FROM SERIAL)";
	
	private static final String SELECTONE = "SELECT B.BUYPRODUCT_CNT, S.SERIAL_PK, S.SERIAL_REGDATE, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_PK FROM BUYPRODUCT B JOIN SERIAL S ON B.SERIAL_PK=S.SERIAL_PK JOIN PRODUCT P ON B.PRODUCT_PK=P.PRODUCT_PK WHERE B.BUYPRODUCT_PK = ?";
	private static final String INSERT = "INSERT INTO BUYPRODUCT (BUYPRODUCT_PK,CART_CNT,SERIAL_PK)VALUES(?,?,(SELECT MAX(SPK) FROM SERIAL))";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	
	public List<BuyProductDTO> selectAll(BuyProductDTO buyProductDTO) {
		Object[] args = { buyProductDTO.getMemberID() };
			if (buyProductDTO.getSearchCondition().equals("buyproduct")) {
				return (List<BuyProductDTO>)jdbcTemplate.query(SELECTALL, args, new BuyProductRowMapper1());
			} else if (buyProductDTO.getSearchCondition().equals("buyproduct_recent")) {
				return (List<BuyProductDTO>)jdbcTemplate.query(SELECTALL_RECENT, args, new BuyProductRowMapper2());
			}
			return null;
	}

	public BuyProductDTO selectOne(BuyProductDTO buyProductDTO) {
		Object[] args = { buyProductDTO.getBuyProductPK() };
		return jdbcTemplate.queryForObject(SELECTONE, args, new BuyProductRowMapper2());
		
	}

	public boolean insert(BuyProductDTO buyProductDTO) {
		int result = jdbcTemplate.update(INSERT, buyProductDTO.getBuyProductPK(), buyProductDTO.getCartCnt());
		if(result<=0) {
			return false;
		}
		return true;
	}

//	private boolean update(BuyProductDTO bDTO) {
//
//		return false;
//	}
//
//	private boolean delete(BuyProductDTO bDTO) {
//
//		return false;
//	}
}

class BuyProductRowMapper1 implements RowMapper<BuyProductDTO> {
	@Override
	public BuyProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BuyProductDTO data = new BuyProductDTO();
		data.setBuyProductPK(rs.getInt("BUYPRODUCT_PK"));
		data.setSerialPK(rs.getInt("SERIAL_PK"));
		data.setSerialRegdate(rs.getDate("SERIAL_REGDATE"));
		data.setSerialDeliveryAddress(rs.getString("SERIAL_DELIVERYADDRESS"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setCartCnt(rs.getInt("CART_CNT"));
		data.setReviewCheck(rs.getInt("REVIEWCHECK"));
		return data;
	}
}

class BuyProductRowMapper2 implements RowMapper<BuyProductDTO> {
	@Override
	public BuyProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BuyProductDTO data = new BuyProductDTO();
		data.setSerialPK(rs.getInt("SERIAL_PK"));
		data.setSerialRegdate(rs.getDate("SERIAL_REGDATE"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setCartCnt(rs.getInt("CART_CNT"));
		return data;
	}
}
