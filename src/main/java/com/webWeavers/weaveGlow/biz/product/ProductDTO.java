package com.webWeavers.weaveGlow.biz.product;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class ProductDTO {

	private int productPK;
	private String productName;
	private int productPrice;
	private Date productRegdate;
	private String productDetailImg;
	private String productImg;
	private int productStatus;
	private int productQuantity;

	private String searchCondition;
	private String memberID;
	private int wish;
	
	private Map<String, Object> option;	// HashMap?TreeMap? 앞단에서주는(페이지번호, 필터, 기타 옵션들), 앞단에보내는(카테고리, 서브카테고리)...받고싶어요

}
