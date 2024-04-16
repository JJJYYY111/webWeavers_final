package com.webWeavers.weaveGlow.controller.checkout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.address.AddressDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductService;
import com.webWeavers.weaveGlow.biz.cart.CartDTO;
import com.webWeavers.weaveGlow.biz.cart.CartService;
import com.webWeavers.weaveGlow.biz.checkout.CheckoutService;
import com.webWeavers.weaveGlow.biz.mail.MailService;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.serial.SerialDTO;
import jakarta.servlet.http.HttpSession;

@Controller
public class CheckoutController {
	
	@Autowired
	CheckoutService checkoutService;
	
	@Autowired
	MailService mailService;

	@Autowired
	CartService cartService;

	@Autowired
	MemberService memberService;

	@Autowired
	BuyProductService buyProductService;
	
	// 결제페이지로 이동하는 메서드
	@PostMapping("/checkout")
	public String checkout(CartDTO cartDTO, MemberDTO memberDTO, HttpSession session, Model model,
			@RequestParam("selectedProducts") List<Integer> selectedProducts) {
		// 결제페이지에 출력할 상품들 조회하고 List로 생성하여 Model에 추가
		List<CartDTO> cdatas = new ArrayList<CartDTO>();
		cartDTO.setSearchCondition("cartAddPurchaseList");
		for (int data : selectedProducts) {
			cartDTO.setCartPK(data);
			cdatas.add(cartService.selectOne(cartDTO));
		}
		if (cdatas.isEmpty()) {
			return "user/cart";
		}
		model.addAttribute("cdatas", cdatas);
		
		// ID값을 통해 결제페이지에 출력할 회원정보를 조회하고 Model에 추가한뒤 페이지이동
		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		memberDTO.setSearchCondition("memberInfo");
		memberDTO = memberService.selectOne(memberDTO);
		if (memberDTO == null) {
			return "redirect:/error";
		}
		model.addAttribute("memberDTO", memberDTO);
		return "user/checkout";
	}
	
	// 구매목록으로 이동하는 메서드
	@GetMapping("/checkoutList")
	public String checkoutList(BuyProductDTO buyProductDTO, HttpSession session, Model model) {
		
		// 사용자의 ID값과 검색조건을 통해 해당 사용자의 구매내역 조회
		buyProductDTO.setMemberID((String) session.getAttribute("sessionMid"));
		buyProductDTO.setSearchCondition("checkoutList");
		List<BuyProductDTO> datas = buyProductService.selectAll(buyProductDTO);
		
		// 조회한 구매내역을 주문번호별로 묶기 위해 맵형식으로 저장
		TreeMap<Integer, List<BuyProductDTO>> mapData = new TreeMap<>(Collections.reverseOrder());

		for (BuyProductDTO data : datas) { // 반복문을 사용하여 모든 구매내역을 맵으로 저장
			int serialPK = data.getSerialPK();
			// 이미 해당 serialPK(주문번호)로 묶인 리스트가 있는지 확인
			if (mapData.containsKey(serialPK)) { // 해당 구매내역의 주문번호가 이미 key로 존재한다면
				mapData.get(serialPK).add(data); // 해당 key(주문번호)에 값을 추가
			} else {
				// 해당 serialPK(주문번호)로 묶인 리스트가 없다면 새로 생성하여 추가하여 값(해당 구매내역)을 저장
				List<BuyProductDTO> newList = new ArrayList<>();
				newList.add(data);
				mapData.put(serialPK, newList);
			}
		}
		if (datas.size() == 0) { // 구매내역이 존재하지 않을경우 '상품없음'메세지를 보냄
			model.addAttribute("msg", "구매한 내역이 없습니다.");
			return "user/checkoutList";
		} else { // 구매내역이 존재할 경우 selectAll로 받아온 목록 출력해서 보냄
			model.addAttribute("mapData", mapData);
			return "user/checkoutList";
		}
	}
	
//	 @Transactionl을 사용한 결제처리
	@PostMapping("/async/paymentProcess")
	public @ResponseBody String paymentProcess(@RequestParam("custom_data") List<Integer> custom_data, 
												CartDTO cartDTO, HttpSession session, SerialDTO serialDTO) {
		// 결제할 상품들을 장바구니에서 조회한뒤 리스트로 생성
		List<CartDTO> datas = new ArrayList<CartDTO>();
		cartDTO.setSearchCondition("cartAddPurchaseList");
		for (int data : custom_data) {
			cartDTO.setCartPK(data);
			datas.add(cartService.selectOne(cartDTO));
		}
		try {
			// 결제데이터를 DB에 처리하기위한 서비스를 사용 (기능 정상수행시 '1'을 리턴)
			return checkoutService.checkoutUpdate(datas, serialDTO,(String)session.getAttribute("sessionMid"));
		}
		catch(Exception e) { // 서비스 기능에 에러 발생시 '0'을 리턴
			System.out.println("에러 throw 받음 : paymentProcess");
			return "0";
		}
	}
	
	// 결제에 성공할 경우 결제완료페이지로 이동하는 메서드
	@PostMapping("/checkoutSuccess")
	public String checkoutSuccess(AddressDTO addressDTO, BuyProductDTO buyProductDTO, 
									MemberDTO memberDTO, HttpSession session, Model model) {
		// 파라미터값을 바인딩한 커맨드객체의 유효성검사 
		if (addressDTO.getAddressName() == null) { 
			addressDTO.setAddressName("임시작성배송지"); 
		}
		model.addAttribute("addressDTO", addressDTO);
		
		// 사용자의 ID와 검색조건을통해 해당사용자의 최근구매한 상품들을 Model에 추가
		buyProductDTO.setMemberID((String) session.getAttribute("sessionMid"));
		buyProductDTO.setSearchCondition("checkoutSuccess");
		List<BuyProductDTO> bdatas = buyProductService.selectAll(buyProductDTO);
		model.addAttribute("bdatas", bdatas);
		
		// 만약 이메일 수신동의에 체크가 되어있다면 이메일전송서비스진행
		if (memberDTO.getMemberMarketing() != null) {
			mailService.SendMail(addressDTO, bdatas, memberDTO.getMemberEmail());
		}
		// 페이지이동
		return "user/checkoutSuccess";
	}
}
