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
		  System.out.println("[S로그1]");
	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";
	        //엑세스 토큰 요청

	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            //URL 객체를 사용하여 원격 서버에 HTTP 연결
	          
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로

	            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            //문자열 구성
	            
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=b76a1451cae3876d403c07b8baec5c88"); //  JavaScript KEY 입력
	            sb.append("&redirect_uri=http://localhost:8088/user/callback"); //  인가코드 받은 redirect_uri 입력
	            sb.append("&code=" + code);
	            bw.write(sb.toString());
	            //sb에 저장된 문자열을 서버로 보냄
	            bw.flush();

	            int responseCode = conn.getResponseCode();
	            System.out.println("[S로그2] responseCode : " + responseCode);
	            //결과 코드가 200이라면 성공

	            
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            //서버로부터 데이터를 읽어옴
	           
	            String line = "";
	            String result = "";

	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            //br에서 나오는 값을 한줄씩 읽어와서 result에 추가
	            System.out.println("[S로그3] response body : " + result);

	            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);
	            
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

	            System.out.println("[S로그4] access_token : " + access_Token);
	            System.out.println("[S로그5] refresh_token : " + refresh_Token);

	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return access_Token;
	    }
	  
	  public String createKakaoUser(String token) {

			String reqURL = "https://kapi.kakao.com/v2/user/me";
			//사용자 정보를 가져오기 위한 URL

			 String line = "";
		     String result = "";
		       
		    //access_token을 이용하여 사용자 정보 조회
		    try {
		       URL url = new URL(reqURL);
		       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		       conn.setRequestMethod("POST");
		       conn.setDoOutput(true);
		       conn.setRequestProperty("Authorization", "Bearer " + token); 
		       //전송할 header 작성, access_token전송

		       //결과 코드가 200이라면 성공
		       int responseCode = conn.getResponseCode();
		       System.out.println("[S로그6] responseCode : " + responseCode);

		       
		       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		       //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기

		       while ((line = br.readLine()) != null) {
		           result += line;
		       }
		       
		       System.out.println("[S로그7] response body : " + result);

		       br.close();

		       } catch (IOException e) {
		            e.printStackTrace();
		       }
		    
		    return result;
		 }
	  
}

