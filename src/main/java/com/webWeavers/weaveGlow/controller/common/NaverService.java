package com.webWeavers.weaveGlow.controller.common;

import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class NaverService {
	
	// 네이버 인증 코드를 받아 액세스 토큰을 가져오는 메서드
	public String getNaverAccessToken(String code) {
		String access_Token = "";
		String reqURL = "https://nid.naver.com/oauth2.0/token"; // 네이버 OAuth 토큰 요청 URL

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			// POST 요청에 필요한 파라미터 설정
			String params = "grant_type=authorization_code" + 
							"&client_id=_oxmjIERuaCMnvt3ruj0" + 
							"&client_secret=WbYG3EP5OE" + 
							"&code=" + code	+ 
							"&redirect_uri=http://localhost:8088/naver/callbackNaver";

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(params); // 위에서 설정한 파라미터 전송
			bw.flush();

			int responseCode = conn.getResponseCode(); // HTTP 응답 코드 가져오기
			System.out.println("[Service로그1] responseCode : " + responseCode);

			BufferedReader br;
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 성공 시 응답 스트림 가져오기
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				// 실패 시 에러 스트림 가져오기
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			StringBuilder response = new StringBuilder();
			String line;
			
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();

			// JSON 파싱을 통해 AccessToken 추출
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(response.toString()).getAsJsonObject();
			
			// 응답에서 액세스 토큰 추출
			access_Token = json.get("access_token").getAsString();

			System.out.println("[Service로그2] 토큰 : " + response);

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	// 위에서 받은 access_Token을 이용하여 네이버 사용자 정보를 가져오는 메서드
	public String getNaverUserInfo(String token) {
		// 사용자 정보를 가져오기 위한 URL
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		// 사용자 정보를 저장할 변수
		String result = "";
		// 응답 내용
		String line = "";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + token);

			int responseCode = conn.getResponseCode();
			System.out.println("[Service로그3] 응답코드 : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			while ((line = br.readLine()) != null) {
				result += line;
			}
			
			System.out.println("[Service로그4] 사용자정보 : " + responseCode);

			
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
