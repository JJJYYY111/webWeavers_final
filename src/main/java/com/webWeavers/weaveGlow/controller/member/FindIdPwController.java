package com.webWeavers.weaveGlow.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.sms.SmsService;

@Controller
public class FindIdPwController {

	@Autowired
	MemberService memberService;
	@Autowired
	SmsService smsService;
	
	@RequestMapping("/sendID")
	public String findID(MemberDTO memberDTO) {
		memberDTO.setSearchCondition("findID");
		if(memberService.selectOne(memberDTO) == null) {
			return "-1";
		}
		smsService.sendMessage(memberDTO);
		return "1";
	}
	
}
