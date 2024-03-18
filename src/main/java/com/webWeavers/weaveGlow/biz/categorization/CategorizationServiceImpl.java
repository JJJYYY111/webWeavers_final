package com.webWeavers.weaveGlow.biz.categorization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categorizationService")
public class CategorizationServiceImpl implements CategorizationService {

	@Autowired
	private CategorizationDAO categorizationDAO;

	@Override
	public List<CategorizationDTO> selectAll(CategorizationDTO categorizationDTO) {
		return categorizationDAO.selectAll(categorizationDTO);
	}

	@Override
	public CategorizationDTO selectOne(CategorizationDTO categorizationDTO) {
		return categorizationDAO.selectOne(categorizationDTO);
	}

	@Override
	public boolean insert(CategorizationDTO categorizationDTO) {
		return categorizationDAO.insert(categorizationDTO);
	}

	@Override
	public boolean update(CategorizationDTO categorizationDTO) {
		return categorizationDAO.update(categorizationDTO);
	}

	@Override
	public boolean delete(CategorizationDTO categorizationDTO) {
		return categorizationDAO.delete(categorizationDTO);
	}

}
