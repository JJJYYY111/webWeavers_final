package com.webWeavers.weaveGlow.biz.serial;

import java.util.Date;

import lombok.Data;

@Data
public class SerialDTO {

	private int serialPK;
	private String memberID;
	private Date serialRegdate;
	private String serialDeliveryAddress;
	
	private String memberName;
	private int buyProductPK;
	private String buyProductStatus;
	private int productPK;
	private String productName;
	private int productStatus;
	private int productPrice;
	private int buyProductCnt;
	private int totalprice;
	
}