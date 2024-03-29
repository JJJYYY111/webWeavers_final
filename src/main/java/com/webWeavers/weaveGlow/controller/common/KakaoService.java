package com.webWeavers.weaveGlow.controller.common;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KakaoService {
	
	  public String getKakaoAccessToken (String code) {
		  System.out.println("로그 1");
	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";

	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);

	            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=2d1ef19b01cc757510b19a63c5da33f0"); //  REST_API_KEY 입력
	            sb.append("&redirect_uri=http://localhost:8088/user/callback"); //  인가코드 받은 redirect_uri 입력
	            sb.append("&code=" + code);
	            bw.write(sb.toString());
	            bw.flush();

	            //결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);

	            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";

	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println("response body : " + result);

	            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);

	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

	            System.out.println("access_token : " + access_Token);
	            System.out.println("refresh_token : " + refresh_Token);

	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return access_Token;
	    }
	  
	  public String createKakaoUser(String token) {

			String reqURL = "https://kapi.kakao.com/v2/user/me";

			 String line = "";
		       String result = "";
		       
		    //access_token을 이용하여 사용자 정보 조회
		    try {
		       URL url = new URL(reqURL);
		       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		       conn.setRequestMethod("POST");
		       conn.setDoOutput(true);
		       conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

		       //결과 코드가 200이라면 성공
		       int responseCode = conn.getResponseCode();
		       System.out.println("responseCode : " + responseCode);

		       //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
		       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      

		       while ((line = br.readLine()) != null) {
		           result += line;
		       }
		       System.out.println("response body : " + result);

				/*
				 * JsonParser parser = new JsonParser(); JsonElement element =
				 * parser.parse(result);
				 * 
				 * int id = element.getAsJsonObject().get("id").getAsInt(); JsonElement
				 * kakaoAccount = element.getAsJsonObject().get("kakao_account");
				 * 
				 * JsonElement profile = kakaoAccount.getAsJsonObject().get("profile");
				 * 
				 * String name = profile.getAsJsonObject().get("nickname").getAsString();
				 * System.out.println("이름: "+name);
				 * 
				 * boolean hasEmail = false; String email = "";
				 * 
				 * if (kakaoAccount != null && !kakaoAccount.isJsonNull()) { JsonElement
				 * hasEmailElement = kakaoAccount.getAsJsonObject().get("has_email"); if
				 * (hasEmailElement != null && !hasEmailElement.isJsonNull()) { hasEmail =
				 * hasEmailElement.getAsBoolean(); }
				 * 
				 * if (hasEmail) { JsonElement emailElement =
				 * kakaoAccount.getAsJsonObject().get("email"); if (emailElement != null &&
				 * !emailElement.isJsonNull()) { email = emailElement.getAsString(); } } }
				 */

		      

		       br.close();

		       } catch (IOException e) {
		            e.printStackTrace();
		       }
		    
		    return result;
		 }

}

