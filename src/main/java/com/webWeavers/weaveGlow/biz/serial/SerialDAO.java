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
	private static final String SELECTALL_ORDERLIST = "SELECT\r\n"
			+ "	S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_DELIVERYADDRESS,\r\n"
			+ "	M.MEMBER_ID, M.MEMBER_NAME, B.BUYPRODUCT_PK, B.BUYPRODUCT_STATUS,\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_STATUS,\r\n"
			+ "	P.PRODUCT_PRICE, B.BUYPRODUCT_CNT,\r\n"
			+ "	P.PRODUCT_PRICE * B.BUYPRODUCT_CNT AS TOTALPRICE\r\n"
			+ "FROM SERIAL S\r\n"
			+ "INNER JOIN BUYPRODUCT B ON\r\n"
			+ "	S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "LEFT JOIN PRODUCT P ON\r\n"
			+ "	B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "LEFT JOIN MEMBER M ON\r\n"
			+ "	S.MEMBER_ID = M.MEMBER_ID\r\n"
			+ "ORDER BY S.SERIAL_PK DESC";
	// 주문번호별 상품현황_(관리자)주문관리페이지
	private static final String SELECTALL_ORDERPRODUCT = "SELECT\r\n"
			+ "	S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_DELIVERYADDRESS,\r\n"
			+ "	M.MEMBER_ID, M.MEMBER_NAME, B.BUYPRODUCT_PK, B.BUYPRODUCT_STATUS,\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_STATUS,\r\n"
			+ "	P.PRODUCT_PRICE, B.BUYPRODUCT_CNT,\r\n"
			+ "	P.PRODUCT_PRICE * B.BUYPRODUCT_CNT AS TOTALPRICE\r\n"
			+ "FROM SERIAL S\r\n"
			+ "INNER JOIN BUYPRODUCT B ON\r\n"
			+ "	S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "LEFT JOIN PRODUCT P ON\r\n"
			+ "	B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "LEFT JOIN MEMBER M ON\r\n"
			+ "	S.MEMBER_ID = M.MEMBER_ID\r\n"
			+ "WHERE S.SERIAL_PK = ?\r\n"
			+ "ORDER BY S.SERIAL_PK DESC";
	// 주문검색_(관리자)주문관리페이지
	private static final String SELECTALL_ORDERSEARCH = "SELECT\r\n"
			+ "	S.SERIAL_PK, S.SERIAL_REGDATE, S.SERIAL_DELIVERYADDRESS,\r\n"
			+ "	M.MEMBER_ID, M.MEMBER_NAME, B.BUYPRODUCT_PK, B.BUYPRODUCT_STATUS,\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_STATUS,\r\n"
			+ "	P.PRODUCT_PRICE, B.BUYPRODUCT_CNT,\r\n"
			+ "	P.PRODUCT_PRICE * B.BUYPRODUCT_CNT AS TOTALPRICE\r\n"
			+ "FROM SERIAL S\r\n"
			+ "INNER JOIN BUYPRODUCT B ON\r\n"
			+ "	S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "LEFT JOIN PRODUCT P ON\r\n"
			+ "	B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "LEFT JOIN MEMBER M ON\r\n"
			+ "	S.MEMBER_ID = M.MEMBER_ID\r\n"
			+ "WHERE\r\n"
			+ "	B.BUYPRODUCT_STATUS = ?\r\n"
			+ "	AND LOWER(S.MEMBER_ID) LIKE '%'||?||'%'\r\n"
			+ "	AND M.MEMBER_NAME LIKE '%'||?||'%'\r\n"
			+ "ORDER BY B.BUYPRODUCT_PK DESC";
//	private static final String SELECTONE = "";
	// 주문추가_(관리자)주문관리페이지
	private static final String INSERT = "INSERT INTO SERIAL (MEMBER_ID, SERIAL_DELIVERYADDRESS) VALUES (?, ?)";
//	private static final String UPDATE = "";
//	private static final String DELETE = "";

	public List<SerialDTO> selectAll(SerialDTO serialDTO) {
		Object args1[] = { serialDTO.getSerialPK() };
		Object args2[] = { serialDTO.getBuyProductStatus(), serialDTO.getMemberID(), serialDTO.getMemberName() };
		try {
			if (serialDTO.getSearchCondition().equals("orderList")) {
				return jdbcTemplate.query(SELECTALL_ORDERLIST, new SerialListAdminRowMapper());
			} else if (serialDTO.getSearchCondition().equals("orderProduct")) {
				return jdbcTemplate.query(SELECTALL_ORDERPRODUCT, args1, new SerialListAdminRowMapper());
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

//	public boolean update(SerialDTO serialDTO) {
//		return false;
//	}

//	private boolean delete(SerialDTO serialDTO) {
//		return false;
//	}

}

// selectAll_Admin_SerialListPage
class SerialListAdminRowMapper implements RowMapper<SerialDTO>{
	@Override
	public SerialDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SerialDTO data = new SerialDTO();
		data.setSerialPK(rs.getInt("SERIAL_PK"));
		data.setMemberID(rs.getString("MEMBER_ID"));
		data.setSerialRegdate(rs.getDate("SERIAL_REGDATE"));
		data.setSerialDeliveryAddress(rs.getString("SERIAL_DELIVERYADDRESS"));
		
		data.setMemberName(rs.getString("MEMBER_NAME"));
		data.setBuyProductPK(rs.getInt("BUYPRODUCT_PK"));
		data.setBuyProductStatus(rs.getString("BUYPRODUCT_STATUS"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductStatus(rs.getInt("PRODUCT_STATUS"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setBuyProductCnt(rs.getInt("BUYPRODUCT_CNT"));
		data.setTotalPrice(rs.getInt("TOTALPRICE"));
		
		return data;
	}
}