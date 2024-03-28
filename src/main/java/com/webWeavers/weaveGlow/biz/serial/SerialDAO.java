package com.webWeavers.weaveGlow.biz.serial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("serialDAO")
public class SerialDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 전체 주문현황_(관리자)주문관리페이지
	private static final String SELECTALL_ORDERLIST = "WITH BS AS (\r\n"	// 주문번호별 상품 종류의 개수를 구하기 위한 BUYPRODUCT 서브쿼리
			+ "	SELECT\r\n"
			+ "		SERIAL_PK,\r\n"
			+ "		COUNT(BUYPRODUCT_PK) - 1 AS CNT\r\n"					// '상품이름' 외 ? 개
			+ "	FROM BUYPRODUCT\r\n"
			+ "	GROUP BY SERIAL_PK\r\n"
			+ ")\r\n"
			+ "SELECT\r\n"
			+ "	S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_STATUS, S.SERIAL_DELIVERYADDRESS,\r\n"
			+ "	S.MEMBER_ID, M.MEMBER_NAME,\r\n"
			+ "	BS.CNT, MAX(P.PRODUCT_NAME) AS PRODUCT_NAME,\r\n"
			+ "	SUM(P.PRODUCT_PRICE * B.BUYPRODUCT_CNT) AS TOTALPRICE\r\n"	// 주문번호별 총 금액
			+ "FROM SERIAL S\r\n"
			+ "LEFT JOIN BUYPRODUCT B ON S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "LEFT JOIN BS ON S.SERIAL_PK = BS.SERIAL_PK\r\n"
			+ "LEFT JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "LEFT JOIN MEMBER M ON S.MEMBER_ID = M.MEMBER_ID\r\n"
			+ "GROUP BY S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_STATUS, S.SERIAL_DELIVERYADDRESS, S.MEMBER_ID, M.MEMBER_NAME, BS.CNT\r\n"
			+ "ORDER BY S.SERIAL_PK DESC";
	// 주문번호별 상품현황_(관리자)주문관리페이지
	private static final String SELECTALL_ORDERPRODUCT = "SELECT\r\n"
			+ "	S.SERIAL_PK, S.SERIAL_REGDATE, M.MEMBER_NAME,\r\n"
			+ "	P.PRODUCT_NAME, B.BUYPRODUCT_CNT,\r\n"
			+ "	(P.PRODUCT_PRICE * B.BUYPRODUCT_CNT) AS SUBTOTALPRICE\r\n"	// 상품별 금액
			+ "FROM SERIAL S\r\n"
			+ "LEFT JOIN BUYPRODUCT B ON S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "LEFT JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "LEFT JOIN MEMBER M ON S.MEMBER_ID = M.MEMBER_ID\r\n"
			+ "WHERE S.SERIAL_PK = ?\r\n"
			+ "ORDER BY B.BUYPRODUCT_PK DESC";
	// 주문검색_(관리자)주문관리페이지
	private static final String SELECTALL_ORDERSEARCH = "WITH BS AS (\r\n"
			+ "	SELECT\r\n"
			+ "		SERIAL_PK,\r\n"
			+ "		COUNT(BUYPRODUCT_PK) - 1 AS CNT\r\n"
			+ "	FROM BUYPRODUCT\r\n"
			+ "	GROUP BY SERIAL_PK\r\n"
			+ ")\r\n"
			+ "SELECT\r\n"
			+ "	S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_STATUS, S.SERIAL_DELIVERYADDRESS,\r\n"
			+ "	S.MEMBER_ID, M.MEMBER_NAME,\r\n"
			+ "	BS.CNT, MAX(P.PRODUCT_NAME) AS PRODUCT_NAME,\r\n"
			+ "	SUM(P.PRODUCT_PRICE * B.BUYPRODUCT_CNT) AS TOTALPRICE\r\n"
			+ "FROM SERIAL S\r\n"
			+ "LEFT JOIN BUYPRODUCT B ON S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "LEFT JOIN BS ON S.SERIAL_PK = BS.SERIAL_PK\r\n"
			+ "LEFT JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "LEFT JOIN MEMBER M ON S.MEMBER_ID = M.MEMBER_ID\r\n"
			+ "WHERE \r\n"
			+ "	S.SERIAL_STATUS LIKE CONCAT('%',?,'%')\r\n"
			+ "	AND M.MEMBER_NAME LIKE CONCAT('%',?,'%')\r\n"
			+ "	AND DATE(S.SERIAL_REGDATE) = ?\r\n"
			+ "GROUP BY S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_STATUS, S.SERIAL_DELIVERYADDRESS, S.MEMBER_ID, M.MEMBER_NAME, BS.CNT\r\n"
			+ "ORDER BY S.SERIAL_PK DESC";
