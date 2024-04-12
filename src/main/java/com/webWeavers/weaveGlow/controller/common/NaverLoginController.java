package com.webWeavers.weaveGlow.controller.common;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

@Controller
@RequestMapping("/naver")
public class NaverLoginController {

	// naverService, memberService 의존주입
	@Autowired
	NaverService naverService;
	
	@Autowired
	MemberService memberService;
	
	// 네이버 OAuth 콜백 처리
	@GetMapping("/callbackNaver")
	public String naverLoginCallback(@RequestParam(name = "code") String code, MemberDTO memberDTO, Model model, HttpSession httpSession) throws ParseException {
	    
		// 네이버에서 전달 받은 코드 출력
		System.out.println("[Controller로그1] 코드 : "+code);
		
		// 네이버 API를 통해 액세스 토큰 받아오기
	    String access = naverService.getNaverAccessToken(code);
	    
	    // 액세스 토큰을 이용하여 네이버 사용자 정보 받아오기
	    String info = naverService.getNaverUserInfo(access);
	    System.out.println("[Controller로그2] 정보 : "+ info);

	    JsonParser parser = new JsonParser();
	    //JSON 형식의 문자열을 JsonElement 객체로 변환
	    JsonElement element = parser.parse(info);

	    // Naver API에서 사용자 정보를 추출하는 부분
	    JsonElement naverUserInfo = element.getAsJsonObject().get("response");
	    
	    // Naver에서 가져온 사용자 프로필정보를 문자열 형태로 저장
	    String id = naverUserInfo.getAsJsonObject().get("id").getAsString();
	    String name = unicodeToChar(naverUserInfo.getAsJsonObject().get("name").getAsString());
		String nickname = naverUserInfo.getAsJsonObject().get("nickname").getAsString();
	    String email = naverUserInfo.getAsJsonObject().get("email").getAsString();
		String phoneNumber = naverUserInfo.getAsJsonObject().get("mobile").getAsString();
	    String birthYear = naverUserInfo.getAsJsonObject().get("birthyear").getAsString();
	    String birthDay = naverUserInfo.getAsJsonObject().get("birthday").getAsString();
	    // 출생년도와 생일을 weaveGlow페이지에 맞게 형식 변경
	    String birth = birthYear + "-" + birthDay;
	    
	    // 추출한 사용자 정보 출력 (확인용)
	    System.out.println("naverUserInfo + " + naverUserInfo);
        System.out.println("[Controller로그4] 이름 : " + name );
        System.out.println("[Controller로그5] 닉네임 : "+ nickname);
        System.out.println("[Controller로그6] email : " + email);
        System.out.println("[Controller로그7] 전화번호 : "+ phoneNumber);
        System.out.println("[Controller로그8] 생년월일 :" + birth); 
        
	    try {
	    memberDTO.setSearchCondition("memberNickNameCheck");
	    memberDTO.setMemberNickname(nickname);
	    memberDTO = memberService.selectOne(memberDTO);
	    //닉네임으로 유저 판단 후 사용자 정보가 없다면 회원가입페이지로 이동
	    
	    phoneNumber = phoneNumber.replaceAll("[\\+\\-]", "");
	    //사용자 편의를 위해 아이디와 비밀번호를 랜덤으로 생성하여 DB에 저장
	    String randomID = RandomStringUtils.random(7, true, true);
	    // 비밀번호의 길이 설정 (8~12개의 랜덤)
        int passwordLength = (int) (Math.random() * 5) + 8; 
        //비밀번호 조합
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*?";
        //랜덤한 문자열 생성
        String randomPassword = RandomStringUtils.random(passwordLength, characters);
        
        System.out.println("[확인용 로그] 랜덤 아이디 : "+ randomID + ", 랜덤 비밀번호 : " + randomPassword);
        
        
        if(memberDTO == null) { //회원이 아닐때
        	// DTO에 MemberBirth 형식이 네이버에서 받아오는 형식과 달라 데이터 형식을 홈페이지에 맞춰주기 위한 코드
        	Date date = null;
            String birthDateString = birth;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(birthDateString);
            date = new Date(utilDate.getTime());
        	
        	memberDTO = new MemberDTO();
            memberDTO.setMemberID(randomID);
            memberDTO.setMemberPassword(randomPassword);
            memberDTO.setMemberName(name);
            memberDTO.setMemberNickname(nickname);
            memberDTO.setMemberBirth(date); 			
            memberDTO.setMemberPhone(phoneNumber);	
            memberDTO.setMemberEmail(email);

            model.addAttribute("memberDTO", memberDTO);
            
            return "/user/callback";  // 회원가입 페이지로 이동
        }
       

        // 랜덤으로 생성했던 ID를 세션에 저장
        httpSession.setAttribute("sessionMid", memberDTO.getMemberID());
	   
	    } catch(ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    // 회원가입이 되어 있다면 메인페이지로 이동
        return "redirect:/main";  
	}
	
	
	/* 유니코드를 일반 문자열로 변환하는 메서드
	   네이버에서 넘겨주는 값이 unicode형식으로 데이터를 넘겨주어서
	   해당 문자열의 가독성과 데이터 확인을 위해서 일반 문자열로 변환 */
	 public static String unicodeToChar(String unicode) {
	        StringBuilder sb = new StringBuilder();
	        int i = 0;
	        while (i < unicode.length()) {
	            char c = unicode.charAt(i);
	            if (c == '\\' && i + 1 < unicode.length() && unicode.charAt(i + 1) == 'u') {
	                String hexCode = unicode.substring(i + 2, i + 6);
	                int code = Integer.parseInt(hexCode, 16);
	                sb.append((char) code);
	                i += 6;
	            } else {
	                sb.append(c);
	                i++;
	            }
	        }
	        return sb.toString();
	    }
	
}
