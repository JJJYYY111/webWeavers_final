package com.webWeavers.weaveGlow.biz.mail;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.webWeavers.weaveGlow.biz.address.AddressDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDTO;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService{
	
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private MemberService memberService;
	
	public void SendMail(AddressDTO addressDTO, List<BuyProductDTO> datas, String recipientAddress){
		
		MimeMessage message = emailSender.createMimeMessage();
		System.out.println(addressDTO);
		System.out.println(datas);
		int totalPrice = 0;
		String html = "<h3>구매해 주셔서 감사합니다</h3><br><span><b>주문번호 : "+datas.get(0).getSerialPK()+"</b></span><br><span><b>주문날짜 : "+ datas.get(0).getSerialRegdate()+"</b></span><br><span><b>배송지 : ["+addressDTO.getAddressZonecode()+"] " + addressDTO.getAddressRoad()+" " + addressDTO.getAddressDetail() + "</b></span> <br><br>"
				+ "<div class=\"table-responsive\" id=\"orderList\">\r\n"
				+ "					<table class=\"table\" style=\"border-spacing:0px; border-collapse: collapse;\">\r\n"
				+ "						<colgroup>\r\n"
				+ "							<col width=\"40%\" />\r\n"
				+ "							<col width=\"20%\" />\r\n"
				+ "							<col width=\"20%\" />\r\n"
				+ "							<col width=\"20%\" />\r\n"
				+ "						</colgroup>\r\n"
				+ "						<thead>\r\n"
				+ "							<tr>\r\n"
				+ "								<th scope=\"col\" style=\"border:2px black solid; text-align:center; border-collapse: collapse; background-color: navajowhite; padding:5px; \">상품명</th>\r\n"
				+ "								<th scope=\"col\" style=\"border:2px black solid; text-align:center; border-collapse: collapse; background-color: navajowhite; padding:5px; \">상품가격</th>\r\n"
				+ "								<th scope=\"col\" style=\"border:2px black solid; text-align:center; border-collapse: collapse; background-color: navajowhite; padding:5px; \">상품수량</th>\r\n"
				+ "								<th scope=\"col\" style=\"border:2px black solid; text-align:center; border-collapse: collapse; background-color: navajowhite; padding:5px; \">합계</th>\r\n"
				+ "							</tr>\r\n"
				+ "						</thead>\r\n"
				+ "						<tbody>\r\n";
		
				// 출력할 구매내역을 for문을 통해 구매한 상품을 반복하여출력
				for(BuyProductDTO data : datas) {
						html += "									<tr>\r\n"
							+ "										<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
							+ "											<p>"+data.getProductName()+"</p>\r\n"
							+ "										</td>\r\n"
							+ "										<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
							+ "											<p>"+data.getProductPrice()+"원</p>\r\n"
							+ "										</td>\r\n"
							+ "										<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
							+ "											<p>"+data.getBuyProductCnt()+"개</p>\r\n"
							+ "										</td>\r\n"
							+ "										<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
							+ "											<p><span class=\"productPrice\">"+data.getProductPrice()*data.getBuyProductCnt()+"</span>원</p>\r\n"
							+ "										</td>\r\n"
							+ "									</tr>\r\n";
							// 구매한 상품들의 총가격을 나타내기위해 구매한 상품의 가격을 총가격에 더하여 저장
							totalPrice += data.getProductPrice()*data.getBuyProductCnt();
				}
				
				html += "							<tr>\r\n"
				+ "								<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
				+ "									<h4>총 결제 금액</h4>\r\n"
				+ "								</td>\r\n"
				+ "								<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
				+ "									<h5>-</h5>\r\n"
				+ "								</td>\r\n"
				+ "								<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
				+ "									<h5>-</h5>\r\n"
				+ "								</td>\r\n"
				+ "								<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
				+ "									<h4>"+totalPrice+"원</h4>\r\n"
				+ "								</td>\r\n"
				+ "							</tr>\r\n"
				+ "						</tbody>\r\n"
				+ "					</table>\r\n"
				+ "				</div>";
				//(String) model.getAttribute("email")
				
				try {
					message.setFrom(new InternetAddress("wgw1008@gmail.com"));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress("wgw1997@naver.com"));
					message.setSubject("구매해 주셔서 감사합니다 - WEAVE GLOW");
					message.setText(html, "UTF-8", "html");
					emailSender.send(message);
				}catch(Exception e) {
					e.printStackTrace();
				}
//		String user = "wgw1008@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
//		String password = "bkrcumodnhxdcfjc"; // 패스워드 bkrcumodnhxdcfjc
	//	
//		// SMTP 서버 정보를 설정
//		Properties prop = new Properties(); 
//		prop.put("mail.smtp.host", "smtp.gmail.com"); // 메일 서버 주소
//		prop.put("mail.smtp.starttls.enable", "true"); // 두 컴퓨터 사이의 연결 암호화 표준 기술 : TLS라는 보안인증서 활성화
//		prop.put("mail.smtp.ssl.protocols","TLSv1.2"); // SSL 프로토콜을 TLS1.2로 설정
//		prop.put("mail.smtp.auth", "true"); // 계정과 비밀번호 넣는 부분으로 사용하겠다는 뜻
//		prop.put("mail.smtp.port", "587"); // TLS를 통신할 포트 : gmail이므로 587포트
	//	
	//	
	//	
//		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(user, password);
//			}
//		});
	//	
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(user));   // 발신자메일주소
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); //수신자메일주소
//			// Subject
//			message.setSubject(subject); //메일 제목을 입력
//			// Text
////			message.setText(text);    //메일 내용을 입력
//			// HTML
//			 message.setContent(html, "text/html; charset=UTF-8");
//			
//			// send the message
//			Transport.send(message); //// Transport.send()를 통해 메세지 전송
//			System.out.println("message sent successfully...");
//			return "checkoutSuccess";
//			
//		} catch (AddressException e) {
//			e.printStackTrace();
//			return "checkoutSuccess";
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return "checkoutSuccess";
//		}
	}
	
	// 아이디|비밀번호 이메일로 찾기
	public int sendIdPwEmail(MemberDTO memberDTO) {
		MimeMessage message = emailSender.createMimeMessage();
		
		String randPW = getRandomPassword(10);
		
		String html = "<h3>[WeaveGlow] 임시 비밀번호</h3><br>"
						+ "<span>발급된 임시 비밀번호는 " + randPW + " 입니다.<br>"
						+ "로그인 후 마이페이지에서 비밀번호 변경바랍니다.";
		try {
			message.setFrom(new InternetAddress("wgw1008@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(memberDTO.getMemberEmail()));
			message.setSubject("[WeaveGlow] 임시 비밀번호");
			message.setText(html, "UTF-8", "html");
			
			// 임시 비밀번호 발급 전, 회원 비밀번호 update
			memberDTO.setMemberPassword(randPW);
			memberDTO.setSearchCondition("updatePW");			
			if(!memberService.update(memberDTO)) {
				return -1;
			}			
			
			// send 메서드로 이메일 전송
			emailSender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return 1;
	}
	
	// 임시 비밀번호를 생성하기 위한 문자후보목록
	private static final char[] CharArray = new char[]{
	        // 숫자
	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        // 대문자
	        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
	        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
	        // 소문자
	        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
	        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	        // 특수기호
	        '@', '$', '!', '%', '*', '?', '&'
	};
	
	// 매개변수로 받은 수의 길이만큼 임시 비밀번호 생성하는 메서드
	public String getRandomPassword(int length) {
		// 보안을 강화하기 위해 SecureRandom 사용
	    SecureRandom random = new SecureRandom();
	    StringBuilder stringBuilder = new StringBuilder();
	    
	    // 랜덤문자배열 변수에 저장 (반복문 실행될 때마다 .length 호출 방지)
	    int charArrayLength = CharArray.length;
	    for (int i = 0; i < length; i++) {
	    	// 랜덤 인덱스로 랜덤 문자 선택 > stringBuilder 객체에 문자 추가
	        stringBuilder.append(CharArray[random.nextInt(charArrayLength)]);
	    }
	    
	    // StringBuilder > String 객체로 변환 후 반환
	    return stringBuilder.toString();
	}

}
