package com.webWeavers.weaveGlow.biz.serial;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISerialDAO {
	
	// 전체 주문현황_(관리자)주문관리페이지
	public List<SerialDTO> selectAllOrderList();
	
	// 주문번호별 상품현황_(관리자)주문관리페이지
	public List<SerialDTO> selectAllOrderProduct(Map<String, Object> map);
	
	// 주문검색_(관리자)주문관리페이지	
	public List<SerialDTO> selectAllOrderSearch(Map<String, Object> map);

	// 주문추가_(관리자)주문관리페이지	
	// 정석
	public int insert(String memberID, String serialDeliveryAddress);
	
	// 주문상태변경_(관리자)주문관리페이지	
	// 파라미터는 SQL문이 변경될때마다 파라미터도 달질 수 있음 > 결합도를 낮추는 Map을 인자로 주는 경우
	public int update(Map<String, Object> map);	
	
}
