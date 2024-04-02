package com.webWeavers.weaveGlow.biz.address;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IAddressDAO {
	
	public List<AddressDTO> selectAll(Map<String,String> map);
	public AddressDTO selectOne(Map<String,Integer> map);
	public int insert(Map<String,Object>map);
	public int update(Map<String,Object>map);
	public int delete(Map<String,Integer> map);
}
