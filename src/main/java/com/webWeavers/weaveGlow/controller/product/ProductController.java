package com.webWeavers.weaveGlow.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	// 상품목록페이지로 이동하는 메서드
	@GetMapping("/productList")
	public String productList() {
		return "user/productList";
	}

	// 상품목록페이지에 요청에 따른 상품목록을 출력하는 메서드
	@GetMapping("/async/productList") // 매핑이 같은경우 오류가 발생할 수 있음
	public @ResponseBody String productList(ProductDTO productDTO, HttpSession session,
			@RequestParam("type") String type, Gson gson) { // 멤버의 인자와 객체가 이름이 다를 경우 종종 사용

		System.out.println("비동기 : productList 진입");
		productDTO.setMemberID((String) session.getAttribute("sessionMid")); // pDTO에 세션ID 저장 (로그인/로그아웃 상태 확인 > 찜)
		productDTO.setSearchCondition("userProductList");
		if (type.equals("신상순")) {
			productDTO.setSortType("regdate");
		} else if (type.equals("낮은가격순")) {
			productDTO.setSortType("rowPrice");
		} else if (type.equals("인기순")) {
			productDTO.setSortType("sales");
		}
		return gson.toJson(productService.selectAll(productDTO)); // json 문자열을 응답
	}

	// 상품상세페이지로 이동하는 메서드
	@GetMapping("/productDetail")
	public String productDetail(ProductDTO productDTO, ReviewDTO reviewDTO, WishListDTO wishListDTO,
			HttpSession session, Model model) {
		productDTO.setMemberID((String) session.getAttribute("sessionMid"));
		productDTO.setSearchCondition("userProduct");
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
		if (wishListDTO.getMemberID() == null) {
			wishListDTO.setMemberID("");
		}
		if (wishListService.selectOne(wishListDTO) == null) {
			model.addAttribute("like", 0);
		} else {
			model.addAttribute("like", 1);
		}

		reviewDTO.setSearchCondition("regdate");
		reviewDTO.setMemberID((String) session.getAttribute("sessionMid"));
		List<ReviewDTO> rdatas = reviewService.selectAll(reviewDTO);
		// 리뷰 리스트의 저장된 리뷰가 없는 경우
		if (rdatas == null || rdatas.size() <= 0) {
			System.out.println("해당 상품에 저장된 리뷰가 없음");
			model.addAttribute("msg", "등록된 리뷰가 없습니다");
			return "user/productDetail";
		}
		model.addAttribute("rdatas", rdatas);
		return "user/productDetail";
	}
	
	// 상품명검색결과페이지로 이동하는 메서드
	@GetMapping("/searchProductName")
	public String searchProductName(ProductDTO productDTO, HttpSession session, Model model) {
		productDTO.setMemberID((String) session.getAttribute("sessionMid"));
		productDTO.setSearchCondition("searchName");

		model.addAttribute("datas", productService.selectAll(productDTO));
		model.addAttribute("keyword", productDTO.getProductName());

		return "user/searchProductName";

	}
	
	// 세부카테고리별 상품목록페이지로 이동하는 메서드
	@GetMapping("/subCategoryProductList")
	public String subCategoryProductList(ProductDTO productDTO, Model model) {
		model.addAttribute("subCategoryPK", productDTO.getSubCategoryPK());
		model.addAttribute("categoryPK", productDTO.getCategoryPK());
		return "user/subCategoryProductList";
	}
}
