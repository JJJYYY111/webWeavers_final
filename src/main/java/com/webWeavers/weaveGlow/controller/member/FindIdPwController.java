package com.webWeavers.weaveGlow.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.mail.MailService;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.sms.SmsService;

@Controller
public class FindIdPwController {

	@Autowired
	MemberService memberService;
	@Autowired
	SmsService smsService;
	@Autowired
	MailService mailService;

	// ID찾기페이지로 이동하는 메서드
	@GetMapping("/findID")
	public String findID() {
		return "user/findId";
	}

	// 비밀번호찾기페이지로 이동하는 메서드
	@GetMapping("/findPW")
	public String findPW() {
		return "user/findPw";
	}

	@PostMapping("/async/smsSendID")
	public @ResponseBody int smsSendID(MemberDTO memberDTO) {

		// selectOne 검색조건 저장
		memberDTO.setSearchCondition("idFindSms");
		memberDTO = memberService.selectOne(memberDTO);

		// 일치하는 회원이 없으면 실패
		if (memberDTO == null) {
			return -1;
		}

		// sendMessage 검색조건 저장
		memberDTO.setSearchCondition("sendID");
		// 서비스 이용 후 return (성공:1, 실패:-1)
		return smsService.sendMessage(memberDTO);

	}

	@PostMapping("/async/smsSendPW")
	public @ResponseBody int smsSendPW(MemberDTO memberDTO) {

		// selectOne 검색조건 저장
		memberDTO.setSearchCondition("pwFindSms");
		memberDTO = memberService.selectOne(memberDTO);

		// 일치하는 회원이 없으면 실패
		if (memberDTO == null) {
			return -1;
		}

		// sendMessage 검색조건 저장
		memberDTO.setSearchCondition("sendPW");
		// 서비스 이용 후 return (성공:1, 실패:-1)
		return smsService.sendMessage(memberDTO);
	}

	@PostMapping("/async/emailSendID")
	public @ResponseBody int emailSendID(MemberDTO memberDTO) {
		// selectOne 검색조건 저장
		memberDTO.setSearchCondition("idFindEmail");
		memberDTO = memberService.selectOne(memberDTO);

		// 일치하는 회원이 없으면 실패
		if (memberDTO == null) {
			return -1;
		}

		// sendMessage
		memberDTO.setSearchCondition("sendID");
		// 서비스 이용 후 return (성공:1, 실패:-1)
		return mailService.sendIdPwEmail(memberDTO);
	}

	@PostMapping("/async/emailSendPW")
	public @ResponseBody int emailSendPW(MemberDTO memberDTO) {
		// selectOne 검색조건 저장
		memberDTO.setSearchCondition("pwFindEmail");
		memberDTO = memberService.selectOne(memberDTO);

		// 일치하는 회원이 없으면 실패
		if (memberDTO == null) {
			return -1;
		}

		// sendMessage
		memberDTO.setSearchCondition("sendPW");
		// 서비스 이용 후 return (성공:1, 실패:-1)
		return mailService.sendIdPwEmail(memberDTO);
	}

}
