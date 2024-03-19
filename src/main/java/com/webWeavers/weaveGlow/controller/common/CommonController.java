package com.webWeavers.weaveGlow.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommonController {

	@Autowired
	ProductService productService;

	@RequestMapping("/contact")
	public String contact() {
		System.out.println("contact진입");
		return "user/contact";
	}

	@RequestMapping("/")
	public String root(ProductDTO productDTO) {
		System.out.println("로그 : index진입");
		productDTO.setSearchCondition("sales");
		List<ProductDTO> datas = productService.selectAll(productDTO);
		if (datas.size() <= 0) {
			return "redirect:/crawling";
		}
		return "redirect:/main";
	}

	@RequestMapping("/crawling")
	public String Crawling() {
		List<ProductDTO> datas = new ArrayList<ProductDTO>();
		String weburl = "https://tonymoly.com/ctgr/category_product_list.do?i_sCategorycd1=L01&i_sCategorynm1=%EA%B8%B0%EC%B4%88&i_iRecordCnt=188&i_iSoldoutFlag=Y&i_sSortTyp=100&i_sSortSql=X.N_TOT_ORDER_CNT%20DESC&i_iPageSize=40&i_iNowPageNo=1";
		Document doc = null;
		try {
			doc = Jsoup.connect(weburl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements elems = doc.select("li.prd_list");
		Iterator<Element> itr = elems.iterator();
//		int count = 0; // 추가된 객체 개수를 세기 위한 변수
		while (itr.hasNext() /* && count < 5 */) { // 5개까지만 추가
			ProductDTO productDTO = new ProductDTO();
			Element el = itr.next();
			String name = el.select("span.prod-name").text();
			String price1 = el.select("div.selling-price-wrap > em.price-after").text().replace(",", "").replace("원", "");
			String img = el.select("img.over").attr("src");
			String detailImg = "https://tonymoly.com" + el.select("a.link.thumb").attr("href");
			int price = Integer.parseInt(price1);
//			System.out.println(name + " / " + price + " / " + img + " / " + detailImg );
			productDTO.setProductName(name);
			productDTO.setProductPrice(price);
			productDTO.setProductImg(img);
			productDTO.setProductDetailImg(detailImg);
			productDTO.setProductQuantity(50);
			productDTO.setProductStatus(1);
			datas.add(productDTO);
		}
		
		for(int i = 0; i<datas.size(); i++) {
			System.out.println(datas.get(i).getProductDetailImg());
			try {
				doc = Jsoup.connect(datas.get(i).getProductDetailImg()).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			elems = doc.select("div#section_detail_view img:first-child");
			String detailImg = elems.attr("src");
			datas.get(i).setProductDetailImg(detailImg);
			if(!productService.insert(datas.get(i))) {
				return "redirect:/error";
			}
		}
	return"redirect:/main";
	}

//	
//	@RequestMapping("/error")
//	public String error() {
//		return "error";
//	}

//	@RequestMapping("/mailSend")
//	public String mailSend(Model model) {
//		String to= (String) model.getAttribute("email");
//		String subject= "구매해 주셔서 감사합니다 - WEAVE GLOW";
//		String text="상품내역은 생략하겠습니다. 구매해주셔서 감사합니다";
//		
//		AddressDTO addressDTO = (AddressDTO)model.getAttribute("addressDTO");
//		List<BuyProductDTO> datas = (List<BuyProductDTO>)model.getAttribute("bdatas");
//		int totalPrice = 0;
//			// 구매한 상품들을 html형식으로 나타내기위한 문자열
//		String html = "<h3>구매해 주셔서 감사합니다</h3><br><span><b>주문번호 : "+datas.get(0).getSerialPK()+"</b></span><br><span><b>주문날짜 : "+ datas.get(0).getSerialRegdate()+"</b></span><br><span><b>배송지 : ["+addressDTO.getAddressZonecode()+"] " + addressDTO.getAddressRoad()+" " + addressDTO.getAddressDetail() + "</b></span> <br><br>"
//				+ "<div class=\"table-responsive\" id=\"orderList\">\r\n"
//				+ "					<table class=\"table\" style=\"border-spacing:0px; border-collapse: collapse;\">\r\n"
//				+ "						<colgroup>\r\n"
//				+ "							<col width=\"40%\" />\r\n"
//				+ "							<col width=\"20%\" />\r\n"
//				+ "							<col width=\"20%\" />\r\n"
//				+ "							<col width=\"20%\" />\r\n"
//				+ "						</colgroup>\r\n"
//				+ "						<thead>\r\n"
//				+ "							<tr>\r\n"
//				+ "								<th scope=\"col\" style=\"border:2px black solid; text-align:center; border-collapse: collapse; background-color: navajowhite; padding:5px; \">상품명</th>\r\n"
//				+ "								<th scope=\"col\" style=\"border:2px black solid; text-align:center; border-collapse: collapse; background-color: navajowhite; padding:5px; \">상품가격</th>\r\n"
//				+ "								<th scope=\"col\" style=\"border:2px black solid; text-align:center; border-collapse: collapse; background-color: navajowhite; padding:5px; \">상품수량</th>\r\n"
//				+ "								<th scope=\"col\" style=\"border:2px black solid; text-align:center; border-collapse: collapse; background-color: navajowhite; padding:5px; \">합계</th>\r\n"
//				+ "							</tr>\r\n"
//				+ "						</thead>\r\n"
//				+ "						<tbody>\r\n";
//		
//				// 출력할 구매내역을 for문을 통해 구매한 상품을 반복하여출력
//				for(BuyProductDTO data : datas) {
//						html += "									<tr>\r\n"
//							+ "										<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
//							+ "											<p>"+data.getProductName()+"</p>\r\n"
//							+ "										</td>\r\n"
//							+ "										<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
//							+ "											<p>"+data.getProductPrice()+"원</p>\r\n"
//							+ "										</td>\r\n"
//							+ "										<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
//							+ "											<p>"+data.getCartCnt()+"개</p>\r\n"
//							+ "										</td>\r\n"
//							+ "										<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
//							+ "											<p><span class=\"productPrice\">"+data.getProductPrice()*data.getCartCnt()+"</span>원</p>\r\n"
//							+ "										</td>\r\n"
//							+ "									</tr>\r\n";
//							// 구매한 상품들의 총가격을 나타내기위해 구매한 상품의 가격을 총가격에 더하여 저장
//							totalPrice += data.getProductPrice()*data.getCartCnt();
//				}
//				
//				html += "							<tr>\r\n"
//				+ "								<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
//				+ "									<h4>총 결제 금액</h4>\r\n"
//				+ "								</td>\r\n"
//				+ "								<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
//				+ "									<h5>-</h5>\r\n"
//				+ "								</td>\r\n"
//				+ "								<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
//				+ "									<h5>-</h5>\r\n"
//				+ "								</td>\r\n"
//				+ "								<td style=\"border:2px black solid; text-align:center; border-collapse: collapse; padding:5px;\">\r\n"
//				+ "									<h4>"+totalPrice+"원</h4>\r\n"
//				+ "								</td>\r\n"
//				+ "							</tr>\r\n"
//				+ "						</tbody>\r\n"
//				+ "					</table>\r\n"
//				+ "				</div>";
//		
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
//	}

	@RequestMapping("/main")
	public String main(ProductDTO productDTO, HttpSession session, Model model) {
		System.out.println("메인 인덱스 진입");
		
		// 메인페이지에 호출되는 전체상품의 정렬방식을 구분하기위해 작성
		// 해당 방식은 찜의 개수로 순서를 정함
		productDTO.setSearchCondition("wish");
		
		productDTO.setMemberID((String)session.getAttribute("sessionMid"));
		//찜 유무 확인을 위해 아이디 저장

		// 추천순으로 정렬된 상품정보를 리스트에 저장
		List<ProductDTO> wdatas = productService.selectAll(productDTO);
		model.addAttribute("wdatas", wdatas);
		
		// 메인페이지에 호출되는 전체상품의 정렬방식을 구분하기위해 작성
		// 해당 방식은 상품 판매량으로 순서를 정함
		productDTO.setSearchCondition("sales");

		// 판매량순으로 정렬된 상품정보를 리스트에 저장
		List<ProductDTO> sdatas = productService.selectAll(productDTO);
		model.addAttribute("sdatas", sdatas);
		return "user/main";
	}
}
