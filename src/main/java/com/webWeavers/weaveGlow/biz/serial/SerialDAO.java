package com.webWeavers.weaveGlow.biz.serial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("serialDAO")
public class SerialDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 주문번호별 주문현황_(관리자)주문관리페이지
	private static final String SELECTALL = "SELECT\r\n"
			+ "	S.SERIAL_PK, S.MEMBER_ID, B.BUYPRODUCT_PK, B.BUYPRODUCT_STATUS, S.SERIAL_REGDATE, S.SERIAL_DELIVERYADDRESS,\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_STATUS,\r\n"
			+ "	P.PRODUCT_PRICE, B.BUYPRODUCT_CNT, P.PRODUCT_PRICE * B.BUYPRODUCT_CNT AS TOTALPRICE\r\n"
			+ "FROM SERIAL S\r\n"
			+ "INNER JOIN BUYPRODUCT B ON S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "LEFT JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK\r\n"
			+ "ORDER BY S.SERIAL_PK DESC";
//	private static final String SELECTONE = "";
	// 주문추가_(관리자)주문관리페이지
	private static final String INSERT = "INSERT INTO SERIAL (MEMBER_ID, SERIAL_DELIVERYADDRESS) VALUES (?, ?)";
//	private static final String UPDATE = "";
//	private static final String DELETE = "";

	public List<SerialDTO> selectAll(SerialDTO serialDTO) {
		try {
			return (List<SerialDTO>)jdbcTemplate.query(SELECTALL, new SerialListAdminRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
		
		Map<String, Object> option = new HashMap<>();
		option.put("buyProductPK", "BUYPRODUCT_PK");
		option.put("buyProductStatus", "BUYPRODUCT_STATUS");
		option.put("productPK", rs.getInt("PRODUCT_PK"));
		option.put("productName", rs.getString("PRODUCT_NAME"));
		option.put("productStatus", rs.getInt("PRODUCT_STATUS"));
		option.put("productPrice", rs.getInt("PRODUCT_PRICE"));
		option.put("buyProductCnt", rs.getInt("BUYPRODUCT_CNT"));
		option.put("totalprice", rs.getInt("TOTALPRICE"));
		data.setOption(option);
		
		return data;
	}
}
