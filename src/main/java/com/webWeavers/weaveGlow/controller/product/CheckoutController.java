package com.webWeavers.weaveGlow.controller.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.serial.SerialDTO;
import com.webWeavers.weaveGlow.biz.serial.SerialService;

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
	ProductService productService;

	@Autowired
	BuyProductService buyProductService;

	@Autowired
	SerialService serialService;

	@RequestMapping("/checkout")
	public String checkout(CartDTO cartDTO, MemberDTO memberDTO, HttpSession session, Model model,
			@RequestParam("selectedProducts") List<Integer> selectedProducts) {
		List<CartDTO> cdatas = new ArrayList<CartDTO>();
		cartDTO.setSearchCondition("cartAddPurchaseList");
		for (int data : selectedProducts) {
			cartDTO.setCartPK(data);
			cdatas.add(cartService.selectOne(cartDTO));
		}
		if (cdatas.isEmpty()) { // 장바구니에 물품이 없을경우 유효성검사
			return "user/cart";
		}
		model.addAttribute("cdatas", cdatas);

		memberDTO.setMemberID((String) session.getAttribute("sessionMid"));
		memberDTO.setSearchCondition("memberInfo");
		memberDTO = memberService.selectOne(memberDTO);
		if (memberDTO == null) { // 해당 사용자가 존재하지 않는다면
			return "redirect:/error";
		}
		model.addAttribute("memberDTO", memberDTO);
		return "user/checkout";
	}

	@RequestMapping("/checkoutList")
	public String checkoutList(BuyProductDTO buyProductDTO, HttpSession session, Model model) {
		buyProductDTO.setMemberID((String) session.getAttribute("sessionMid"));
		buyProductDTO.setSearchCondition("checkoutList");

		// 값이 저장된 bDTO를 검색기능인 R(selectAll)기능을 통해서 해당 사용자의 구매내역을 받아옴
		List<BuyProductDTO> datas = buyProductService.selectAll(buyProductDTO);
		// 받아온 구매내역을 주문번호별로 묶기 위해 맵형식으로 저장
		TreeMap<Integer, List<BuyProductDTO>> mapData = new TreeMap<>(Collections.reverseOrder());

		for (BuyProductDTO data : datas) { // 반복문을 사용하여 모든 구매내역을 맵으로 저장
			int serialPK = data.getSerialPK();
			// 이미 해당 spk(주문번호)로 묶인 리스트가 있는지 확인
			if (mapData.containsKey(serialPK)) { // 해당 구매내역의 주문번호가 이미 key로 존재한다면
				mapData.get(serialPK).add(data); // 해당 key(주문번호)에 값을 추가
			} else {
				// 해당 spk(주문번호)로 묶인 리스트가 없다면 새로 생성하여 추가하여 값(해당 구매내역)을 저장
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
	
	
//	@RequestMapping("/paymentProcess")
//	public @ResponseBody String paymentProcess(@RequestParam("custom_data") List<Integer> custom_data, HttpSession session,
//												CartDTO cartDTO, BuyProductDTO buyProductDTO,
//												ProductDTO productDTO, SerialDTO serialDTO) {
//		System.out.println("paymentProcess진입");
//		System.out.println(custom_data);
//		System.out.println(serialDTO);
//		String mid = (String) session.getAttribute("sessionMid");
//		boolean flag = false;
//
//		List<CartDTO> datas = new ArrayList<CartDTO>();
//		cartDTO.setSearchCondition("cartAddPurchaseList");
//		for (int data : custom_data) {
//			cartDTO.setCartPK(data);
//			System.out.println(cartDTO);
//			datas.add(cartService.selectOne(cartDTO));
//		}
//		if (datas.isEmpty()) {
//			return "redirect:/cart";
//		}
//
//		
//		try {
//			serialDTO.setMemberID(mid);
//			if (serialService.insert(serialDTO)) { 
//				System.out.println("SERIAL테이블에 데이터 추가 성공!"); 
//			} else {
//				throw new Exception();
//			}
//		} catch (Exception e) { 
//			System.out.println("SERIAL테이블에 데이터 추가 실패");
//			e.printStackTrace();
//			return "0";
//		}
//
//		try {
//			for (int i = 0; i < datas.size(); i++) { 
//				buyProductDTO.setProductPK(datas.get(i).getProductPK()); 
//				buyProductDTO.setBuyProductCnt(datas.get(i).getCartCnt()); 
//				flag = buyProductService.insert(buyProductDTO); 
//				if (!flag) { 
//					break; 
//				}
//			}
//			if (!flag) { 
//				throw new Exception(); 
//			} else {
//				System.out.println("BUYPRODUCT테이블에 데이터 추가 성공!");
//			}
//		} catch (Exception e) {
//			System.out.println("BUYPRODUCT테이블에 데이터 추가 실패");
//			e.printStackTrace();
//			return "0";
//		}
//
//		try { // 판매에 따른 상품재고수정
//			productDTO.setSearchCondition("updateBySelling");
//			for (int i = 0; i < datas.size(); i++) { 
//				productDTO.setProductPK(datas.get(i).getProductPK()); 
//				productDTO.setProductQuantity(datas.get(i).getCartCnt()); 
//				flag = productService.update(productDTO); 
//				if (!flag) { 
//					break; 
//				}
//			}
//			if (!flag) { 
//				throw new Exception(); 
//			} else { 
//				System.out.println("PRODUCT테이블 데이터 수정 성공!"); 
//			}
//		} catch (Exception e) { 
//			System.out.println("PRODUCT테이블 데이터 수정 실패");
//			e.printStackTrace();
//			return "0";
//		}
//		
//		try {
//			cartDTO.setSearchCondition("deleteOne");
//			for (CartDTO data : datas) {
//				cartDTO.setCartPK(data.getCartPK());
//				flag = cartService.delete(cartDTO);
//				if(!flag) {
//					break;
//				}
//			}
//			if(!flag) {
//				throw new Exception(); 
//			} else { 
//				System.out.println("CART테이블 데이터 수정 성공!"); 
//			}
//		}
//		catch(Exception e) {
//			System.out.println("CART테이블 데이터 수정 실패");
//			e.printStackTrace();
//			return "0";
//		}
//		
//		return "1";
//	}
	
	
//	 @Transactionl을 사용한 결제처리
	@RequestMapping("/async/paymentProcess")
	public @ResponseBody String paymentProcess(@RequestParam("custom_data") List<Integer> custom_data, CartDTO cartDTO, HttpSession session, SerialDTO serialDTO) {
		System.out.println("paymentProcess진입");
		String mid = (String) session.getAttribute("sessionMid");
		System.out.println(custom_data);
		String result;
		List<CartDTO> datas = new ArrayList<CartDTO>();
		cartDTO.setSearchCondition("cartAddPurchaseList");
		for (int data : custom_data) {
			cartDTO.setCartPK(data);
			System.out.println(cartDTO);
			datas.add(cartService.selectOne(cartDTO));
		}
		if (datas.isEmpty()) {
			return "redirect:/cart";
		}
		
		try {
			result = checkoutService.checkoutPayment(datas, mid, serialDTO);
		}
		catch(Exception e) {
			result = "0";
		}
		return result;
	}
	
	@RequestMapping("/checkoutSuccess")
	public String checkoutSuccess(AddressDTO addressDTO, BuyProductDTO buyProductDTO, 
									MemberDTO memberDTO, HttpSession session, Model model) {

		if (addressDTO.getAddressName() == null) { 
			addressDTO.setAddressName("임시작성배송지"); 
		}
		model.addAttribute("addressDTO", addressDTO);

		buyProductDTO.setMemberID((String) session.getAttribute("sessionMid"));
		buyProductDTO.setSearchCondition("checkoutSuccess");
		List<BuyProductDTO> bdatas = buyProductService.selectAll(buyProductDTO);
		System.out.println(bdatas);
		model.addAttribute("bdatas", bdatas); // request에 최근 구매한 상품목록을 저장

		if (memberDTO.getMemberMarketing() != null) {
			mailService.SendMail(addressDTO, bdatas, memberDTO.getMemberEmail());
		}
		return "user/checkoutSuccess";
	}
}
