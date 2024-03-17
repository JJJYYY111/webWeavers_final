package com.webWeavers.weaveGlow.biz.serial;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class SerialDTO {

	private int serialPK;
	private String memberID;
	private String serialStatus;
	private Date serialRegdate;
	private String serialDeliveryAddress;
	
	// 데이터 전달용 멤버변수
	private Map<String, Object> option;

}
