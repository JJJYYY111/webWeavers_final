package com.webWeavers.weaveGlow.biz.serial;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SerialServiceImpl")
public class SerialServiceImpl implements SerialService {

	@Autowired
	private SerialDAO serialDAO;

//	@Override
//	public List<SerialDTO> selectAll(SerialDTO serialDTO) {
//		return serialDAO.selectAll(serialDTO);
//	}

//	@Override
//	public SerialDTO selectOne(SerialDTO serialDTO) {
//		return serialDAO.selectOne(serialDTO);
//	}

	@Override
	public boolean insert(SerialDTO serialDTO) {
		return serialDAO.insert(serialDTO);
	}

//	@Override
//	public boolean update(SerialDTO serialDTO) {
//		return serialDAO.update(serialDTO);
//	}

//	@Override
//	public boolean delete(SerialDTO serialDTO) {
//		return serialDAO.delete(serialDTO);
//	}

}
