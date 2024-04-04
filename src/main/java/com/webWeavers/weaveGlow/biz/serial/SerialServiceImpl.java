package com.webWeavers.weaveGlow.biz.serial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serialService")
public class SerialServiceImpl implements SerialService {

	@Autowired
	private ISerialDAO serialDAO;

	@Override
	public List<SerialDTO> selectAll(SerialDTO serialDTO) {
		if (serialDTO.getSearchCondition().equals("orderList")) {
			return serialDAO.selectAllOrderList();
		}
		else if (serialDTO.getSearchCondition().equals("orderProduct")) {
			Map<String, Object> map = new HashMap<String, Object>();	
			map.put("serialPK", serialDTO.getSerialPK());
			return serialDAO.selectAllOrderProduct(map);
		}
		else if (serialDTO.getSearchCondition().equals("orderSearch")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("serialStatus", serialDTO.getSerialStatus());
			map.put("memberName", serialDTO.getMemberName());
			map.put("serialRegdate", serialDTO.getSerialRegdate());
			return serialDAO.selectAllOrderSearch(map);
		}
		return null;
	}

//	@Override
//	public SerialDTO selectOne(SerialDTO serialDTO) {
//		return serialDAO.selectOne(serialDTO);
//	}

	// 정석
	@Override
	public boolean insert(SerialDTO serialDTO) {
		int result = serialDAO.insert(serialDTO.getMemberID(), serialDTO.getSerialDeliveryAddress());
		if (result <= 0) {
			return false;
		}
		return true;
	}

	// 파라미터는 SQL문이 변경될때마다 파라미터도 달질 수 있음 > 결합도를 낮추는 Map을 인자로 주는 경우
	@Override
	public boolean update(SerialDTO serialDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serialStatus", serialDTO.getSerialStatus());
		map.put("serialPK", serialDTO.getSerialPK());
		if (serialDAO.update(map) <= 0) {
			return false;
		}
		return true;
	}

//	@Override
//	public boolean delete(SerialDTO serialDTO) {
//		return false;
//	}

}
