package com.webWeavers.weaveGlow.controller.common;

import java.awt.PageAttributes.MediaType;
import java.net.http.HttpHeaders;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class KakaoLoginPageController {

	
	@Autowired
	KakaoService kakaoService;
	
	@Autowired
	MemberService memberService;
    /**
     * 카카오 callback
     * [GET] /user/callback
     */
    
	
	@GetMapping("/callback")
	public String kakaoLogin(@RequestParam(name = "code") String code, MemberDTO memberDTO, Model model, HttpSession httpSession) {
		  System.out.println("코드"+code);
		    String access = kakaoService.getKakaoAccessToken(code);
		    System.out.println("리턴:"+ access);
		    String info = kakaoService.createKakaoUser(access);
		    System.out.println("로그: "+ info);
		
		    JsonParser parser = new JsonParser();
		       JsonElement element = parser.parse(info);
		       
		    int id = element.getAsJsonObject().get("id").getAsInt();
		       JsonElement kakaoAccount = element.getAsJsonObject().get("kakao_account");
		       
		       JsonElement profile = kakaoAccount.getAsJsonObject().get("profile");
		       
		       String nickname = profile.getAsJsonObject().get("nickname").getAsString();
		     
		       String name = kakaoAccount.getAsJsonObject().get("name").getAsString();
		       
		      String phoneNumber = kakaoAccount.getAsJsonObject().get("phone_number").getAsString();
		       
		      String birthyear = kakaoAccount.getAsJsonObject().get("birthyear").getAsString();
		      
		      String birthday = kakaoAccount.getAsJsonObject().get("birthday").getAsString();

		      
		       boolean hasEmail = false;
		       
		       String email = "";
		       
		       // 생년월일 데이터를 합쳐서 DATE 형식으로 변환
		       String memberBirth = null;
		       
		       if (!birthyear.isEmpty() && !birthday.isEmpty()) {
		    	   memberBirth = birthyear + "-" + birthday.substring(0, 2) + "-" + birthday.substring(2, 4);
		       }

		       
		       if (kakaoAccount != null && !kakaoAccount.isJsonNull()) {
		           JsonElement hasEmailElement = kakaoAccount.getAsJsonObject().get("has_email");
		           if (hasEmailElement != null && !hasEmailElement.isJsonNull()) {
		               hasEmail = hasEmailElement.getAsBoolean();
		           }

		           if (hasEmail) {
		               JsonElement emailElement = kakaoAccount.getAsJsonObject().get("email");
		               if (emailElement != null && !emailElement.isJsonNull()) {
		                   email = emailElement.getAsString();
		               }
		           }
		       }
		       
		       System.out.println("이름 : " + name );
		       System.out.println("닉네임: "+ nickname);
		       System.out.println("email : " + email);
		       System.out.println("전화번호 : "+ phoneNumber);
		       //이메일로 유저 판단
		       //안되어있다
		       //회원가입-> 이름과 이메일 들고 감
		       memberDTO.setSearchCondition("memberNickNameCheck");
		       memberDTO.setMemberEmail(nickname);
		       memberDTO = memberService.selectOne(memberDTO);
		       
		       String randomID = RandomStringUtils.random(7, true, true);
		       
		       if(memberDTO == null) { //회원이 아닐때
		    	    memberDTO = new MemberDTO();
		    	    memberDTO.setMemberEmail(email);
		    	    memberDTO.setMemberNickname(nickname);
		    	    memberDTO.setMemberName(name);
		    	    memberDTO.setMemberPhone(phoneNumber);
		    	  
		    	    // 아이디 랜덤 생성
		    	    
		    	    memberDTO.setMemberID(randomID);
		    	    
		    	    // 비밀번호 랜덤 생성
		    	    int passwordLength = 11; // 비밀번호의 길이 설정
		    	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*?";
		    	    String randomPassword = RandomStringUtils.random(passwordLength, characters);
		    	    
		    	    memberDTO.setMemberPassword(randomPassword);

		    	    System.out.println("아이디 랜덤 : "+ randomID);
		    	    System.out.println("비밀번호 랜덤 : "+ randomPassword);
		    	  
		    	    model.addAttribute("memberDTO", memberDTO);
		    	    model.addAttribute("snsLogin", "sns");
		    	    return "/user/register"; 
		    	}
		       //아이디는 랜덤으로 저장
		       //되어있다 -> 세션에 랜덤 아이디 
		       httpSession.setAttribute("sessionMid", memberDTO.getMemberID());
		       //세션- 로그인아이디
		return "redirect:/main";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "index";
	}

	  

}