//	private static final String SELECTONE = "";
	// 주문추가_(관리자)주문관리페이지
	private static final String INSERT = "INSERT INTO SERIAL (MEMBER_ID, SERIAL_DELIVERYADDRESS) VALUES (?, ?)";
	// 주문상태변경_(관리자)주문관리페이지
	private static final String UPDATE = "UPDATE SERIAL SET SERIAL_STATUS = ? WHERE SERIAL_PK = ?";
//	private static final String DELETE = "";

	public List<SerialDTO> selectAll(SerialDTO serialDTO) {
		Object args1[] = { serialDTO.getSerialPK() };
		Object args2[] = { serialDTO.getSerialStatus(), serialDTO.getMemberName(), serialDTO.getSerialRegdate() };
		try {
			if (serialDTO.getSearchCondition().equals("orderList")) {
				return jdbcTemplate.query(SELECTALL_ORDERLIST, new SerialListAdminRowMapper());
			} else if (serialDTO.getSearchCondition().equals("orderProduct")) {
				return jdbcTemplate.query(SELECTALL_ORDERPRODUCT, args1, new SerialDetailListAdminRowMapper());
			} else if (serialDTO.getSearchCondition().equals("orderSearch")) {
				return jdbcTemplate.query(SELECTALL_ORDERSEARCH, args2, new SerialListAdminRowMapper());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

//	private SerialDTO selectOne(SerialDTO serialDTO) {
//		return null;
//	}

	public boolean insert(SerialDTO serialDTO) {
		try {
			int result = jdbcTemplate.update(INSERT, serialDTO.getMemberID(), serialDTO.getSerialDeliveryAddress());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(SerialDTO serialDTO) {
		try {
			int result = jdbcTemplate.update(UPDATE, serialDTO.getSerialStatus(), serialDTO.getSerialPK());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

//	private boolean delete(SerialDTO serialDTO) {
//		return false;
//	}

}

// selectAll_관리자_주문관리페이지
class SerialListAdminRowMapper implements RowMapper<SerialDTO>{
	@Override
	public SerialDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SerialDTO data = new SerialDTO();
		data.setSerialPK(rs.getInt("SERIAL_PK"));
		data.setSerialRegdate(rs.getDate("SERIAL_REGDATE"));
		data.setSerialStatus(rs.getString("SERIAL_STATUS"));
		data.setSerialDeliveryAddress(rs.getString("SERIAL_DELIVERYADDRESS"));
		data.setMemberID(rs.getString("MEMBER_ID"));
		
		data.setMemberName(rs.getString("MEMBER_NAME"));
		data.setBuyProductCnt(rs.getInt("CNT"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setTotalPrice(rs.getInt("TOTALPRICE"));
		
		return data;
	}
}

//selectAll_관리자_주문상세페이지
class SerialDetailListAdminRowMapper implements RowMapper<SerialDTO>{
	@Override
	public SerialDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SerialDTO data = new SerialDTO();
		data.setSerialPK(rs.getInt("SERIAL_PK"));
		data.setSerialRegdate(rs.getDate("SERIAL_REGDATE"));
		
		data.setMemberName(rs.getString("MEMBER_NAME"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setBuyProductCnt(rs.getInt("BUYPRODUCT_CNT"));
		data.setTotalPrice(rs.getInt("SUBTOTALPRICE"));
		
		return data;
	}
}