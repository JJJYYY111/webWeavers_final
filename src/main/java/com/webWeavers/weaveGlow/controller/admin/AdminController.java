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
	
	@RequestMapping("/adminDashboard")
	public String adminDashboard() {
		return "admin/adminDashboard";
	}
	
	// 회원관리 ======================================================================
	// ============================================================================
	
	@RequestMapping("/adminMemberStatus")
	public String adminMemberStatus(MemberDTO memberDTO, Model model) {
		memberDTO.setSearchCondition("allMemberInfo");
		model.addAttribute("memberDatas", memberService.selectAll(memberDTO));
		return "admin/adminMemberStatus";
	}
	
	
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
	
	@RequestMapping("/adminMemberUpdate")
	public String adminMemberUpdate(MemberDTO memberDTO) {
		memberDTO.setSearchCondition("adminUpdateMember");
		System.out.println(memberDTO);
		if(!memberService.update(memberDTO)) {
			return "redirect:/error";
		}
		return "redirect:/adminMemberStatus";
	}
	
	@RequestMapping("/adminMemberSearch")
	public @ResponseBody String adminMemberSearch(MemberDTO memberDTO) {
		memberDTO.setSearchCondition("userSearch");
		System.out.println(memberDTO);
		List<MemberDTO> datas = memberService.selectAll(memberDTO);
		System.out.println(datas);
		String json;
		if (datas == null) { // 만약 주소목록이 비어있다면
			json = "[]"; // 빈 배열을 반환
		}
		Gson gson = new Gson(); // Gson 객체 생성
		json = gson.toJson(datas);
		System.out.println(json);
		return json;
	}

	// 상품관리 ======================================================================	
	// ============================================================================
	
	@RequestMapping("/adminProductStatus")
	public String adminProductStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminProductList");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminProductStatus";
	}
	
	@RequestMapping("/adminProductRegistration")
	public String adminProductRegistration() {
		return "admin/adminProductRegistration";
	}
	
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
	
	@RequestMapping("/adminProductStatusChange")
	public String adminProductStatusChange(ProductDTO productDTO, CategoryDTO categoryDTO, CategorizationDTO categorizationDTO, Model model) {
		productDTO.setSearchCondition("adminProduct");
		model.addAttribute("productDTO", productService.selectOne(productDTO));
		model.addAttribute("categorizationDTO", categorizationService.selectOne(categorizationDTO));
		return "admin/adminProductStatusChange";
	}
	
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
		productService.update(productDTO);
		
		categorizationService.delete(categorizationDTO);
		for(int subcategoryName: subCategoryNames) {
			categorizationDTO.setSubCategoryPK(subcategoryName);
			System.out.println(categorizationDTO);
			categorizationService.insert(categorizationDTO);
		}
		return "redirect:/adminProductStatus";
	}
	
	@RequestMapping("/adminCategoryCheckbox")
	public @ResponseBody String adminCategoryCheckbox(CategorizationDTO categorizationDTO, Gson gson, SubCategoryDTO subCategoryDTO) {
		return gson.toJson(categorizationService.selectAll(categorizationDTO));
	}
	
	// 주문관리 ======================================================================
	// ============================================================================
	
	@RequestMapping("/adminOrderStatus")
	public String adminOrderStatus(SerialDTO serialDTO, Model model) {
		serialDTO.setSearchCondition("orderList");
		model.addAttribute("serialDatas", serialService.selectAll(serialDTO));
		return "admin/adminOrderStatus";
	}
	
	@RequestMapping("/adminGetOrderList")
	public @ResponseBody String adminGetOrderList(SerialDTO serialDTO, Gson gson) {
		serialDTO.setSearchCondition("orderProduct");
		return gson.toJson(serialService.selectAll(serialDTO));
	}
	
	@RequestMapping("/adminSearchSerial")
	public @ResponseBody String adminSearchSerial(SerialDTO serialDTO, Gson gson) {
		serialDTO.setSearchCondition("orderSearch");
		return gson.toJson(serialService.selectAll(serialDTO));
	}
	
	@RequestMapping("/adminSerialStatus")
	public @ResponseBody String adminSerialStatus(SerialDTO serialDTO, Gson gson) {
		return String.valueOf(serialService.update(serialDTO));
	}
	
	// 매출관리 ======================================================================
	// ============================================================================
	
	@RequestMapping("/adminSalesStatus")
	public String adminSalesStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminProductSales");
		model.addAttribute("productSaleDatas", productService.selectAll(productDTO));
		return "admin/adminSalesStatus";
	}
	
	@RequestMapping("/adminSearchSales")
	public @ResponseBody String adminSearchSales(ProductDTO productDTO, Gson gson){
		productDTO.setSearchCondition("adminProductSales");
		return gson.toJson(productService.selectAll(productDTO));
	}
	
	@RequestMapping("/adminDaliySalesStatus")
	public String adminDaliySalesStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminDailySales");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminDaliySalesStatus";
	}
	
	@RequestMapping("/adminTodaySalesGraph")
	public @ResponseBody String adminTodaySalesGraph(ProductDTO productDTO, Gson gson) {
		productDTO.setSearchCondition("adminTodaySalesByHours");
		return gson.toJson(productService.selectAll(productDTO));
	}
	
	@RequestMapping("/adminYesterdaySalesGraph")
	public @ResponseBody String adminYesterdaySalesGraph(ProductDTO productDTO, Gson gson) {
		productDTO.setSearchCondition("adminPvdaySalesByHours");
		return gson.toJson(productService.selectAll(productDTO));
	}
	
	@RequestMapping("/adminMonthlySalesStatus")
	public String adminMonthlySalesStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminMonthlySales");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminMonthlySalesStatus";
	}
	
	@RequestMapping("/adminMonthlySalesGraph")
	public @ResponseBody String adminMonthlySalesGraph(ProductDTO productDTO, Gson gson) {
		productDTO.setSearchCondition("adminMonthlySalesGraph");
		return gson.toJson(productService.selectAll(productDTO));
	}
	
	
	
}