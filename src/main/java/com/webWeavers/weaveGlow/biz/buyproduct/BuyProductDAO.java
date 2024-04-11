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
			+ "	IF(R.REVIEW_PK IS NOT NULL, 1, 0) REVIEWCHECK\r\n"	// BUYPRODUCT_PK 기준으로 REVIEW, BUYPRODUCT 테이블 조인 -> 구매한 상품의 리뷰 작성 유무 확인
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
			+ "WHERE S.MEMBER_ID = ? AND S.SERIAL_PK = (SELECT MAX(SERIAL_PK) FROM SERIAL)";	// SERIAL테이블에 데이터 INSERT 후 BUYPRODUCT테이블에 데이터 INSERT -> SERIAL_PK 최대값
	// 구매상품선택 > 리뷰수정
	private static final String SELECTONE = "SELECT B.BUYPRODUCT_PK, B.BUYPRODUCT_CNT, S.SERIAL_PK, S.SERIAL_REGDATE, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_PK FROM BUYPRODUCT B JOIN SERIAL S ON B.SERIAL_PK=S.SERIAL_PK JOIN PRODUCT P ON B.PRODUCT_PK=P.PRODUCT_PK WHERE B.BUYPRODUCT_PK = ?";
	// 구매상품추가
	private static final String INSERT = "INSERT INTO BUYPRODUCT (PRODUCT_PK, SERIAL_PK, BUYPRODUCT_CNT) VALUES (?, (SELECT MAX(SERIAL_PK) FROM SERIAL), ?)";
//	private static final String UPDATE = "";
//	private static final String DELETE = "";
	
	public List<BuyProductDTO> selectAll(BuyProductDTO buyProductDTO) {
		Object[] args = { buyProductDTO.getMemberID() };
		try {
			// 구매목록페이지
			if (buyProductDTO.getSearchCondition().equals("checkoutList")) {
				return jdbcTemplate.query(SELECTALL_CHECKOUTLIST, args, new BuyProductRowMapper1());
			}
			// 구매완료페이지_최근구매내역
			else if (buyProductDTO.getSearchCondition().equals("checkoutSuccess")) {
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
		// 구매상품선택 > 리뷰수정
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

//	private boolean update(BuyProductDTO buyProductDTO) {
//		return false;
//	}

//	private boolean delete(BuyProductDTO buyProductDTO) {
//
//		return false;
//	}
	
}

// selectAll 사용자 구매목록 페이지 출력
// selectAll 사용자 구매완료 페이지 최근 구매내역 출력
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
//selectOne 사용자 구매상품선택
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
