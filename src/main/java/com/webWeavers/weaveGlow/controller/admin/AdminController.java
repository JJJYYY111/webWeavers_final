package com.webWeavers.weaveGlow.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.categorization.CategorizationDTO;
import com.webWeavers.weaveGlow.biz.categorization.CategorizationService;
import com.webWeavers.weaveGlow.biz.category.CategoryDTO;
import com.webWeavers.weaveGlow.biz.imageupload.ImageService;
import com.webWeavers.weaveGlow.biz.member.MemberDTO;
import com.webWeavers.weaveGlow.biz.member.MemberService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.serial.SerialDTO;
import com.webWeavers.weaveGlow.biz.serial.SerialService;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryDTO;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryService;

@Controller
public class AdminController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	SerialService serialService;
	@Autowired
	ProductService productService;
	@Autowired
	SubCategoryService subCategoryService;
	@Autowired
	CategorizationService categorizationService;
	@Autowired
	ImageService imageService;
	
	// 관리자페이지 - 관리자메인페이지로 이동하는 메서드
	@RequestMapping("/adminDashboard")
	public String adminDashboard(ProductDTO productDTO, MemberDTO memberDTO, Model model) {
		productDTO.setSearchCondition("adminSalesThisMonth");
		model.addAttribute("monthlyTotalSales", productService.selectOne(productDTO));
		productDTO.setSearchCondition("adminTodaySales");
		model.addAttribute("dailyTotalSales", productService.selectOne(productDTO));
		productDTO.setSearchCondition("adminSalesProductTotalNum");
		model.addAttribute("productTotalCount", productService.selectOne(productDTO));
		memberDTO.setSearchCondition("totalMember");
		model.addAttribute("memberTotalCount", memberService.selectOne(memberDTO));
		return "admin/adminDashboard";
	}
	
	// 회원관리 ======================================================================
	// ============================================================================
	
	// 관리자페이지 - 회원목록으로 이동하는 메서드
	@RequestMapping("/adminMemberStatus")
	public String adminMemberStatus(MemberDTO memberDTO, Model model) {
		memberDTO.setSearchCondition("allMemberInfo");
		model.addAttribute("memberDatas", memberService.selectAll(memberDTO));
		return "admin/adminMemberStatus";
	}
	
	// 관리자페이지 - 해당 회원의 정보수정페이지로 이동하는 메서드
	@RequestMapping("/adminMemberStatusChange")
	public String adminMemberStatusChange(MemberDTO memberDTO, Model model) {
		memberDTO.setSearchCondition("idCheck");
		memberDTO = memberService.selectOne(memberDTO);		
		model.addAttribute("memberID", memberDTO.getMemberID());
		model.addAttribute("memberPassword", memberDTO.getMemberPassword());
		model.addAttribute("memberName", memberDTO.getMemberName());
		model.addAttribute("memberBirth", memberDTO.getMemberBirth());
		model.addAttribute("memberPhone", memberDTO.getMemberPhone());
		model.addAttribute("memberNickname", memberDTO.getMemberNickname());
		model.addAttribute("memberEmail", memberDTO.getMemberEmail());
		model.addAttribute("gradePK", memberDTO.getGradePK());
		return "admin/adminMemberStatusChange";
	}
	
	// 관리자페이지 - 회원정보수정을 수행하는 메서드 
	@RequestMapping("/adminMemberUpdate")
	public String adminMemberUpdate(MemberDTO memberDTO) {
		memberDTO.setSearchCondition("adminUpdateMember");
		System.out.println(memberDTO);
		if(!memberService.update(memberDTO)) {
			return "redirect:/error";
		}
		return "redirect:/adminMemberStatus";
	}
	
	// 관리자페이지 - 회원검색기능의 메서드
	@RequestMapping("/async/adminMemberSearch")
	public @ResponseBody String adminMemberSearch(MemberDTO memberDTO, Gson gson) {
		memberDTO.setSearchCondition("userSearch");
		System.out.println(memberDTO);
		return gson.toJson(memberService.selectAll(memberDTO));
	}

	// 상품관리 ======================================================================	
	// ============================================================================
	
	// 관리자페이지 - 상품목록으로 이동하는 메서드
	@RequestMapping("/adminProductStatus")
	public String adminProductStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminProductList");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminProductStatus";
	}
	
	// 관리자페이지 - 상품등록페이지로 이동하는 메서드
	@RequestMapping("/adminProductRegistration")
	public String adminProductRegistration() {
		return "admin/adminProductRegistration";
	}
	
	// 관리자페이지 - 상품등록기능을 수행하는 메서드
	@RequestMapping("/adminProductInsert")
	public String adminProductInsert(ProductDTO productDTO, CategorizationDTO categorizationDTO, 
									@RequestParam("productImage") MultipartFile productImage, 
									@RequestParam("productDetailImage") MultipartFile productDetailImage,
									@RequestParam("subCategoryName") List<Integer> subCategoryNames) {
		// 1번기능 상품추가
		productDTO.setProductImg(imageService.imageInsert(productImage, "product"));
		productDTO.setProductDetailImg(imageService.imageInsert(productDetailImage, "product"));
		System.out.println(productDTO);
		productService.insert(productDTO);
		
		// 2번기능 방금 등록한 상품 productPK번호 받아와서 카테고리에 추가하도록 사전설정
		productDTO.setSearchCondition("productInsert");
		categorizationDTO.setProductPK(productService.selectOne(productDTO).getProductPK());
		
		// 3번기능 카테고리 추가
		for(int data: subCategoryNames) {
			categorizationDTO.setSubCategoryPK(data);
			System.out.println(categorizationDTO);
			categorizationService.insert(categorizationDTO);
		}
		return "redirect:/adminProductStatus";
	}
	
	// 관리자페이지 - 상품정보수정페이지로 이동하는 메서드
	@RequestMapping("/adminProductStatusChange")
	public String adminProductStatusChange(ProductDTO productDTO, CategoryDTO categoryDTO, CategorizationDTO categorizationDTO, Model model) {
		productDTO.setSearchCondition("adminProduct");
		model.addAttribute("productDTO", productService.selectOne(productDTO));
		model.addAttribute("categorizationDTO", categorizationService.selectOne(categorizationDTO));
		return "admin/adminProductStatusChange";
	}
	
	// 관리자페이지 - 상품정보수정을 수행하는 메서드
	@RequestMapping("/adminProductUpdate")
	public String adminProductUpdate(ProductDTO productDTO, CategorizationDTO categorizationDTO, 
										@RequestParam("productImage") MultipartFile productImage, 
										@RequestParam("productDetailImage") MultipartFile productDetailImage,
										@RequestParam("subCategoryName") List<Integer> subCategoryNames) {
		
		productDTO.setSearchCondition("adminProduct");
		ProductDTO data = productService.selectOne(productDTO);
		productDTO.setProductImg(data.getProductImg());
		productDTO.setProductDetailImg(data.getProductDetailImg());
		if(!productImage.isEmpty()) {
			productDTO.setProductImg(imageService.imageUpdate(productImage, data.getProductImg(), "product"));
		}
		if(!productDetailImage.isEmpty()) {
			productDTO.setProductDetailImg(imageService.imageUpdate(productDetailImage, data.getProductDetailImg(), "product"));
		}
		productDTO.setSearchCondition("updateAdminProduct");
		productService.update(productDTO);
		
		categorizationService.delete(categorizationDTO);
		for(int subcategoryName: subCategoryNames) {
			categorizationDTO.setSubCategoryPK(subcategoryName);
			System.out.println(categorizationDTO);
			categorizationService.insert(categorizationDTO);
		}
		return "redirect:/adminProductStatus";
	}
	
	// 관리자페이지 - 상품수정페이지에 해당 상품의 카테고리분류를 나타내기위한 메서드
	@RequestMapping("/async/adminCategoryCheckbox")
	public @ResponseBody String adminCategoryCheckbox(CategorizationDTO categorizationDTO, Gson gson, SubCategoryDTO subCategoryDTO) {
		return gson.toJson(categorizationService.selectAll(categorizationDTO));
	}
	
	// 주문관리 ======================================================================
	// ============================================================================
	
	// 관리자페이지 - 주문관리페이지로 이동하는 메서드
	@RequestMapping("/adminOrderStatus")
	public String adminOrderStatus(SerialDTO serialDTO, Model model) {
		serialDTO.setSearchCondition("orderList");
		model.addAttribute("serialDatas", serialService.selectAll(serialDTO));
		return "admin/adminOrderStatus";
	}
	
	// 관리자페이지 - 해당 주문번호의 구매한 상품들을 출력하기위한 메서드
	@RequestMapping("/async/adminDetailOrderList")
	public @ResponseBody String adminGetOrderList(SerialDTO serialDTO, Gson gson) {
		serialDTO.setSearchCondition("orderProduct");
		return gson.toJson(serialService.selectAll(serialDTO));
	}
	
	// 관리자페이지 - 특정 주문번호를 검색하기위한 메서드
	@RequestMapping("/async/adminSearchSerial")
	public @ResponseBody String adminSearchSerial(SerialDTO serialDTO, Gson gson) {
		serialDTO.setSearchCondition("orderSearch");
		return gson.toJson(serialService.selectAll(serialDTO));
	}
	
	// 관리자페이지 - 해당주문번호의 주문상태수정기능을 수행하는 메서드
	@RequestMapping("/async/adminSerialStatusChange")
	public @ResponseBody String adminSerialStatus(SerialDTO serialDTO, Gson gson) {
		return String.valueOf(serialService.update(serialDTO));
	}
	
	// 매출관리 ======================================================================
	// ============================================================================
	
	// 관리자페이지 - 총매출현황페이지로 이동하는 메서드
	@RequestMapping("/adminSalesStatus")
	public String adminSalesStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminProductSales");
		model.addAttribute("productSaleDatas", productService.selectAll(productDTO));
		return "admin/adminSalesStatus";
	}
	
	// 관리자페이지 - 특정상품의 판매현황을 검색하기위한 메서드
	@RequestMapping("/async/adminSearchSales")
	public @ResponseBody String adminSearchSales(ProductDTO productDTO, Gson gson){
		productDTO.setSearchCondition("adminProductSales");
		return gson.toJson(productService.selectAll(productDTO));
	}
	
	// 관리자페이지 - 일일매출현황페이지로 이동하는 메서드
	@RequestMapping("/adminDailySalesStatus")
	public String adminDailySalesStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminDailySales");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminDailySalesStatus";
	}
	
	// 관리자페이지 - 금일매출현황그래프를 나타내기위한 메서드
	@RequestMapping("/async/adminTodaySalesGraph")
	public @ResponseBody String adminTodaySalesGraph(ProductDTO productDTO, Gson gson) {
		productDTO.setSearchCondition("adminTodaySalesByHours");
		return gson.toJson(productService.selectAll(productDTO));
	}
	
	// 관리자페이지 - 전일매출현황그래프를 나타내기위한 메서드
	@RequestMapping("/async/adminYesterdaySalesGraph")
	public @ResponseBody String adminYesterdaySalesGraph(ProductDTO productDTO, Gson gson) {
		productDTO.setSearchCondition("adminPvdaySalesByHours");
		return gson.toJson(productService.selectAll(productDTO));
	}
	
	// 관리자페이지 - 월별매출현황페이지로 이동하는 메서드
	@RequestMapping("/adminMonthlySalesStatus")
	public String adminMonthlySalesStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminMonthlySales");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminMonthlySalesStatus";
	}
	
	// 관리자페이지 - 월별매출현황그래프를 나타내기위한 메서드
	@RequestMapping("/async/adminMonthlySalesGraph")
	public @ResponseBody String adminMonthlySalesGraph(ProductDTO productDTO, Gson gson) {
		productDTO.setSearchCondition("adminMonthlySalesGraph");
		return gson.toJson(productService.selectAll(productDTO));
	}
	
	// 관리자페이지 - 카테고리별 매출현황을 도넛그래프로 나타내기위한 메서드
	@RequestMapping("/async/adminDonutGraph")
	public @ResponseBody String adminDonutGraph(ProductDTO productDTO, Gson gson) {
		productDTO.setSearchCondition("adminCategorySalesDonut");
		List<ProductDTO> datas = productService.selectAll(productDTO);
		System.out.println("도넛진입 :"+datas);
		return gson.toJson(datas);
	}
	
	
	
}