package com.webWeavers.weaveGlow.biz.product;

import java.util.Date;

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

}
