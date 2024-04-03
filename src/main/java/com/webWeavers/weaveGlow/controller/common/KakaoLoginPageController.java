package com.webWeavers.weaveGlow.controller.common;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class KakaoLoginPageController {

	
	@Autowired
	KakaoService kakaoService;
	
	@Autowired
	MemberService memberService;
	
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

	    System.out.println("생년" + birthyear);
	    System.out.println("월일" + birthday);

	    boolean hasEmail = false;

	    String email = "";

	    
	    Date birth = null;

	    try {
	        String birthDateString = birthyear + "-" + birthday.substring(0, 2) + "-" + birthday.substring(2, 4);

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date utilDate = sdf.parse(birthDateString);

	        birth = new Date(utilDate.getTime());

	        System.out.println("생일 (java.sql.Date 타입): " + birth);


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
	        System.out.println("생일 :" + birth); 
	        //닉네임으로 유저 판단
	        //안되어있다
	        //회원가입
	        memberDTO.setSearchCondition("memberNickNameCheck");
	        memberDTO.setMemberNickname(nickname);
	        memberDTO = memberService.selectOne(memberDTO);

	        String randomID = RandomStringUtils.random(7, true, true);

	        if(memberDTO == null) { //회원이 아닐때
	            memberDTO = new MemberDTO();
	            memberDTO.setMemberEmail(email);
	            memberDTO.setMemberNickname(nickname);
	            memberDTO.setMemberName(name);
	            memberDTO.setMemberPhone(phoneNumber);
	            memberDTO.setMemberBirth(birth);
	            
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
	            
	            return "/user/callback";
	        }
	        //아이디는 랜덤으로 저장
	        //되어있다 -> 세션에 랜덤 아이디
	        httpSession.setAttribute("sessionMid", memberDTO.getMemberID());
	        //세션- 로그인아이디
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }


	    return "redirect:/main";
	}
	
	@PostMapping("/joinForm")
	public String joinForm(Model model, MemberDTO memberDTO) {
		
		 model.addAttribute("memberDTO", memberDTO);
         
         model.addAttribute("snsLogin", "sns"); //회원가입 갔을 때 sns로 로그인 했는지 판단 유무
		
		return "/user/register";
	}

	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("access_Token");
	    session.removeAttribute("userId");
	    return "index";
	}

	  

}