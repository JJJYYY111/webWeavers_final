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
	
	// 구매목록페이지
	private static final String SELECTALL_CHECKOUTLIST = "SELECT\r\n"
			+ "	S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_DELIVERYADDRESS,\r\n"
			+ "	B.BUYPRODUCT_PK, B.BUYPRODUCT_CNT,\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_NAME,\r\n"
			+ "	IF(R.REVIEW_PK IS NOT NULL, 1, 0) REVIEWCHECK\r\n"
			+ "FROM SERIAL S\r\n"
			+ "LEFT JOIN BUYPRODUCT B ON S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "LEFT JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "LEFT JOIN REVIEW R ON B.BUYPRODUCT_PK = R.BUYPRODUCT_PK\r\n"
			+ "WHERE S.MEMBER_ID = ?\r\n"
			+ "ORDER BY S.SERIAL_PK DESC";
	// 구매완료페이지_최근구매내역
	private static final String SELECTALL_CHECKOUTSUCCESS = "SELECT\r\n"
			+ "	S.SERIAL_PK, S.SERIAL_REGDATE, B.BUYPRODUCT_PK, B.BUYPRODUCT_CNT,\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG\r\n"
			+ "FROM SERIAL S\r\n"
			+ "JOIN BUYPRODUCT B ON B.SERIAL_PK = S.SERIAL_PK\r\n"
			+ "JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "WHERE S.MEMBER_ID = ? AND S.SERIAL_PK = (SELECT MAX(SERIAL_PK) FROM SERIAL)";
	// ?
	private static final String SELECTONE = "SELECT B.BUYPRODUCT_PK, B.BUYPRODUCT_CNT, S.SERIAL_PK, S.SERIAL_REGDATE, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_PK FROM BUYPRODUCT B JOIN SERIAL S ON B.SERIAL_PK=S.SERIAL_PK JOIN PRODUCT P ON B.PRODUCT_PK=P.PRODUCT_PK WHERE B.BUYPRODUCT_PK = ?";
	// 구매상품추가
	private static final String INSERT = "INSERT INTO BUYPRODUCT (PRODUCT_PK, SERIAL_PK, BUYPRODUCT_CNT) VALUES (?, (SELECT MAX(SERIAL_PK) FROM SERIAL), ?)";
	// 구매상품별 주문상태변경_(관리자)주문관리페이지
	private static final String UPDATE = "UPDATE BUYPRODUCT SET BUYPRODUCT_STATUS = ? WHERE BUYPRODUCT_PK = ?";
	private static final String DELETE = "";
	
	public List<BuyProductDTO> selectAll(BuyProductDTO buyProductDTO) {
		Object[] args = { buyProductDTO.getMemberID() };
		try {
			if (buyProductDTO.getSearchCondition().equals("checkoutList")) {
				return jdbcTemplate.query(SELECTALL_CHECKOUTLIST, args, new BuyProductRowMapper1());
			} else if (buyProductDTO.getSearchCondition().equals("checkoutSuccess")) {
				return jdbcTemplate.query(SELECTALL_CHECKOUTSUCCESS, args, new BuyProductRowMapper2());
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;	
	}

	public BuyProductDTO selectOne(BuyProductDTO buyProductDTO) {
		Object[] args = { buyProductDTO.getBuyProductPK() };
	try {
		return jdbcTemplate.queryForObject(SELECTONE, args, new BuyProductRowMapper2());
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}
}

	public boolean insert(BuyProductDTO buyProductDTO) {
		int result = jdbcTemplate.update(INSERT, buyProductDTO.getProductPK(), buyProductDTO.getBuyProductCnt());
		if(result<=0) {
			return false;
		}
		return true;
	}

	public boolean update(BuyProductDTO buyProductDTO) {
		try {
			int result = jdbcTemplate.update(UPDATE, buyProductDTO.getBuyProductStatus(), buyProductDTO.getBuyProductPK());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
//
//	private boolean delete(BuyProductDTO buyProductDTO) {
//
//		return false;
//	}
	
}

class BuyProductRowMapper1 implements RowMapper<BuyProductDTO> {
	@Override
	public BuyProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BuyProductDTO data = new BuyProductDTO();
		data.setSerialPK(rs.getInt("SERIAL_PK"));
		data.setSerialRegdate(rs.getDate("SERIAL_REGDATE"));
		data.setSerialDeliveryAddress(rs.getString("SERIAL_DELIVERYADDRESS"));
		data.setBuyProductPK(rs.getInt("BUYPRODUCT_PK"));
		data.setBuyProductCnt(rs.getInt("BUYPRODUCT_CNT"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
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
		data.setBuyProductPK(rs.getInt("BUYPRODUCT_PK"));
		data.setBuyProductCnt(rs.getInt("BUYPRODUCT_CNT"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		return data;
	}
}
