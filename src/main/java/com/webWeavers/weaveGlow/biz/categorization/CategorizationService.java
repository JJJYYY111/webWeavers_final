package com.webWeavers.weaveGlow.biz.categorization;

import java.util.List;

public interface CategorizationService {
	List<CategorizationDTO> selectAll(CategorizationDTO categorizationDTO);
	CategorizationDTO selectOne(CategorizationDTO categorizationDTO);
	
	boolean insert(CategorizationDTO categorizationDTO);
	boolean update(CategorizationDTO categorizationDTO);
	boolean delete(CategorizationDTO categorizationDTO);
}
