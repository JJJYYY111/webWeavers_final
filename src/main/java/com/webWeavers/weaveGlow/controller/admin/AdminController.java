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
		productDTO.setProductImg(imageService.imageInsert(productImage));
		productDTO.setProductDetailImg(imageService.imageInsert(productDetailImage));
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
		if(!data.getProductImg().equals(productDTO.getProductImg())) {
			productDTO.setProductImg(imageService.imageUpdate(productImage, data.getProductImg()));
		}
		if(!data.getProductDetailImg().equals(productDTO.getProductDetailImg())) {
			productDTO.setProductDetailImg(imageService.imageUpdate(productDetailImage, data.getProductDetailImg()));
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
	
	@RequestMapping("categoryCheckbox")
	public @ResponseBody String categoryCheckBox(CategorizationDTO categorizationDTO, Gson gson, SubCategoryDTO subCategoryDTO) {
		return gson.toJson(categorizationService.selectAll(categorizationDTO));
	}
	
	@RequestMapping("/adminOrderStatus")
	public String adminOrderStatus(SerialDTO serialDTO, Model model) {
		serialDTO.setSearchCondition("orderList");
		model.addAttribute("serialDatas", serialService.selectAll(serialDTO));
		return "admin/adminOrderStatus";
	}
	
	@RequestMapping("/getOrderList")
	public @ResponseBody String getOrderList(SerialDTO serialDTO, Gson gson) {
		serialDTO.setSearchCondition("orderProduct");
		List<SerialDTO> datas = serialService.selectAll(serialDTO);
		System.out.println(datas);
		return gson.toJson(datas);
	}
	
	@RequestMapping("/searchSerial")
	public @ResponseBody String searchSerial(SerialDTO serialDTO, Gson gson) {
		serialDTO.setSearchCondition("orderSearch");
		return gson.toJson(serialService.selectAll(serialDTO));
	}
	
	
	@RequestMapping("/salesStatus")
	public String salesStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminProductSales");
		model.addAttribute("productSaleDatas", productService.selectAll(productDTO));
		return "admin/salesStatus";
	}
	
	@RequestMapping("/salesSearch")
	public @ResponseBody String salesSearch(){
		
		return "";
	}
	
	@RequestMapping("/daliySalesStatus")
	public String daliySalesStatus() {
		return "admin/daliySalesStatus";
	}
	
	@RequestMapping("/monthlySalesStatus")
	public String monthlySalesStatus() {
		return "admin/monthlySalesStatus";
	}
	
	
	
}