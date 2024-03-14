package com.webWeavers.weaveGlow.controller.product;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping("/async/productList/{검색조건}") // 매핑이 같은경우 오류가 발생할 수 있음
	public @ResponseBody String productList(ProductDTO productDTO, HttpSession session) { // 멤버의 인자와 객체가 이름이 다를 경우 종종 사용

//		productDTO.setSearchCondition(request.getParameter("검색조건")); // pDTO에 검색조건 저장
		productDTO.setMemberID((String) session.getAttribute("sessionMid")); // pDTO에 세션ID 저장 (로그인/로그아웃 상태 확인 > 찜)

		List<ProductDTO> datas = productService.selectAll(productDTO); // selectAll()을 통해 DB에서 가져온 상품목록을 datas에 저장

		// 과연 한글이 자동으로 될까
//		response.setContentType("application/json"); // 전송하는 데이터가 json 형식임을 클라이언트에게 알려줌
//		response.setCharacterEncoding("UTF-8");

		if (datas == null) { // 데이터가 없는 경우,
			return "[]"; // 빈 배열 반환
		} else { // 데이터가 있는 경우,
			Gson gson = new Gson(); // Gson 라이브러리로 Gson 객체 생성
			String json = gson.toJson(datas); // datas 객체를 json 문자열로 변환
			return json; // json 문자열을 응답
		}
	}
	
	@RequestMapping("/productDetail")
	public String productDetail(ProductDTO productDTO, ReviewDTO reviewDTO, WishListDTO wishListDTO, HttpSession session, Model model) {
		productDTO.setMemberID((String)session.getAttribute("sessionMid"));
		
		// 제품정보의 사용방식을 구분하기위해 작성
		productDTO.setSearchCondition("상세페이지");
		productDTO = productService.selectOne(productDTO);
		// 디버깅을 위한 출력
//		System.out.println(pDTO.getImg());
		
		// 확인한 제품정보가 없는 경우
		if(productDTO == null) {
			return "redirect:/error";
		}
		// 확인한 제품정보가 있는 경우
		else {
			// 제품정보를 ProductDTO에서 받아와 저장
			model.addAttribute("ppk", productDTO.getProductPK());
			model.addAttribute("pname", productDTO.getProductName());
			model.addAttribute("price", productDTO.getProductPrice());
			model.addAttribute("detail", productDTO.getProductDetailImg());
			model.addAttribute("img", productDTO.getProductImg());
		}
		
		// 찜상태 알려주는 기능
		wishListDTO.setMemberID((String)session.getAttribute("sessionMid"));
		// 해당 제품을 찜하지 않은 경우
		if(wishListService.selectOne(wishListDTO) == null) {
			model.addAttribute("like", "0");
		}
		// 해당 제품을 찜한 경우
		else {
			model.addAttribute("like", "1");
		}
		
		// 리뷰목록 보여주는 기능
		reviewDTO.setSearchCondition("메인리뷰출력");
		List<ReviewDTO> rdatas =  reviewService.selectAll(reviewDTO);
		
		// 리뷰 리스트의 저장된 리뷰가 없는 경우
		if(rdatas.size() <= 0) {
			model.addAttribute("msg", "등록된 리뷰가 없습니다");
			return "productDetail";
		}
		// 리뷰 리스트의 저장된 리뷰가 있는 경우
		else {
			// 해당 리뷰를 전달
			model.addAttribute("rdatas", rdatas);
			return "productDetail";
		}
	}
	
	@RequestMapping("/productList")
	public String productList() {
		return "productList";
	}
	
	@RequestMapping("/searchProductName")
	public String searchProductName(ProductDTO productDTO, HttpSession session, Model model) {
		productDTO.setMemberID((String)session.getAttribute("sessionMid"));	
		productDTO.setSearchCondition("이름으로찾기");
		
		List<ProductDTO> datas = productService.selectAll(productDTO);
		
		model.addAttribute("datas", datas);
		
		model.addAttribute("productName",  productDTO.getProductName());
		
		return "searchProductName";

	}
	
}
