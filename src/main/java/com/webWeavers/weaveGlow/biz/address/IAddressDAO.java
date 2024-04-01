package com.webWeavers.weaveGlow.biz.address;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAddressDAO {
	
	public List<AddressDTO> selectAll(String Member_ID);
	public AddressDTO selectOne(Map<String,Integer> map);
	public boolean insert(AddressDTO addressDTO);
	public boolean update(AddressDTO addressDTO);
	public boolean delete(Map<String,Integer> map);
}
