package com.webWeavers.weaveGlow.biz.address;

import lombok.Data;

@Data
public class AddressDTO {
	private int addressPK;
	private String memberID;
	private String addressZonecode;
	private String addressJibun;
	private String addressRoad;
	private String addressDetail;
	private String addressName;
		
}
