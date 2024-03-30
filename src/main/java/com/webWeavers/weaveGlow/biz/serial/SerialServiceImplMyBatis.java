package com.webWeavers.weaveGlow.biz.serial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serialServiceMyBatis")
public class SerialServiceImplMyBatis implements ISerialService {
	
	@Autowired
	private ISerialDAO serialDAO;

	@Override
	public List<SerialDTO> selectAll(SerialDTO serialDTO) {
		return serialDAO.selectAll();
	}

//	@Override
//	public SerialDTO selectOne(SerialDTO serialDTO) {
//		return serialDAO.selectOne(serialDTO);
//	}

	@Override
	public boolean insert(SerialDTO serialDTO) {
		return serialDAO.insert(serialDTO.getMemberID(), serialDTO.getSerialDeliveryAddress());
	}

	@Override
	public boolean update(SerialDTO serialDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serialStatus", serialDTO.getSerialStatus());
		map.put("serialPK", serialDTO.getSerialPK());
		return serialDAO.update(map);
	}

//	@Override
//	public boolean delete(SerialDTO serialDTO) {
//		return false;
//	}
}
