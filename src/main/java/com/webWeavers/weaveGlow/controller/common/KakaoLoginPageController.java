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
	    System.out.println("[C로그1] 코드 : "+code);
	    String access = kakaoService.getKakaoAccessToken(code);
	    System.out.println("[C로그2] 리턴 :"+ access);
	    String info = kakaoService.createKakaoUser(access);
	    System.out.println("[C로그3] 정보 : "+ info);

	    JsonParser parser = new JsonParser();
	    //JSON 형식의 문자열을 읽어와서 JsonElement 객체로 변환
	    JsonElement element = parser.parse(info);

	    int id = element.getAsJsonObject().get("id").getAsInt();
	    //"id"를 정수형태로 저장되는 메서드
	    
	    JsonElement kakaoAccount = element.getAsJsonObject().get("kakao_account");

	    JsonElement profile = kakaoAccount.getAsJsonObject().get("profile");
	    //kakaoAccount 안에 있는 profile의 값을 가져옴

	    String nickname = profile.getAsJsonObject().get("nickname").getAsString();
	    //profile 안에 있는 닉네임 값을 문자열 형태로 저장 

	    String name = kakaoAccount.getAsJsonObject().get("name").getAsString();
	    //profile 안에 있는 이름 값을 문자열 형태로 저장

	    String phoneNumber = kakaoAccount.getAsJsonObject().get("phone_number").getAsString();
	    //profile 안에 있는 휴대전화번호 값을 문자열 형태로 저장
	    
	   
	    String birthyear = kakaoAccount.getAsJsonObject().get("birthyear").getAsString();
	    //profile 안에 있는 년도 값을 문자열 형태로 저장
	    
	    String birthday = kakaoAccount.getAsJsonObject().get("birthday").getAsString();
	    //profile 안에 있는 월일 값을 문자열 형태로 저장
	    
	    System.out.println("[C로그4] 생년 : " + birthyear);
	    
	    System.out.println("[C로그5] 월일 : " + birthday);

	  
	    boolean hasEmail = false;
	    //이메일이 있는지 여부
	    
	    String email = "";
	    //이메일 값을 저장하는 변수
	    
	    Date birth = null;
	    //birth 변수 초기화

	    try {
	        String birthDateString = birthyear + "-" + birthday.substring(0, 2) + "-" + birthday.substring(2, 4);
	        //생년월일에서 월 추출, 일 추출, -하이픈으로 연결 
	        //즉, "yyyy-MM-dd" 형식
	        
	        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	        //yyyy-MM-dd 형식으로 변환
	        java.util.Date utilDate = date.parse(birthDateString);
	        //java.util.Date 객체로 변환하여 반환

	        birth = new Date(utilDate.getTime());
	        System.out.println("[C로그6] 생년월일 : " + utilDate.getTime());


	        if (kakaoAccount != null && !kakaoAccount.isJsonNull()) {
	        	//kakaoAccount 가 null이 아니고 JSON 값이 null이 아닐 때 이메일 정보 확인
	            JsonElement hasEmailElement = kakaoAccount.getAsJsonObject().get("has_email");
	            
	            if (hasEmailElement != null && !hasEmailElement.isJsonNull()) {
	            	//hasEmailElement 가 null이 아니고 JSON 값이 null이 아닐 때 이메일 정보 확인
	                hasEmail = hasEmailElement.getAsBoolean();
	                //JSON에서 boolean 값을 Java의 boolean 타입으로 변환
	            }

	            if (hasEmail) {
	            	//사용자가 이메일 정보를 가지고 있다면
	                JsonElement emailElement = kakaoAccount.getAsJsonObject().get("email");
	                //email 가져와서 저장
	                if (emailElement != null && !emailElement.isJsonNull()) {
	                	//emailElement 가 null이 아니고 JSON 값이 null이 아닐 때 이메일 정보 확인
	                    email = emailElement.getAsString();
	                    //emailElement의 값을 문자열로 변환
	                }
	            }
	        }
	        
	        ///DB에 저장되는 전화번호 값이 010********로 저장되기때문에 기호들 제거
		    phoneNumber = phoneNumber.replaceAll("[\\+\\-]", "");
		    //'+'와 '-' 기호 제거
		    
		    if (phoneNumber.startsWith("82")) {
		    	//'82' 제거
		        phoneNumber = phoneNumber.substring(2);
		    }

		    phoneNumber = phoneNumber.replaceAll("\\s", "");
		    //공백 제거
		    
		    if (!phoneNumber.startsWith("0")) { 
		    	//앞에 0 추가
		        phoneNumber = "0" + phoneNumber;
		    }
		    

	        System.out.println("[C로그7] 이름 : " + name );
	        System.out.println("[C로그8] 닉네임 : "+ nickname);
	        System.out.println("[C로그9] email : " + email);
	        System.out.println("[C로그10] 전화번호 : "+ phoneNumber);
	        System.out.println("[C로그11] 생년월일 :" + birth); 
	        
	        memberDTO.setSearchCondition("memberNickNameCheck");
	        memberDTO.setMemberNickname(nickname);
	        memberDTO = memberService.selectOne(memberDTO);
	        //닉네임으로 유저 판단 후 사용자가 없다면 회원가입페이지로 이동
	        
	        //사용자 간편을 위해 아이디와 비밀번호를 랜덤으로 생성하여 DB에 저장
	        String randomID = RandomStringUtils.random(7, true, true);
	        //아이디 랜덤 생성
	        
	        //비밀번호 랜덤 생성
            int passwordLength = (int) (Math.random() * 5) + 8; // 비밀번호의 길이 설정 (8~12개의 랜덤)
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*?";
            //비밀번호 조합
            String randomPassword = RandomStringUtils.random(passwordLength, characters);
            //랜덤한 문자열 생성
            

            System.out.println("[C로그12] 아이디 랜덤 : "+ randomID);
            System.out.println("[C로그13] 비밀번호 랜덤 : "+ randomPassword);

	        if(memberDTO == null) { //회원이 아닐때
	            memberDTO = new MemberDTO();
	            memberDTO.setMemberID(randomID);
	            memberDTO.setMemberPassword(randomPassword);
	            memberDTO.setMemberName(name);
	            memberDTO.setMemberNickname(nickname);
	            memberDTO.setMemberBirth(birth);
	            memberDTO.setMemberPhone(phoneNumber);
	            memberDTO.setMemberEmail(email);
	          
	            model.addAttribute("memberDTO", memberDTO);
	            
	            return "/user/callback";
	        }
	       
	        //아이디는 랜덤으로 저장
	        //회원이 있다면 -> 세션에 랜덤 아이디 저장 후 메인으로 이동
	        httpSession.setAttribute("sessionMid", memberDTO.getMemberID());
	        
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    return "redirect:/main";
	}
	
	@PostMapping("/joinForm")
	public String joinForm(Model model, MemberDTO memberDTO) {
		
		 model.addAttribute("memberDTO", memberDTO);
         
         model.addAttribute("snsLogin", "sns");
       //회원가입 갔을 때 sns로 로그인 했는지 판단 유무
		
		return "/user/register";
	}
	
}