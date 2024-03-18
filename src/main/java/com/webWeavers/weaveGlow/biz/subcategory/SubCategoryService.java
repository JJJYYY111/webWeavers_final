package com.webWeavers.weaveGlow.biz.subcategory;

import java.util.List;

public interface SubCategoryService {
	List<SubCategoryDTO> selectAll(SubCategoryDTO subcategoryDTO);
	SubCategoryDTO selectOne(SubCategoryDTO subcategoryDTO);
	
	boolean insert(SubCategoryDTO subcategoryDTO);
	boolean update(SubCategoryDTO subcategoryDTO);
	boolean delete(SubCategoryDTO subcategoryDTO);
}
