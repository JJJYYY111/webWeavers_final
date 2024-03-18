package com.webWeavers.weaveGlow.biz.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subcategoryService")
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryDAO subcategoryDAO;

	@Override
	public List<SubCategoryDTO> selectAll(SubCategoryDTO subcategoryDTO) {
		return subcategoryDAO.selectAll(subcategoryDTO);
	}

	@Override
	public SubCategoryDTO selectOne(SubCategoryDTO subcategoryDTO) {
		return subcategoryDAO.selectOne(subcategoryDTO);
	}

	@Override
	public boolean insert(SubCategoryDTO subcategoryDTO) {
		return subcategoryDAO.insert(subcategoryDTO);
	}

	@Override
	public boolean update(SubCategoryDTO subcategoryDTO) {
		return subcategoryDAO.update(subcategoryDTO);
	}

	@Override
	public boolean delete(SubCategoryDTO subcategoryDTO) {
		return subcategoryDAO.delete(subcategoryDTO);
	}

}
