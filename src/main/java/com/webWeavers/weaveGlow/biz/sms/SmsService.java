package com.webWeavers.weaveGlow.biz.sms;

import org.springframework.stereotype.Service;

import com.webWeavers.weaveGlow.biz.member.MemberDTO;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service("smsService")
public class SmsService {
	public int sendMessage(MemberDTO memberDTO) {		
		System.out.println("[로그] 문자인증 서비스");
		
		// initialize("API 키 입력", "API 시크릿 키 입력", "주소")
		DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize("NCSBXXQF0TAAOOLN", "I58EYW5XFVYACID2PCAKVCCNCOCHHMSD", "https://api.coolsms.co.kr");
		
		Message message = new Message();

		message.setFrom("01062360548");
		message.setTo(memberDTO.getMemberPhone());
		
		int randNum = -1;
		String randPW;
		if(memberDTO.getSearchCondition().equals("certification")) {
			randNum = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;	// 인증번호 5자리, 랜덤 생성
			message.setText("[WeaveGlow 인증번호] " + randNum);
		}
		else if(memberDTO.getSearchCondition().equals("findID")) {
			message.setText("[WeaveGlow ID]" + memberDTO.getMemberID());
		}
		else if(memberDTO.getSearchCondition().equals("findPW")) {
			randPW = "";
			message.setText("[WeaveGlow 임시 비밀번호]" + randPW);
		}

		try {
		  // send 메서드로 문자 전송
		  messageService.send(message);
		  // 최소한의 보안을 위해 인증번호 * 7777
		  if(memberDTO.getSearchCondition().equals("certification")) {
			  return randNum * 7777;
		  }else {
			  return 1;
		  }
		  
		} catch (NurigoMessageNotReceivedException exception) {
		  // 발송에 실패한 메시지 목록을 확인
		  System.out.println(exception.getFailedMessageList());
		  System.out.println(exception.getMessage());
		  return -1;
		} catch (Exception exception) {
		  System.out.println(exception.getMessage());
		  return -1;
		}
	}
}
