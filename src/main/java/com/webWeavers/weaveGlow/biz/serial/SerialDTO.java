package com.webWeavers.weaveGlow.biz.serial;

import java.util.Date;

public class SerialDTO {

	private int serialPK;
	private String memberID;
	private Date serialRegdate;
	private String serialDeliveryAddress;

	public int getSerialPK() {
		return serialPK;
	}

	public void setSerialPK(int serialPK) {
		this.serialPK = serialPK;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public Date getSerialRegdate() {
		return serialRegdate;
	}

	public void setSerialRegdate(Date serialRegdate) {
		this.serialRegdate = serialRegdate;
	}

	public String getSerialDeliveryAddress() {
		return serialDeliveryAddress;
	}

	public void setSerialDeliveryAddress(String serialDeliveryAddress) {
		this.serialDeliveryAddress = serialDeliveryAddress;
	}

	@Override
	public String toString() {
		return "SerialDTO [serialPK=" + serialPK + ", memberID=" + memberID + ", serialRegdate=" + serialRegdate
				+ ", serialDeliveryAddress=" + serialDeliveryAddress + "]";
	}

}
