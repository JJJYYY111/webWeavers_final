package com.webWeavers.weaveGlow.biz.address;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAddressDAO {
	
	public List<AddressDTO> selectAll(String Member_ID);
	public AddressDTO selectOne(int Address_PK);
	
}
