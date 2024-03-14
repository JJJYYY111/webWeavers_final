package com.webWeavers.weaveGlow.biz.serial;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("serialDAO")
public class SerialDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	private static final String SELECTALL = "";
//	private static final String SELECTONE = "";

	private static final String INSERT = "INSERT INTO SERIAL (SERIAL_PK, MEMBER_ID, SERIAL_DELIVERYADDRESS) VALUES ((SELECT NVL(MAX(SERIAL_PK), 0) + 1 FROM SERIAL), ?, ?)";
//	private static final String UPDATE = "";
//	private static final String DELETE = "";

//	private List<SerialDTO> selectAll(SerialDTO serialDTO) {
//		return null;
//	}

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

//	private boolean update(SerialDTO serialDTO) {
//		return false;
//	}

//	private boolean delete(SerialDTO serialDTO) {
//		return false;
//	}

}
