package com.webWeavers.weaveGlow.biz.categorization;

import lombok.Data;

@Data
public class CategorizationDTO {
	private int categorizationPK;
	private int productPK;
	private int subCategoryPK;
	private String categoryName;
}
