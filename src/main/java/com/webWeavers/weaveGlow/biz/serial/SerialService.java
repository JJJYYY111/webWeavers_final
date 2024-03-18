package com.webWeavers.weaveGlow.biz.serial;

import java.util.List;

public interface SerialService {
	List<SerialDTO> selectAll(SerialDTO serialDTO);
//	SerialDTO selectOne(SerialDTO serialDTO);
	
	boolean insert(SerialDTO serialDTO);
//	boolean update(SerialDTO serialDTO);
//	boolean delete(SerialDTO serialDTO);
}
