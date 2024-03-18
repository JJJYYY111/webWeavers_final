package com.webWeavers.weaveGlow.biz.address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("addressDAO")
public class AddressDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECTALL = "SELECT ADDRESS_PK, ADDRESS_ZONECODE, ADDRESS_JIBUN, ADDRESS_ROAD, ADDRESS_DETAIL, ADDRESS_NAME FROM ADDRESS WHERE MEMBER_ID = ?";
	private static final String SELECTONE = "SELECT ADDRESS_ZONECODE, ADDRESS_JIBUN, ADDRESS_ROAD, ADDRESS_DETAIL, ADDRESS_NAME FROM ADDRESS WHERE ADDRESS_PK = ?";
	
	private static final String INSERT = "INSERT INTO ADDRESS (ADDRESS_PK, MEMBER_ID, ADDRESS_ZONECODE, ADDRESS_JIBUN, ADDRESS_ROAD, ADDRESS_DETAIL, ADDRESS_NAME) VALUES (SELECT IFNULL(MAX(APK), 0) + 1 FROM ADDRESS), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE ADDRESS SET ADDRESS_ZONECODE=?, ADDRESS_JIBUN=?, ADDRESS_ROAD=?, ADDRESS_DETAIL=?, ADDRESS_NAME=? WHERE ADDRESS_PK=?";
	private static final String DELETE = "DELETE FROM ADDRESS WHERE ADDRESS_PK=?";
	
	public List<AddressDTO> selectAll(AddressDTO addressDTO) {
		return (List<AddressDTO>)jdbcTemplate.query(SELECTALL, new AddressRowMapper1());
	}

	public AddressDTO selectOne(AddressDTO addressDTO) {
		Object[] args = { addressDTO.getAddressPK() };
		try {
			return jdbcTemplate.queryForObject(SELECTONE, args, new AddressRowMapper2());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insert(AddressDTO addressDTO) {
		int result = jdbcTemplate.update(INSERT,addressDTO.getMemberID(),addressDTO.getAddressZonecode(),addressDTO.getAddressJibun(),addressDTO.getAddressRoad(),addressDTO.getAddressDetail(),addressDTO.getAddressName());
		if(result<=0) {
			return false;
		}
		return true;
	}

	public boolean update(AddressDTO addressDTO) {
		int result = jdbcTemplate.update(UPDATE,addressDTO.getAddressPK(),addressDTO.getAddressZonecode(),addressDTO.getAddressJibun(),addressDTO.getAddressRoad(),addressDTO.getAddressDetail(),addressDTO.getAddressName());
		if(result<=0) {
			return false;
		}
		return true;
	}

	public boolean delete(AddressDTO addressDTO) {
		int result = jdbcTemplate.update(DELETE,addressDTO.getAddressPK());
		if(result<=0) {
			return false;
		}
		return true;
	}

}

class AddressRowMapper1 implements RowMapper<AddressDTO> {
	@Override
	public AddressDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AddressDTO data = new AddressDTO();
		data.setAddressPK(rs.getInt("ADDRESSPK"));
		data.setAddressZonecode(rs.getString("ADDRESS_ZONECODE"));
		data.setAddressJibun(rs.getString("ADDRESS_JIBUN"));
		data.setAddressRoad(rs.getString("ADDRESS_ROAD"));
		data.setAddressDetail(rs.getString("ADDRESS_DETAIL"));
		data.setAddressName(rs.getString("ADDRESS_NAME"));
		data.setMemberID(rs.getString("MEMBER_ID"));
		return data;
	}
}

class AddressRowMapper2 implements RowMapper<AddressDTO> {
	@Override
	public AddressDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AddressDTO data = new AddressDTO();
		data.setAddressPK(rs.getInt("ADDRESS_PK"));
		return data;
	}
}
