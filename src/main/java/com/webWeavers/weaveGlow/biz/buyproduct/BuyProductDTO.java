package com.webWeavers.weaveGlow.biz.buyproduct;

import java.sql.Date;

import lombok.Data;

@Data
public class BuyProductDTO {
	private int buyProductPK;
	private int productPK;
	private int serialPK;
	private Date serialRegdate;
	private String productName;
	private int productPrice;
	private String productImg;
	private String memberID;
	private String serialDeliveryAddress;
	
	private int cartCnt;
	private int reviewCheck;
	private String searchCondition;
	
}
