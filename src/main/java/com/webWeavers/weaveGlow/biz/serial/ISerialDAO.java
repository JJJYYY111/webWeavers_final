package com.webWeavers.weaveGlow.biz.serial;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISerialDAO {

	public List<SerialDTO> selectAll();
	
	// 정석
	public boolean insert(String memberID, String serialDeliveryAddress);
	
	// 파라미터는 SQL문이 변경될때마다 파라미터도 달질 수 있음 > 결합도를 낮추는 Map을 인자로 주는 경우
	public boolean update(Map<String, Object> map);	
	
}
