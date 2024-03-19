package com.webWeavers.weaveGlow.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.review.ReviewDTO;
import com.webWeavers.weaveGlow.biz.review.ReviewService;
import com.webWeavers.weaveGlow.biz.wishlist.WishListDTO;
import com.webWeavers.weaveGlow.biz.wishlist.WishListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	WishListService wishListService;

	// 검색키워드 들어오기 때문에 키워드 넣어주어야함
	@RequestMapping("/async/productList") // 매핑이 같은경우 오류가 발생할 수 있음
	public @ResponseBody String productList(ProductDTO productDTO, HttpSession session,
			@RequestParam("type") String type) { // 멤버의 인자와 객체가 이름이 다를 경우 종종 사용

		System.out.println("비동기 : productList 진입");
		productDTO.setMemberID((String) session.getAttribute("sessionMid")); // pDTO에 세션ID 저장 (로그인/로그아웃 상태 확인 > 찜)
		if (type.equals("신상순")) {
			productDTO.setSearchCondition("regdate");
		} else if (type.equals("낮은가격순")) {
			productDTO.setSearchCondition("rowPrice");
		} else if (type.equals("인기순")) {
			productDTO.setSearchCondition("sales");
		}
		System.out.println(productDTO);
		List<ProductDTO> datas = productService.selectAll(productDTO); // selectAll()을 통해 DB에서 가져온 상품목록을 datas에 저장
		System.out.println(datas);
		if (datas == null) { // 데이터가 없는 경우,
			return "[]"; // 빈 배열 반환
		} else { // 데이터가 있는 경우,
			Gson gson = new Gson(); // Gson 라이브러리로 Gson 객체 생성
			String json = gson.toJson(datas); // datas 객체를 json 문자열로 변환
			System.out.println(json);
			return json; // json 문자열을 응답
		}
	}

	@RequestMapping("/productDetail")
	public String productDetail(ProductDTO productDTO, ReviewDTO reviewDTO, WishListDTO wishListDTO,
			HttpSession session, Model model) {
		productDTO.setMemberID((String) session.getAttribute("sessionMid"));
		productDTO = productService.selectOne(productDTO);

		if (productDTO == null) {
			return "redirect:/error";
		}
		model.addAttribute("productPK", productDTO.getProductPK());
		model.addAttribute("productName", productDTO.getProductName());
		model.addAttribute("productPrice", productDTO.getProductPrice());
		model.addAttribute("productDetailImg", productDTO.getProductDetailImg());
		model.addAttribute("productImg", productDTO.getProductImg());
		
		wishListDTO.setMemberID((String) session.getAttribute("sessionMid"));
		if(wishListDTO.getMemberID()== null) {
			wishListDTO.setMemberID("");
		}
		System.out.println(wishListDTO);
		if (wishListService.selectOne(wishListDTO) == null) {
			model.addAttribute("like", 0);
		} else {
			model.addAttribute("like", 1);
		}
		
		reviewDTO.setSearchCondition("regdate");
		List<ReviewDTO> rdatas = reviewService.selectAll(reviewDTO);

		// 리뷰 리스트의 저장된 리뷰가 없는 경우
		if (rdatas == null || rdatas.size() <= 0) {
			System.out.println("해당 상품에 저장된 리뷰가 없음");
			model.addAttribute("msg", "등록된 리뷰가 없습니다");
			return "user/productDetail";
		}
		// 리뷰 리스트의 저장된 리뷰가 있는 경우
		else {
			// 해당 리뷰를 전달
			System.out.println("상품상세페이지에 리뷰전달");
			model.addAttribute("rdatas", rdatas);
			return "user/productDetail";
		}
	}

	@RequestMapping("/productList")
	public String productList() {
		return "user/productList";
	}

	@RequestMapping("/searchProductName")
	public String searchProductName(ProductDTO productDTO, HttpSession session, Model model) {
		productDTO.setMemberID((String) session.getAttribute("sessionMid"));
		productDTO.setSearchCondition("이름으로찾기");

		List<ProductDTO> datas = productService.selectAll(productDTO);

		model.addAttribute("datas", datas);

		model.addAttribute("productName", productDTO.getProductName());

		return "user/searchProductName";

	}

}
