package com.webWeavers.weaveGlow.biz.serial;

import java.util.Date;

import lombok.Data;

@Data
public class SerialDTO {

	private int serialPK;
	private String memberID;
	private Date serialRegdate;
	private String serialDeliveryAddress;

}
