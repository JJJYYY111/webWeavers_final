package com.webWeavers.weaveGlow.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.sms.SmsService;

@Controller
public class FindIdPwController {

	@Autowired
	MemberService memberService;
	@Autowired
	SmsService smsService;
	
	@RequestMapping("/async/smsSendID")
	public @ResponseBody int smsSendID(MemberDTO memberDTO) {	
		System.out.println("[로그] ID발송진입");
		System.out.println("처음받은 memberDTO"+memberDTO);
		memberDTO.setSearchCondition("idForgot");
		System.out.println("서치컨디션저장한 memberDTO"+memberDTO);
		memberDTO = memberService.selectOne(memberDTO);
		System.out.println("selectOne한"+memberDTO);
		if(memberDTO == null) {
			System.out.println("들어오면안된다고");
			return -1;
		}
		// memberDTO 검색조건 저장
		memberDTO.setSearchCondition("sendID");
		// 서비스 이용 후 return (성공:1, 실패:-1)
		return smsService.sendMessage(memberDTO);
	}
	
	@RequestMapping("/async/successSmsSendID")
	public String login() {
		return "user/login";
	}
	
}
