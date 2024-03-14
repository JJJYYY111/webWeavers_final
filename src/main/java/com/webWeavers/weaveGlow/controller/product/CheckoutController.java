package com.webWeavers.weaveGlow.controller.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webWeavers.weaveGlow.biz.address.AddressDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductService;
import com.webWeavers.weaveGlow.biz.cart.CartDTO;
import com.webWeavers.weaveGlow.biz.cart.CartService;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.serial.SerialDTO;
import com.webWeavers.weaveGlow.biz.serial.SerialService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CheckoutController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BuyProductService buyProductService;
	
	@Autowired
	SerialService serialService;
	
	@RequestMapping("/checkout")
	public String checkout(CartDTO cartDTO, MemberDTO memberDTO, HttpSession session, Model model) {
		
		cartDTO.setMemberID((String)session.getAttribute("sessionMid"));
		
		List<CartDTO> cdatas = cartService.selectAll(cartDTO);
		if(cdatas.isEmpty()) { // 장바구니에 물품이 없을경우 유효성검사	
			return "cart";
		}
		// 결제 페이지에 구매할 물품들을 출력하기위해 전달
		model.addAttribute("cartDatas", cdatas);
		
		// 사용자 DTO에 'id'와 검색조건'회원정보'설정
		memberDTO.setMemberID((String)session.getAttribute("sessionMid"));
		memberDTO.setSearchCondition("회원정보");
		if(memberService.selectOne(memberDTO) == null) { // 해당 사용자가 존재하지 않는다면
			return "redirect:/error";
		} else { // 사용자가 존재한다면 사용자의 이름, 이메일, 전화번호, 마케팅(이메일수신) 정보를 request에 저장해서 checkout.jsp로 전송
			model.addAttribute("name", memberDTO.getMemberName());
			model.addAttribute("email", memberDTO.getMemberEmail());
			model.addAttribute("phone", memberDTO.getMemberPhone());
			model.addAttribute("marketing", memberDTO.getMemberMarketing());
			
			//  request에 저장된 값이 존재하기 때문에 forward를 false로 설정
			// 결제페이지로 이동
			return "checkout";
		}
	}
	
	@RequestMapping("/checkoutList")
	public String checkoutList(BuyProductDTO buyProductDTO, HttpSession session, Model model) {
		buyProductDTO.setMemberID((String)session.getAttribute("sessionMid"));
		buyProductDTO.setSearchCondition("전체구매내역");
		
		// 값이 저장된 bDTO를 검색기능인 R(selectAll)기능을 통해서 해당 사용자의 구매내역을 받아옴
		List<BuyProductDTO> datas = buyProductService.selectAll(buyProductDTO);
		// 받아온 구매내역을 주문번호별로 묶기 위해 맵형식으로 저장
		TreeMap<Integer, List<BuyProductDTO>> mapData = new TreeMap<>(Collections.reverseOrder());
		
		for (BuyProductDTO data : datas) { // 반복문을 사용하여 모든 구매내역을 맵으로 저장
            int spk = data.getSerialPK();
            // 이미 해당 spk(주문번호)로 묶인 리스트가 있는지 확인
            if (mapData.containsKey(spk)) { // 해당 구매내역의 주문번호가 이미 key로 존재한다면
                mapData.get(spk).add(data); // 해당 key(주문번호)에 값을 추가
            } else {
                // 해당 spk(주문번호)로 묶인 리스트가 없다면 새로 생성하여 추가하여 값(해당 구매내역)을 저장
            	List<BuyProductDTO> newList = new ArrayList<>();
                newList.add(data);
                mapData.put(spk, newList);
            }
        }
		
		if(datas.size() == 0) { // 구매내역이 존재하지 않을경우 '상품없음'메세지를 보냄
			model.addAttribute("msg", "구매한 내역이 없습니다.");
			return "checkoutList";
		}
		else { // 구매내역이 존재할 경우 selectAll로 받아온 목록 출력해서 보냄
			model.addAttribute("mapData", mapData);
			return "checkoutList";
		}
	}
	
	@RequestMapping("/checkoutSuccess")
	public String checkoutSuccess(CartDTO cartDTO, AddressDTO addressDTO, SerialDTO serialDTO, BuyProductDTO buyProductDTO, ProductDTO pDTO, MemberDTO memberDTO, HttpSession session, Model model) {
	      String mid = (String)session.getAttribute("sessionMid");
	      
	      // 유효성검사를 위한 flag변수 선언 및 초기화;
	      boolean flag = false;
	      
	      // 장바구니DTO에 사용자 id를 저장하여 검색기능인 R(selectAll) 기능을 사용하여 해당 유저의 장바구니목록을 받아옴
	      cartDTO.setMemberID(mid);
	      List<CartDTO> datas = cartService.selectAll(cartDTO);
	      
	      if(datas.isEmpty()) { // 만약 장바구니에 물품이 없을경우 장바구니페이지로 보냄 (유효성 검사)
	    	  return "redirect:/cart";
	      }
	      
	      if(addressDTO.getAddressName() == null) { // 만약 aname(배송지명)이 null이라면
	    	  addressDTO.setAddressName("임시작성배송지"); // 배송명을 초기화
	      }
	      model.addAttribute("addressDTO", addressDTO);// 파라미터값을 저장한 aDTO를 request에 저장   
	      
	      // 주문번호에 저장할 배송지 ( 주소와는 별개로 지정해야함 ), 주소로 배송지를 저장한다면? => 이사해서 주소를 변경할 경우 과거의 배송지도 같이 변경되는 경우가 발생!
	      // 배송지는 request로 값들을 받아와서 한줄의 문자열로 저장
	      String address = "["+addressDTO.getAddressName()+"] "+addressDTO.getAddressRoad()+" "+ addressDTO.getAddressDetail();
	      
	      try {
	         // 주문번호DTO에 사용자 id와 사용자의 배송지를 입력하여 주문번호DAO의 Insert기능을 수행 ( 주문번호를 테이블에 저장 ) 
	         serialDTO.setMemberID(mid);
	         serialDTO.setSerialDeliveryAddress(address);
	         if(serialService.insert(serialDTO)) {    // 테이블에 데이터가 성공적으로 저장이 됐다면
	            System.out.println("SERIAL테이블에 데이터 추가 성공!"); // 콘솔에 성공문구 출력
	         }
	         else {
	            throw new Exception(); // 실패했다면 강제로 예외 발생
	         }
	      }catch(Exception e) { // 예외가 발생했다면 실패문구 출력
	         System.out.println("SERIAL테이블에 데이터 추가 실패");
	         e.printStackTrace();
	      }
	      
	      try {
	         for(int i=0; i<datas.size(); i++) {      // 장바구니 배열 사이즈만큼 반복문 진행
	            buyProductDTO.setProductPK(datas.get(i).getProductPK());   // bDTO에 배열의 i번째 pk를 저장
	            buyProductDTO.setCartCnt(datas.get(i).getCartCnt());   // bDTO에 배열의 i번째 cnt를 저장
	            flag = buyProductService.insert(buyProductDTO);         // insert를 해준다
	            if(!flag) { // 구매상품테이블에 데이터 저장을 실패했다면 
	               break; // 반복문 정지
	            }
	         }
	         if(!flag) { // 구매상품테이블에 데이터 저장을 실패했다면
	            throw new Exception(); // 강제로 예외 발생
	         }
	         else { // 구매상품테이블에 데이터 저장을 성공했다면 
	            System.out.println("BUYPRODUCT테이블에 데이터 추가 성공!"); // 구매성공문구 출력
	         }
	      }catch(Exception e) { // 예외가 발생했다면 실패문구 출력
	         System.out.println("BUYPRODUCT테이블에 데이터 추가 실패");
	         e.printStackTrace();
	      }
	      
	      // 구매한 개수만큼 반복(선택구매)★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	      // 구매가 완료 되었다면 장바구니에서 구매한 물건들 전부삭제 => 해당 사용자의 장바구니 전부 비우기
	      try {
	         // 장바구니DTO에 사용자의 id와 '전체삭제'조건을 입력
	         cartDTO.setMemberID(mid);
	         cartDTO.setSearchCondition("전체삭제");
	         flag = cartService.delete(cartDTO); // 장바구니DTO를 사용하여 삭제기능인 D(DELETE)기능을 사용하여 해당유저의 장바구니의 모든 상품 삭제
	         if(flag) { // 장바구니 테이블에서 데이터 삭제가 성공했다면
	            System.out.println("CART 테이블에 데이터 삭제 성공!"); // 삭제성공문구 출력
	         }
	         else { // 장바구니 테이블에서 데이터삭제가 실패했다면
	            throw new Exception(); // 강제로 예외 발생
	         }
	      }
	      
	      catch (Exception e) { // 예외가 발생했다면
	         System.out.println("CART테이블에 데이터 삭제 실패"); // 삭제실패문구 출력
	         e.printStackTrace();
	      }
	      
	      // 최근구매한 상품들을 출력해서 결제완료페이지에 보내줘야하므로
	      // bDTO에 id와 검색조건을 설정하여 검색기능인 R(selectAll)기능을 사용하여 최근 구매한 상품목록을 받아옴 
	      buyProductDTO.setMemberID(mid);
	      buyProductDTO.setSearchCondition("최근구매내역");
	      List<BuyProductDTO> bdatas = buyProductService.selectAll(buyProductDTO);
	      model.addAttribute("bdatas", bdatas); // request에 최근 구매한 상품목록을 저장
	      
	      // 이메일 수신동의 했으면 이메일 보내기
	      // 마케팅 오류날것같음 일단
	      if(memberDTO.getMemberMarketing() != 0) { // 만약 이메일수신동의를 하지 않았다면 
	    	 model.addAttribute("email",memberDTO.getMemberEmail());
	    	 return "redirect:/mailSend";
	      }
	      return "checkoutSuccess";
	}
}
