package com.webWeavers.weaveGlow.controller.wishlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.wishlist.WishListDTO;
import com.webWeavers.weaveGlow.biz.wishlist.WishListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WishlistController {
	@Autowired
	WishListService wishListService;
	
	@RequestMapping("/async/likeButton") // 매핑이 같은경우 오류가 발생할 수 있음
	public @ResponseBody String likeButton(WishListDTO wishListDTO, HttpSession session) { // 멤버의 인자와 객체가 이름이 다를 경우 종종 사용
		
		wishListDTO.setMemberID((String)session.getAttribute("sessionMid"));	// wDTO에 세션ID 저장 (로그인 상태, 로그아웃 상태 체크)
		WishListDTO data = wishListService.selectOne(wishListDTO);					// selectOne()을 통해 리턴값(객체) 저장
		if(data == null) {								// 찜이 안된 상태라면
			return String.valueOf(wishListService.insert(wishListDTO));
		}
		else {
			return String.valueOf(wishListService.delete(wishListDTO));
		}

	}
	
	@RequestMapping("/wishList")
	public String wishList(WishListDTO wishListDTO, HttpSession session, Model model) {
		wishListDTO.setMemberID((String)session.getAttribute("sessionMid"));
		// wDTO를 사용하여 검색기능인 R(selectAll)기능을 수행하여 해당유저의 찜목록을 받아옴
		List<WishListDTO> wdatas =  wishListService.selectAll(wishListDTO);
		
		// request에 해당유저의 찜목록을 저장
		model.addAttribute("wdatas", wdatas);
		
		return "user/wishList";
	}
}
