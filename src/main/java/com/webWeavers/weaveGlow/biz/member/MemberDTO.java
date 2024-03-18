package com.webWeavers.weaveGlow.biz.member;

import java.util.Date;

import lombok.Data;

@Data
public class MemberDTO {
	private String memberID;
	private String memberPassword;
	private String memberName;
	private Date memberBirth;
	private String memberPhone;
	private String memberNickname;
	private String memberEmail;
	private int memberMarketing;
	private int memberRegday;
	private int gradePK;
	private String gradeName;
	private String searchCondition;

}
