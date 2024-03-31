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
		
		int randNum = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;	// 인증번호 5자리, 랜덤 생성
		message.setFrom("01062360548");
		message.setTo(memberDTO.getMemberPhone());
		message.setText("[WeaveGlow 인증번호] " + randNum);

		try {
		  // send 메서드로 문자 전송
		  messageService.send(message);
		  // 최소한의 보안을 위해 인증번호 * 7777
		  return randNum * 7777;
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
